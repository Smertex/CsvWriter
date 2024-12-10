package by.smertex.util.interfaces;

import by.smertex.writer.interfaces.ModelMetadata;

import java.util.Map;

/**
 * Абстракция для сканирования моделей
 * @author Киселев Антон
 * @version 1.0
 */
public interface ModelScanner {

    /**
     * Находит все модели, то есть классы помеченные аннотацией @Csv, в виде ассоциативного массива
     */
    Map<Class<?>, ModelMetadata> getModels();
}
