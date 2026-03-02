package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    /**
     * Make data required.
     * @return StringSchema
     */
    public StringSchema required() {
        setRequired(true);
        return this;
    }

    /**
     * Set minimum length.
     * @param length minimum length
     * @return StringSchema
     */
    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s.length() >= length);
        return this;
    }

    /**
     * Set contains constraint.
     * @param part substring
     * @return StringSchema
     */
    public StringSchema contains(String part) {
        addCheck("contains", s -> s.contains(part));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(String data) {
        if (isRequired() && (data == null || data.isEmpty())) {
            return false;
        }
        if (!isRequired() && data == null) {
            return true;
        }

        for (var p: getChecks().values()) {
            if (!p.test(data)) {
                return false;
            }
        }
        return true;
    }
}
