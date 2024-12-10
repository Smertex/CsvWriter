package by.smertex.util.realisation;

import by.smertex.exception.util.ComponentDirectoryIsEmpty;
import by.smertex.exception.util.LoadComponentException;
import by.smertex.util.interfaces.ClassFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Базовая реализация поиска всех классов по указанной папке
 * @author Киселев Антон
 * @version 1.0
 */
public class ClassFinderBasicRealisation implements ClassFinder {

    /**
     * Найденные класы, инициализируются при создании ClassFinderBasicRealisation
     */
    private List<Class<?>> allClassInProject;

    public ClassFinderBasicRealisation(String componentPath){
        init(componentPath);
    }

    private void init(String componentPath){
        allClassInProject = findClasses(componentPath);
    }

    /**
     * Рекурсивно сканирует папки и ищет в них классы
     * @param componentPath передаваемый путь, все классы которого будут отсканированы
     * @return возвращает все найденные классы
     */
    @Override
    public List<Class<?>> findClasses(String componentPath) {
        List<String> objects = objectInDirectory(componentPath);
        List<Class<?>> allClassInDirectory = findClassInDirectory(objects, componentPath);
        allClassInDirectory.addAll(recursiveTraversal(objects, componentPath));

        return allClassInDirectory;
    }

    /**
     * Получение всех отсканированных классов
     */
    @Override
    public List<Class<?>> getClasses() {
        return allClassInProject;
    }

    /**
     * Сканирует все объекты в пакете
     * @param componentPath передаваемый путь, все классы которого будут отсканированы
     */
    private List<String> objectInDirectory(String componentPath){
        try(InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(componentPath.replaceAll("[.]", "/"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){

            return reader.lines().collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new ComponentDirectoryIsEmpty(e.getMessage());
        } catch (IOException e) {
            throw new LoadComponentException(e.getMessage());
        }
    }

    /**
     * Метод для поиска классов в пакете
     * @param objects - все элементы исходного пакета
     * @param componentPath - полный путь
     * @return возвращает найденные классы
     */
    private List<Class<?>> findClassInDirectory(List<String> objects, String componentPath){
        return objects.stream()
                .filter(line -> line.endsWith(".class"))
                .map(clazz -> pathToClass(mergeClassPath(componentPath, clazz)))
                .collect(Collectors.toList());
    }

    /**
     * Метод, реализующий рекурсивный обход
     * @param objects - все элементы исходного пакета
     * @param componentPath - полный путь
     */
    private List<Class<?>> recursiveTraversal(List<String> objects, String componentPath){
        return objects.stream()
                .filter(line -> !line.contains("."))
                .map(directory -> findClasses(pathMerging(componentPath, directory)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
