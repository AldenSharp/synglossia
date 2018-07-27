package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Tense {
    COMMON("COMMON"), PRESENT("PRESENT"), PAST("PAST"), FUTURE("FUTURE");

    private final String value;
    Tense(String value) { this.value = value; }

    public static Tense getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<Tense> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Tense::getFromItem)
                .collect(Collectors.toList());
    }
}
