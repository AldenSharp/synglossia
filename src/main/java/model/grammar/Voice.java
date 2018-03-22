package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Voice {
    ACTIVE("ACTIVE"), MIDDLE("MIDDLE"), PASSIVE("PASSIVE"), MEDIOPASSIVE("MEDIOPASSIVE");

    private final String value;
    Voice(String value) {
        this.value = value;
    }

    public static Voice getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }

    public static List<Voice> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Voice::getFromItem)
                .collect(Collectors.toList());
    }
}
