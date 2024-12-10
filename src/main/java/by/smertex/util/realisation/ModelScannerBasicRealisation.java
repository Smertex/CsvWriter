package by.smertex.util.realisation;

import by.smertex.annotation.Csv;
import by.smertex.util.interfaces.ClassFinder;
import by.smertex.writer.interfaces.ModelMetadata;
import by.smertex.util.interfaces.ModelScanner;
import by.smertex.writer.realisation.ModelMetadataBasicRealisation;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Базовая реализация для сканирования моделей
 * @author Киселев Антон
 * @version 1.0
 */
public class ModelScannerBasicRealisation implements ModelScanner {

    /**
     * Поисковик классов
     */
    private final ClassFinder classFinder;

    /**
     * Отсеивает классы, найденные classFinder, которые не являются моделями
     * @return возвращает ассоциативный массив, где ключ - это модель, а значение - метаданные модели
     */
    @Override
    public Map<Class<?>, ModelMetadata> getModels() {
        return classFinder.getClasses().stream()
                .filter(clazz -> clazz.isAnnotationPresent(Csv.class))
                .collect(Collectors
                        .toMap(clazz -> clazz,
                                ModelMetadataBasicRealisation::new)
                );
    }

    public ModelScannerBasicRealisation(String modelPackage) {
        classFinder = new ClassFinderBasicRealisation(modelPackage);
    }
}
