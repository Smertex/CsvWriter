package by.smertex.writer.realisation;

import by.smertex.exception.writer.CsvWriteException;
import by.smertex.writer.interfaces.CsvWriter;

import java.io.FileWriter;
import java.io.Writer;

/**
 * Базовая реализация класса для записи информации в файл
 * @author Киселев Антон
 * @version 1.0
 */
public class CsvWriterBasicRealisation implements CsvWriter {

    /**
     * Записывает строку в файл
     * @param data строка, которая будет записана
     * @param filename путь к файлу, в которо будет записана строка
     */
    @Override
    public void write(String data, String filename) {
        try(Writer writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (Exception e) {
            throw new CsvWriteException(e.getMessage());
        }
    }
}
