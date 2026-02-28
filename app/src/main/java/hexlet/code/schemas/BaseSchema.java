package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    /**
     * Checks whether the data meets all requirements.
     * @param data data for validation
     * @return {@code true} if data is valid, {@code false} otherwise
     */
    public abstract boolean isValid(T data);
}
