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

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class SoundChange {
	private String fromSound;
	private String toSound;

	private static SoundChange getFromItem(Map<String, AttributeValue> item, String location) {
		ExceptionUtils.checkObjectElements(
		        Arrays.asList("fromSound", "toSound"),
                Arrays.asList(STRING, STRING),
                location, item);
	    return SoundChange.builder()
                .fromSound(TypeUtils.getStringFromItem(item.get("fromSound")))
                .toSound(TypeUtils.getStringFromItem(item.get("toSound")))
                .build();
    }

    public static List<SoundChange> getListFromItemList(List<AttributeValue> itemList, String location) {
		ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}