package model.writingSystem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
@Builder
public class AlphabetRule {
	List<String> sounds;
	List<AlphabetRuleGrapheme> graphemes;

	public static AlphabetRule getFromItem(Map<String, AttributeValue> item) {
	    return AlphabetRule.builder()
                .sounds(TypeUtils.getStringListFromItemList(item.get("sounds").getL()))
                .graphemes(AlphabetRuleGrapheme.getListFromItemList(item.get("graphemes").getL()))
                .build();
    }

    public static List<AlphabetRule> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}