package model.descendantLanguage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
@Builder
public class EvolutionStep {
	private Integer date;
	private List<Transformation> transformations;

	private static EvolutionStep getFromItem(Map<String, AttributeValue> item) {
	    return EvolutionStep.builder()
                .date(TypeUtils.getIntegerFromItem(item.get("date")))
                .transformations(Transformation.getListFromItemList(item.get("transformations").getL()))
                .build();
    }

    public static List<EvolutionStep> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}