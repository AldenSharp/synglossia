package model.writingSystem;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.condition.Condition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@Data
public class AlphabetRuleGrapheme {
	private String value;
	private Condition condition;

	public static AlphabetRuleGrapheme getFromItem(Map<String, AttributeValue> item) {
	    return AlphabetRuleGrapheme.builder()
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .condition(Condition.getFromItem(item.get("condition").getM()))
                .build();
    }

    public static List<AlphabetRuleGrapheme> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}