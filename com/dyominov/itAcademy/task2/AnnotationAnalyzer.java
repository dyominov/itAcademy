package task2;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

class AnnotationAnalyzer {

    List<Class<?>> analyz(List<Class<?>> classes, Class<? extends Annotation> annotation) {
        List<Class<?>> classesWithAnnotation = new ArrayList<>();
        for (Class<?> aClass : classes) {
            if (aClass.isAnnotationPresent(annotation)) {
                classesWithAnnotation.add(aClass);
            }
        }
        return classesWithAnnotation;
    }
}
