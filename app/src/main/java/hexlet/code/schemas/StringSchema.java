package hexlet.code.schemas;

public class StringSchema {

    private boolean isRequired;
    private boolean checkMinLength;
    private int minLength;
    private String substring;

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        checkMinLength = true;
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.substring = substring;
        return this;
    }

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
