package by.smertex.writer.interfaces;

import java.util.Map;

/**
 * Абстракция для менеджера управления моделями
 * @author Киселев Антон
 * @version 1.0
 */
public interface ModelManager {

    /**
     * Возвращает все модели, то есть классы помеченные аннотацией @Csv, в виде ассоциативного массива
     */
    Map<Class<?>, ModelMetadata> getModels();

    /**
     * Возвращает метаданные модели
     */
    ModelMetadata getModelMetadata(Class<?> model);

    /**
     * Проверяет, существует ли модель у объекта
     */
    boolean modelExists(Object model);
}
