package hexlet.code.schemas;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import hexlet.code.Validator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MapSchemaTest {
    @Test
    void testMapSchemaDeep() {
        var v = new Validator();
        var schema = new MapSchema();

        // Проверка размера
        schema.sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        // Shape validation
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.string().minLength(3));
        schema.shape(schemas);

        Map<String, String> actual1 = new HashMap<>();
        actual1.put("name", "Maya");
        actual1.put("age", "18+");
        assertTrue(schema.isValid(actual1));

        Map<String, String> actual2 = new HashMap<>();
        actual2.put("name", ""); // Ошибка: required в StringSchema запрещает пустую строку
        actual2.put("age", "18+");
        assertFalse(schema.isValid(actual2));
    }

    @Test
    void testCase4() {
        var schema = new MapSchema();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }

    @Test
    void tastCase5() {
        var v = new Validator();

        var schema = v.map();

        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", v.string().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", v.string().required().minLength(2));

        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schema.shape(schemas);

        // Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false
    }
}
