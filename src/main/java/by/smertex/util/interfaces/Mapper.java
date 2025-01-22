package by.smertex.util.interfaces;

/**
 * Абстракция для конвертации объектов
 * @author Киселев Антон
 * @version 1.0
 */
public interface Mapper <F, T>{

    /**
     * Метод для конвертации объекта с типом F в тип T
     * @param from конвертируемый объект
     */
    T map(F from);
}
