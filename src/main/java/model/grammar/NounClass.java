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
public class NounClass {
    private String name;
    private List<Gender> genders;
    private Integer endingStartPosition;
    private ClassType type;

    public static NounClass getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "genders", "endingStartPosition", "type"),
                Arrays.asList(STRING, LIST, NUMBER, STRING),
                location, item
        );
        String locationWithType = location + " of type " + item.get("type").getS();
        switch (ClassType.getFromItem(item.get("type"))) {
            case DEFAULT:
                return NounClass.builder()
                        .name(TypeUtils.getStringFromItem(item.get("name")))
                        .genders(Gender.getListFromItemList(item.get("genders").getL(), location + ": gender"))
                        .endingStartPosition(TypeUtils.getIntegerFromItem(item.get("endingStartPosition")))
                        .type(ClassType.getFromItem(item.get("type")))
                        .build();
            case VARIANT:
                return VariantNounClass.getFromItem(item, locationWithType);
        }
        return NounClass.builder().build();
    }

    public static List<NounClass> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(),location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
