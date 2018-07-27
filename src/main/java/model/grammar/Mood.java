package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Mood {
    INDICATIVE("INDICATIVE"), SUBJUNCTIVE("SUBJUNCTIVE"), IMPERATIVE("IMPERATIVE"), JUSSIVE("JUSSIVE"), ALLATIVE("ALLATIVE");

    private final String value;
    Mood(String value) { this.value = value; }

    public static Mood getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<Mood> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Mood::getFromItem)
                .collect(Collectors.toList());
    }
}
