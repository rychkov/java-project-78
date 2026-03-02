package hexlet.code.schemas;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> checks = new HashMap<>();
    private boolean required = false;

    /**
     * Set required flag.
     * @param value flag
     */
    protected void setRequired(boolean value) {
        required = value;
    }

    /**
     * @return required flag
     */
    protected boolean isRequired() {
        return required;
    }

    /**
     * @return unmodifiable checks
     */
    protected Map<String, Predicate<T>> getChecks() {
        return Collections.unmodifiableMap(checks);
    }

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
