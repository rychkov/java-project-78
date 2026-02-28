package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean isRequired;

    /**
     * Make data required.
     * @return NumberSchema
     */
    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    final boolean isRequired() {
        return isRequired;
    }

    /**
     * Checks whether the data meets all requirements.
     * @param data data for validation
     * @return {@code true} if data is valid, {@code false} otherwise
     */
    public abstract boolean isValid(T data);
}
