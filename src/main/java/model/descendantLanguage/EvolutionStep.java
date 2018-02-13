package model.descendantLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.NUMBER;
import static util.FieldType.OBJECT;

@Data
@Builder
public class EvolutionStep {
	private Integer date;
	private List<Transformation> transformations;

	private static EvolutionStep getFromItem(Map<String, AttributeValue> item, String location) {
		ExceptionUtils.checkObjectElements(
		        Arrays.asList("date", "transformations"),
                Arrays.asList(NUMBER, LIST),
                location, item);
		String locationWithDate = location + ", dated " + item.get("date").getN();
	    return EvolutionStep.builder()
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
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