package by.smertex.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, которой помечаются модели
 * @author Киселев Антон
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Csv {

    /**
     * Исключение полей из процесса записи в .csv файл
     */
    String[] exclude() default {};
}
