package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Gender {
    MASCULINE("MASCULINE"), FEMININE("FEMININE"), NEUTER("NEUTER");

    private final String value;
    Gender(String value) {
        this.value = value;
    }

    public static Gender getFromItem(AttributeValue item) {
        return valueOf(TypeUtils.getStringFromItem(item));
    }

    public static List<Gender> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Gender::getFromItem)
                .collect(Collectors.toList());
    }
}
