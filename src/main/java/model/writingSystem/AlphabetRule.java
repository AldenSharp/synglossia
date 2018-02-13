package model.writingSystem;

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
import static util.FieldType.OBJECT;

@Data
@Builder
public class AlphabetRule {
	List<String> sounds;
	List<AlphabetRuleGrapheme> graphemes;

	public static AlphabetRule getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
        		Arrays.asList("sounds", "graphemes"),
                Arrays.asList(LIST, LIST),
                location, item);
	    return AlphabetRule.builder()
                .sounds(TypeUtils.getStringListFromItemList(item.get("sounds").getL(), ": sound item"))
                .graphemes(AlphabetRuleGrapheme.getListFromItemList(item.get("graphemes").getL(), location + ": grapheme"))
                .build();
    }

    public static List<AlphabetRule> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}