package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class SoundChange {
	private String fromSound;
	private String toSound;

	private static SoundChange getFromItem(Map<String, AttributeValue> item) {
	    return SoundChange.builder()
                .fromSound(TypeUtils.getStringFromItem(item.get("fromSound")))
                .toSound(TypeUtils.getStringFromItem(item.get("toSound")))
                .build();
    }

    public static List<SoundChange> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}