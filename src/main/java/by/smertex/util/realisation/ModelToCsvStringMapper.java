package by.smertex.util.realisation;

import by.smertex.exception.util.ModelToStringMapException;
import by.smertex.util.interfaces.Mapper;
import by.smertex.writer.interfaces.ModelManager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Базовая реализация маппера для конвертации модели в строку
 * @author Киселев Антон
 * @version 1.0
 */
public class ModelToCsvStringMapper implements Mapper<Object, String> {

    /**
     * Менеджер модели
     */
    private final ModelManager modelManager;

    /**
     * Метод для конвертации объекта с типом Object в тип String
     */
    @Override
    public String map(Object from) {
        return generateStringForInputCsv(from);
    }

    /**
     * Генерирует модель в строку, подходяющую для сохранения в формате .csv
     * @param data экземпляр модели
     * @return строка для записи в .csv
     */
    private String generateStringForInputCsv(Object data){
       return modelManager.getModelMetadata(data.getClass())
                .getFields().stream()
                .map(field -> fieldToString(data, field))
                .collect(Collectors.joining(","));
    }

    /**
     * Конвертирует поле в строку
     * @param data - экземпляр модели
     * @param field - поле модели
     */
    private String fieldToString(Object data, Field field){
        try {
            Object object = field.get(data);
            if(object == null)
                return "";
            if(modelManager.getModelMetadata(field.getType()) != null)
                return generateStringForInputCsv(object);
            if(object instanceof List<?> list)
                return listToString(list);
            return object.toString();
        } catch (ClassCastException | IllegalAccessException e) {
            throw new ModelToStringMapException(e.getMessage());
        }
    }

    /**
     * Конвертирует элементы списка в одну строку
     */
    private String listToString(List<?> list){
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    public ModelToCsvStringMapper(ModelManager modelManager) {
        this.modelManager = modelManager;
    }
}
