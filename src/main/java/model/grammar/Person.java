package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Person {
    FIRST("FIRST"), SECOND("SECOND"), THIRD("THIRD");

    private final String value;
    Person(String value) { this.value = value; }

    public static Person getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<Person> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Person::getFromItem)
                .collect(Collectors.toList());
    }
}
