package hexlet.code.schemas;

import java.util.StringJoiner;

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
        System.out.println("schema: " + toString());
        System.out.println("data -> " + data);

        if (isRequired && (data == null || data.isEmpty())) {
            System.out.println("false");
            return false;
        }
        if (!isRequired && data == null) {
            System.out.println("true");
            return true;
        }

        if (checkMinLength) {
            if (data == null) {
                if (minLength != 0) {
                    return false;
                }
            } else {
                if (data.length() < minLength) {
                    return false;
                }
            }
        }

        if (substring != null) {
            if (data == null) {
                System.out.println("false");
                return false;
            } else {
                System.out.println(data.contains(substring));
                return data.contains(substring);
            }
        }
        System.out.println("true");
        return true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StringSchema.class.getSimpleName() + "[", "]")
            .add("isRequired=" + isRequired)
            .add("checkMinLength=" + checkMinLength)
            .add("minLength=" + minLength)
            .add("substring='" + substring + "'")
            .toString();
    }
}
