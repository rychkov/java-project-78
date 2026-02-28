package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private int size = -1;
    private Map<String, BaseSchema<String>> shape;

    /**
     * Set map size.
     * @param n number of keys
     * @return MapSchema
     */
    public MapSchema sizeof(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be >= 0");
        }
        size = n;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Map data) {
        if (isRequired() && data == null) {
            return false;
        }
        if (!isRequired() && data == null) {
            return true;
        }

        if (size >= 0 && data.size() != size) {
            return false;
        }

        if (shape != null) {
            for (var entry : shape.entrySet()) {
                var key = entry.getKey();
                var validator = entry.getValue();
                if (!validator.isValid((String) data.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Set shape validation.
     * @param data validation map
     * @return MapSchema
     */
    public MapSchema shape(Map<String, BaseSchema<String>> data) {
        shape = data;
        return this;
    }
}
