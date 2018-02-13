package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class PhonotacticsPosition {
    private String value;

    public static PhonotacticsPosition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("value"), Collections.singletonList(STRING), location + ": value", item);
        return PhonotacticsPosition.builder()
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .build();
    }

    private static List<PhonotacticsPosition> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }

    static List<List<PhonotacticsPosition>> getListListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, LIST);
        return itemList.stream()
                .map(item -> getListFromItemList(item.getL(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}