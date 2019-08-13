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

import static util.FieldType.*;

@Data
@Builder
public class Evolution {
    private String parentLanguage;
    private String descendantLanguage;
    private List<EvolutionStep> evolution;

    public static Evolution getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("parentLanguage", "descendantLanguage", "evolution"),
                Arrays.asList(STRING, STRING, LIST),
                location, item);
        return Evolution.builder()
                .parentLanguage(TypeUtils.getStringFromItem(item.get("parentLanguage")))
                .descendantLanguage(TypeUtils.getStringFromItem(item.get("descendantLanguage")))
                .evolution(EvolutionStep.getListFromItemList(item.get("evolution").getL(), location + " evolution"))
                .build();
    }

    public static List<Evolution> getListFromItemList(List<Map<String, AttributeValue>> itemList, String location) {
        return itemList.stream()
                .map(item -> getFromItem(item, location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}
