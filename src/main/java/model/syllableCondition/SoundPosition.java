package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.NUMBER;
import static util.FieldType.OBJECT;

@Data
@Builder
public class SoundPosition {
	private Integer syllable;
	private Integer sound;

	public static SoundPosition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllable", key -> new AttributeValue().withN("0"));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("syllable", "sound"),
                Arrays.asList(NUMBER, NUMBER),
                location, item);
	    return SoundPosition.builder()
                .syllable(TypeUtils.getIntegerFromItem(item.get("syllable")))
                .sound(TypeUtils.getIntegerFromItem(item.get("sound")))
                .build();
    }

    public static List<SoundPosition> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}