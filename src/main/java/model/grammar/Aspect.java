package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum Aspect {
    IMPERFECTIVE("IMPERFECTIVE"), PERFECTIVE("PERFECTIVE");

    private final String value;
    Aspect(String value) { this.value = value; }

    public static Aspect getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<Aspect> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(Aspect::getFromItem)
                .collect(Collectors.toList());
    }
}
