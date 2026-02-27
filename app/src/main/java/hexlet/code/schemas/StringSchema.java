package hexlet.code.schemas;

public final class StringSchema {

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
     * Checks whether the data meets all requirements.
     * @param data data for validation
     * @return {@code true} if data is valid, {@code false} otherwise
     */
    public boolean isValid(String data) {
        if (isRequired && (data == null || data.isEmpty())) {
            return false;
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
