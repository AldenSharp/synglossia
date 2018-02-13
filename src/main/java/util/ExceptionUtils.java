package util;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.List;
import java.util.Map;

public class ExceptionUtils {
    public static void checkObjectElements(List<String> fields, List<FieldType> types, String location, Map<String, AttributeValue> object) {
        for (String field : fields) {
            if (object.get(field) == null) {
                throw new NullPointerException(location + " has missing '" + field + "' property.");
            }
            checkType(object.get(field), types.get(fields.indexOf(field)), location + " field '" + field + "'");
        }
    }

    public static void checkListElements(List<AttributeValue> list, String location, FieldType elementType) {
        for (AttributeValue item : list) {
            checkType(item, elementType, location + " at position " + list.indexOf(item));
        }
    }

    private static void checkType(AttributeValue item, FieldType type, String location) {
        switch (type) {
            case STRING:
                if (item.getS() == null) {
                    throw new NullPointerException(location + " is not of string type.");
                }
                break;
            case NUMBER:
                if (item.getN() == null) {
                    throw new NullPointerException(location + " is not of number type.");
                }
                break;
            case BOOLEAN:
                if (item.getBOOL() == null) {
                    throw new NullPointerException(location + " is not of Boolean type.");
                }
                break;
            case LIST:
                if (item.getL() == null) {
                    throw new NullPointerException(location + " is not of list type.");
                }
                break;
            case OBJECT:
                if (item.getM() == null) {
                    throw new NullPointerException(location + " is not of object type.");
                }
        }
    }
}
