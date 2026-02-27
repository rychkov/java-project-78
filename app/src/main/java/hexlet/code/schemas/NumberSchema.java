package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Number> {
    private boolean checkForPositive;
    private Number from;
    private Number to;

    /**
     * Add check for positive number.
     * @return NumberSchema
     */
    public NumberSchema positive() {
        checkForPositive = true;
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
        this.from = lowerBound;
        this.to = upperBound;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Number data) {
        if (isRequired() && data == null) {
            return false;
        }
        if (!isRequired() && data == null) {
            return true;
        }

        if (checkForPositive && data.doubleValue() <= 0) {
            return false;
        }

        if (from != null && to != null
            && (data.doubleValue() < from.doubleValue()
                || to.doubleValue() < data.doubleValue())
        ) {
            return false;
        }
        return true;
    }
}
