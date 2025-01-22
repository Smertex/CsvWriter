package by.smertex.writer.interfaces;

/**
 * Абстракция для записи информации в файл
 * @author Киселев Антон
 * @version 1.0
 */
public interface CsvWriter {

    /**
     * Записывает строку в файл
     * @param data строка, которая будет записана
     * @param filename путь к файлу, в которо будет записана строка
     */
    void write(String data, String filename);
}
