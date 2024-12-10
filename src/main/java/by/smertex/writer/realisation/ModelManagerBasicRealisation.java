package by.smertex.writer.realisation;

import by.smertex.writer.interfaces.ModelManager;
import by.smertex.writer.interfaces.ModelMetadata;
import by.smertex.util.interfaces.ModelScanner;
import by.smertex.util.realisation.ModelScannerBasicRealisation;

import java.util.HashMap;
import java.util.Map;

/**
 * Базовая реализация менеджера управления моделями
 * @author Киселев Антон
 * @version 1.0
 */
public class ModelManagerBasicRealisation implements ModelManager {

    /**
     * Найденные модели на основе переданного пути
     */
    private final Map<Class<?>, ModelMetadata> models;

    /**
     * Сканнер моделей
     */
    private final ModelScanner modelScanner;

    public ModelManagerBasicRealisation(String modelPackage) {
        models = new HashMap<>();
        modelScanner = new ModelScannerBasicRealisation(modelPackage);
        initModels();
    }

    private void initModels(){
        models.putAll(modelScanner.getModels());
    }

    /**
     * Возвращает все модели, то есть классы помеченные аннотацией @Csv, в виде ассоциативного массива
     */
    @Override
    public Map<Class<?>, ModelMetadata> getModels() {
        return new HashMap<>(models);
    }

    /**
     * Возвращает метаданные модели
     */
    @Override
    public ModelMetadata getModelMetadata(Class<?> model) {
        return models.get(model);
    }

    /**
     * Проверяет, существует ли модель у объекта
     */
    @Override
    public boolean modelExists(Object model) {
        return models.containsKey(model.getClass());
    }
}
