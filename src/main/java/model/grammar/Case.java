package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Case {
    NOMINATIVE("NOMINATIVE"),
    VOCATIVE("VOCATIVE"),
    ACCUSATIVE("ACCUSATIVE"),
    GENITIVE("GENITIVE"),
    DATIVE("DATIVE"),
    ABLATIVE("ABLATIVE"),
    LOCATIVE("LOCATIVE"),
    INSTRUMENTAL("INSTRUMENTAL");

    private final String value;
    Case(String value) { this.value = value; }

    public static Case getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<Case> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Case::getFromItem)
                .collect(Collectors.toList());
    }
}
