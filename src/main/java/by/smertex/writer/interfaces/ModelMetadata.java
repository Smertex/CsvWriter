package by.smertex.writer.interfaces;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Абстракция для метаданных модели, или же объекта, который помечен аннотацией @Csv и может быть сохранен в .csv файл
 * @author Киселев Антон
 * @version 1.0
 */
public interface ModelMetadata {

    /**
     * Получение всех полей модели
     */
    List<Field> getFields();
}
