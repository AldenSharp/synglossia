package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.FieldType;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.NUMBER;
import static util.FieldType.OBJECT;

@Data
@Builder
public class StressParadigmPosition {
	private Integer value;
	private SyllableCondition condition;

	private static StressParadigmPosition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("value", "condition"),
                Arrays.asList(NUMBER, OBJECT),
                location, item);
	    return StressParadigmPosition.builder()
                .value(TypeUtils.getIntegerFromItem(item.get("value")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": paradigm condition"))
                .build();
    }

    public static List<StressParadigmPosition> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}