package model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class PhonotacticsPosition {
    private String value;

    public static PhonotacticsPosition getFromItem(Map<String, AttributeValue> item) {
        return PhonotacticsPosition.builder()
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .build();
    }

    private static List<PhonotacticsPosition> getListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }

    public static List<List<PhonotacticsPosition>> getListListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getListFromItemList(item.getL()))
                .collect(Collectors.toList());
    }
}