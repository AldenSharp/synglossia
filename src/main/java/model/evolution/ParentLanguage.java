package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class ParentLanguage {
    private String name;
    private List<EvolutionStep> evolution;

    private static ParentLanguage getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("name", "evolution"),
                Arrays.asList(STRING, LIST),
                "Parent language", item);
        String locationWithName = location + ", name '" + item.get("name").getS() + "'";
        return ParentLanguage.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
                .evolution(EvolutionStep.getListFromItemList(item.get("evolution").getL(), locationWithName + " evolution step"))
                .build();
    }

    public static List<ParentLanguage> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
