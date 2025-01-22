package by.smertex.writer.interfaces;

import java.util.List;

/**
 * Абстракция для записи моделей в .csv
 */
public interface Writable {

    /**
     * Записывает передаваемые модели в нужный файл
     * @param data список моделей, которые необходимо занести в .csv
     * @param fileName путь к файлу
     */
    void writeToFile(List<?> data, String fileName);

}
