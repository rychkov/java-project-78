package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Number> {
    /**
     * Make data required.
     * @return NumberSchema
     */
    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    /**
     * Add check for positive number.
     * @return NumberSchema
     */
    public NumberSchema positive() {
        addCheck("positive", n -> n.doubleValue() > 0);
        return this;
    }

    /**
     * Add range [from .. to].
     * @param lowerBound from number
     * @param upperBound to number
     * @return NumberSchema
     */
    public NumberSchema range(Number lowerBound, Number upperBound) {
        Objects.requireNonNull(lowerBound, "lowerBound can't be null");
        Objects.requireNonNull(upperBound, "upperBound can't be null");
        if (lowerBound.doubleValue() > upperBound.doubleValue()) {
            throw new IllegalArgumentException("lowerBound should be less or equal to upperBound");
        }
        addCheck("range", n -> lowerBound.doubleValue() <= n.doubleValue()
                                                   && n.doubleValue() <= upperBound.doubleValue());
        return this;
    }
}
