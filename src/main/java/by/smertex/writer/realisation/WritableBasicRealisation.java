package by.smertex.writer.realisation;

import by.smertex.exception.writer.DataValidationException;
import by.smertex.util.interfaces.Mapper;
import by.smertex.util.realisation.ModelToCsvStringMapper;
import by.smertex.writer.interfaces.ModelManager;
import by.smertex.writer.interfaces.CsvWriter;
import by.smertex.writer.interfaces.Writable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Базовая реализация записи моделей в .csv
 */
public class WritableBasicRealisation implements Writable {

    /**
     * Менеджер моделей
     */
    private final ModelManager modelManager;

    /**
     * Маппер для моделей
     */
    private final Mapper<Object, String> mapper;

    /**
     * Класс для записи моделей в файл
     */
    private final CsvWriter csvWriter;

    /**
     * Записывает передаваемые модели в нужный файл, а также валидирует данные
     * @param data список моделей, которые необходимо занести в .csv
     * @param fileName путь к файлу
     */
    @Override
    public void writeToFile(List<?> data, String fileName) {
        String allRecords = data.stream()
                .peek(this::validate)
                .map(mapper::map)
                .collect(Collectors.joining("\n"));
        csvWriter.write(allRecords, fileName);
    }
    /**
     * Метод валидации
     * @param data валидируемая модель
     */
    private void validate(Object data) {
        if(!modelManager.modelExists(data))
            throw new DataValidationException(data.getClass());
    }

    public WritableBasicRealisation(String modelPath) {
        this.modelManager = new ModelManagerBasicRealisation(modelPath);
        this.csvWriter = new CsvWriterBasicRealisation();
        this.mapper = new ModelToCsvStringMapper(modelManager);
    }
}
