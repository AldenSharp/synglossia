package util;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.List;
import java.util.stream.Collectors;

public class TypeUtils {
    public static String getStringFromItem(AttributeValue item) {
        String string = item.getS();
        if (string.equals("_")) {
            string = "";
        }
        return string;
    }

    public static Integer getIntegerFromItem(AttributeValue item) {
        return Integer.parseInt(item.getN());
    }

    public static Boolean getBooleanFromItem(AttributeValue item) {
        return item.getBOOL();
    }

    public static List<String> getStringListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(TypeUtils::getStringFromItem)
                .collect(Collectors.toList());
    }

    public static List<Integer> getIntegerListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(TypeUtils::getIntegerFromItem)
                .collect(Collectors.toList());
    }

    public static List<List<String>> getStringListListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getStringListFromItemList(item.getL()))
                .collect(Collectors.toList());
    }
}
