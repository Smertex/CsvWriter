package by.smertex.exception.writer;

public class DataValidationException extends RuntimeException {

    private static final String VALIDATE_EXCEPTION = "Trying to save a non-CSV object. Problem class: %s";

    public DataValidationException(Class<?> clazz) {
        super(VALIDATE_EXCEPTION.formatted(clazz));
    }
}
