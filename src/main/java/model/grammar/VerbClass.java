package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.*;

@Data
@Builder
public class VerbClass {
    private String name;
    private Integer endingStartPosition;
    private ClassType type;

    public static VerbClass getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "endingStartPosition", "type"),
                Arrays.asList(STRING, NUMBER, STRING),
                location, item
        );
        String locationWithType = location + " of type " + item.get("type").getS();
        switch (ClassType.getFromItem(item.get("type"))) {
            case DEFAULT:
                return VerbClass.builder()
                        .name(TypeUtils.getStringFromItem(item.get("name")))
                        .endingStartPosition(TypeUtils.getIntegerFromItem(item.get("endingStartPosition")))
                        .type(ClassType.getFromItem(item.get("type")))
                        .build();
            case VARIANT:
                return VariantVerbClass.getFromItem(item, locationWithType);
        }
        return VerbClass.builder().build();
    }

    public static List<VerbClass> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(),location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
