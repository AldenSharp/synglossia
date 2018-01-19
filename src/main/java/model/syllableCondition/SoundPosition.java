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
public class SoundPosition {
	private Integer syllable;
	private Integer sound;

	public static SoundPosition getFromItem(Map<String, AttributeValue> item) {
	    return SoundPosition.builder()
                .syllable(TypeUtils.getIntegerFromItem(item.get("syllable")))
                .sound(TypeUtils.getIntegerFromItem(item.get("sound")))
                .build();
    }

    public static List<SoundPosition> getListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}