package cl.atromilen.spring.kafka.producer.utils;

public class Utils {

    private Utils() {
    }

    /**
     * Utility used to transform the attributes classes written in camelCase to snake_case.
     * This is for consistency with the SnakeCaseStrategies (jackson) used to represent i/o fields in REST APIs.
     * @param fieldName
     * @return
     */
    public static String convertCamelCaseToSnakeCase(String fieldName){
        String newFieldName = fieldName
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2");

        return newFieldName.toLowerCase();
    }

}
