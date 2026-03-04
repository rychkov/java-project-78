package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    /**
     * Make data required.
     * @return MapSchema
     */
    public MapSchema required() {
        setRequired(true);
        return this;
    }

    /**
     * Set map size.
     * @param n number of keys
     * @return MapSchema
     */
    public MapSchema sizeof(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be >= 0");
        }
        addCheck("sizeof", map -> map.size() == n);
        return this;
    }

    /**
     * Set shape validation.
     * @param shape validation map
     * @return MapSchema
     */
    public MapSchema shape(Map<String, BaseSchema<String>> shape) {
        addCheck("shape", map -> {
            for (var entry : shape.entrySet()) {
                var key = entry.getKey();
                var validator = entry.getValue();
                if (!validator.isValid((String) map.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
