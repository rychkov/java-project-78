package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private int size = -1;

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
        return true;
    }
}
