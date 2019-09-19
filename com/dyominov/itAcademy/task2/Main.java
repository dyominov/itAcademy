package task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Class<?>> list = new ArrayList<>();
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
        list.add(MyClassWithAnnotation.class);
        list.add(MyClassWithoutAnnotation.class);
        List<Class<?>> classes = analyzer.analyz(list, MyAnnotation.class);
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("config.properties");
            properties.load(fis);
            String name = properties.getProperty("name");
            String email = properties.getProperty("email");
            for (int i = 0; i < classes.size(); i++) {
                Constructor constructor = list.get(i).getConstructor();
                Object obj = constructor.newInstance();
                Field fieldName = obj.getClass().getDeclaredField("name");
                fieldName.setAccessible(true);
                fieldName.set(obj, name);
                Field fieldEmail = obj.getClass().getDeclaredField("email");
                fieldEmail.setAccessible(true);
                fieldEmail.set(obj, email);
                System.out.println(obj);
                Object obj2 = list.get(i).newInstance();
                MyClassWithAnnotation myClass = (MyClassWithAnnotation) obj2;
                myClass.setName(name);
                myClass.setEmail(email);
                System.out.println(myClass);
            }
        } catch (FileNotFoundException ignored) {
        }
    }
}
