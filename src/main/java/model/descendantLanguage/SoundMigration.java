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
public class SoundMigration {
	private Integer fromPosition;
	private Integer syllableShift;
	private Integer toPosition;

	public static SoundMigration getFromItem(Map<String, AttributeValue> item) {
	    return SoundMigration.builder()
                .fromPosition(TypeUtils.getIntegerFromItem(item.get("fromPosition")))
                .syllableShift(TypeUtils.getIntegerFromItem(item.get("syllableShift")))
                .toPosition(TypeUtils.getIntegerFromItem(item.get("toPosition")))
                .build();
    }

    public static List<SoundMigration> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}