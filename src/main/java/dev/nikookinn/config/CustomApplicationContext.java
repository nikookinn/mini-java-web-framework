package dev.nikookinn.config;

import dev.nikookinn.annotations.MyAutowired;
import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.annotations.MyController;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * CustomApplicationContext - Container for application beans

 * This class manages the initializes of all beans, performs component scanning,
 * and handles dependency injection.
 */
public class CustomApplicationContext {
    private final Map<Class<?>,Object> beanMap = new HashMap<>();


    /**
     * Scans the specified package for components and initializes beans
     *
     * @param basePackage The base package and sub package to scan
     * @throws ClassNotFoundException If a class cannot be loaded
     * @throws NoSuchMethodException If a constructor cannot be found
     * @throws InvocationTargetException If constructor invocation fails
     * @throws InstantiationException If a class cannot be instantiated
     * @throws IllegalAccessException If a constructor is not accessible
     */
    public void scan(String basePackage) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        beanMap.put(CustomApplicationContext.class, this);

        String path = basePackage.replace(".","/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        File dir = new File(resource != null ? resource.getFile() : null);

        recursiveScan(dir, basePackage);
        
        injectBeansInClazz(beanMap.values());
    }

    /**
     * Recursively scans directories for component classes
     *
     * @param dir The directory to scan
     * @param basePackage The current package name
     * @throws ClassNotFoundException If a class cannot be loaded
     * @throws NoSuchMethodException If a constructor cannot be found
     * @throws InvocationTargetException If constructor invocation fails
     * @throws InstantiationException If a class cannot be instantiated
     * @throws IllegalAccessException If a constructor is not accessible
     */
    private void recursiveScan(File dir, String basePackage) throws
            ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                recursiveScan(file, basePackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = basePackage + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(MyComponent.class) || clazz.isAnnotationPresent(MyController.class)) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    beanMap.put(clazz, instance);

                    for (Class<?> iface : clazz.getInterfaces()) {
                        beanMap.put(iface, instance);
                    }
                }
            }
        }
    }

    /**
     * Performs dependency injection for all beans which is annotated by @MyAutowire annotation
     *
     * @param values Collection of bean instances
     * @throws IllegalAccessException If a field is not accessible
     */
    public void injectBeansInClazz(Collection<Object> values) throws IllegalAccessException {
        for (Object bean : values) {
            for (Field field : bean.getClass().getDeclaredFields()){
                if (field.isAnnotationPresent(MyAutowired.class)){
                    field.setAccessible(true);
                    Object dependency = beanMap.get(field.getType());
                    field.set(bean,dependency);
                }
            }
        }

    }

    /**
     * Gets a bean by its class type
     *
     * @param clazz The class type of the bean
     * @return The bean instance
     */
    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beanMap.get(clazz));
    }

    /**
     * Gets the entire bean map
     *
     * @return Map of class types to bean instances
     */
    public Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
