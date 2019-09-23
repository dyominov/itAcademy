package task2;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Class<?>> list = loadData();
        AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
        List<Class<?>> classes = analyzer.analyze(list, MyAnnotation.class);
        FileInputStream fis = new FileInputStream("config.properties");
        createClassesInstancesFromProperties(fis, classes, list);

    }


    private static List<Class<?>> loadData() {
        List<Class<?>> list = new ArrayList<>();
        list.add(MyClassWithAnnotation.class);
        list.add(MyClassWithoutAnnotation.class);
        return list;
    }

    private static void createClassesInstancesFromProperties(
            FileInputStream fis,
            List<Class<?>> classes,
            List<Class<?>> list)
            throws Exception {
        Properties properties = new Properties();
        properties.load(fis);
        for (int i = 0; i < classes.size(); i++) {
            Field[] fields = list.get(i).getDeclaredFields();
            Constructor constructor = list.get(i).getConstructor();
            Object obj = constructor.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, properties.getProperty(field.getName()));
            }
            System.out.println(obj);
        }
    }
}
