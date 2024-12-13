package by.smertex.writer.realisation;

import by.smertex.annotation.Csv;
import by.smertex.writer.interfaces.ModelMetadata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Базовая реализация метаданных модели, или же объекта, который помечен аннотацией @Csv и может быть сохранен в .csv файл
 * @author Киселев Антон
 * @version 1.0
 */
public class ModelMetadataBasicRealisation implements ModelMetadata {

    /**
     * Поля модели
     */
    private final List<Field> fields;

    /**
     * Получение всех полей модели
     */
    @Override
    public List<Field> getFields() {
        return fields;
    }

    public ModelMetadataBasicRealisation(Class<?> clazz) {
        this.fields = new ArrayList<>();
        initFields(clazz);
    }

    /**
     * Заполняет fields полями модели, исключая те, которые были добавлены в exclude
     * @param clazz класс модели
     */
    private void initFields(Class<?> clazz){
        List<Field> modelFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> Arrays.stream(clazz.getAnnotation(Csv.class).exclude())
                        .noneMatch(fieldName -> fieldName.equals(field.getName()))
                )
                .peek(field -> field.setAccessible(true))
                .toList();
        fields.addAll(modelFields);
    }
}
