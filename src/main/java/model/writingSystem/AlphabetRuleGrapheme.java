package model.writingSystem;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.condition.Condition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Builder
@Data
public class AlphabetRuleGrapheme {
	private String value;
	private Condition condition;

	private static AlphabetRuleGrapheme getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
        		Arrays.asList("value", "condition"),
                Arrays.asList(STRING, OBJECT),
                location, item);
	    return AlphabetRuleGrapheme.builder()
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .condition(Condition.getFromItem(item.get("condition").getM(), location + ": grapheme condition"))
                .build();
    }

    public static List<AlphabetRuleGrapheme> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}