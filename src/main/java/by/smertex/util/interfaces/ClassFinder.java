package by.smertex.util.interfaces;

import java.util.List;

/**
 * Абстрацкия для поиска всех классов по указанной папке
 * @author Киселев Антон
 * @version 1.0
 */
public interface ClassFinder {

    /**
     * Поиск всех компонентов
     * @param componentPath передаваемый путь, все классы которого будут отсканированы
     */
    List<Class<?>> findClasses(String componentPath);

    /**
     * Получение всех отсканированных классов
     */
    List<Class<?>> getClasses();

    /**
     * Конвертирует путь к классу в сам класс
     * @param componentPath путь классу
     */
    default Class<?> pathToClass(String componentPath){
        try {
            return Class.forName(componentPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Объединяет пути
     * @param rootPath исходный путь
     * @param appendableObject объект, который необходимо добавить к пути. Это может быть как пакет, так и класс
     * @return возвращается объединенный путь
     */
    default String pathMerging(String rootPath, String appendableObject){
        return rootPath + "." + appendableObject;
    }

    /**
     * Объединяет и редактирует путь к классу
     * @param rootPath исходный путь
     * @param appendableObject объект, который необходимо добавить к пути. Это может быть как пакет, так и класс
     * @return возвращает объединенный путь и убирает окончание .class
     */
    default String mergeClassPath(String rootPath, String appendableObject){
        return pathMerging(rootPath, appendableObject).replaceAll("\\.class", "");
    }

}
