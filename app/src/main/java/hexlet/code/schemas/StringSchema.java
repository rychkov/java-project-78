package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private boolean isRequired;
    private boolean checkMinLength;
    private int minLength;
    private String substring;

    /**
     * Make data required.
     * @return StringSchema
     */
    public StringSchema required() {
        isRequired = true;
        return this;
    }

    /**
     * Set minimun length.
     * @param length minimum length
     * @return StringSchema
     */
    public StringSchema minLength(int length) {
        checkMinLength = true;
        minLength = length;
        return this;
    }

    /**
     * Set contains constraint.
     * @param part substring
     * @return StringSchema
     */
    public StringSchema contains(String part) {
        this.substring = part;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String data) {
        if (isRequired && (data == null || data.isEmpty())) {
            return false;
        }
        if (!isRequired && data == null) {
            return true;
        }

        if (checkMinLength) {
            if (data == null) {
                return minLength == 0;
            } else {
                return data.length() >= minLength;
            }
        }

        if (substring != null) {
            if (data == null) {
                return false;
            } else {
                return data.contains(substring);
            }
        }
        return true;
    }
}
