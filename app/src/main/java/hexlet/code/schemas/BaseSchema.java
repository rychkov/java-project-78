package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new HashMap<>();
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    /**
     * Checks whether the data meets all requirements.
     * @param data data for validation
     * @return {@code true} if data is valid, {@code false} otherwise
     */
    public abstract boolean isValid(T data);
}
