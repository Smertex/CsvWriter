package by.smertex.annotation;

import java.lang.annotation.*;

/**
 * Аннотация, которой помечаются модели
 * @author Киселев Антон
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Csv {

    /**
     * Исключение полей из процесса записи в .csv файл
     */
    String[] exclude() default {};
}
