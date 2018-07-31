package model.evolution;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
@Builder
public class EvolutionStep {
    private Integer date;
    private String description;
    private List<Transformation> transformations;

    private static EvolutionStep getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("date", "description", "transformations"),
                Arrays.asList(NUMBER, STRING, LIST),
                location, item);
        String locationWithDate = location + ", dated " + item.get("date").getN();
        return EvolutionStep.builder()
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .description(TypeUtils.getStringFromItem(item.get("description")))
                .transformations(Transformation.getListFromItemList(item.get("transformations").getL(), locationWithDate + ": transformation"))
                .build();
    }

    public static List<EvolutionStep> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}