package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class StressParadigmPosition {
	private Integer value;
	private SyllableCondition condition;

	public static StressParadigmPosition getFromItem(Map<String, AttributeValue> item) {
	    return StressParadigmPosition.builder()
                .value(TypeUtils.getIntegerFromItem(item.get("value")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }

    public static List<StressParadigmPosition> getListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}