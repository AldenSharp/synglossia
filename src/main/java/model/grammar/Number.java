package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Number {
    SINGULAR("SINGULAR"), DUAL("DUAL"), PLURAL("PLURAL");

    private final String value;
    Number(String value) {
        this.value = value;
    }

    public static Number getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }

    public static List<Number> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Number::getFromItem)
                .collect(Collectors.toList());
    }
}
