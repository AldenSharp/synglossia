package model.descendantLanguage;

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
public class SoundMigration {
	private Integer fromPosition;
	private Integer syllableShift;
	private Integer toPosition;

	public static SoundMigration getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("fromPosition", "syllableShift", "toPosition"),
                Arrays.asList(NUMBER, NUMBER, NUMBER),
                location, item);
	    return SoundMigration.builder()
                .fromPosition(TypeUtils.getIntegerFromItem(item.get("fromPosition")))
                .syllableShift(TypeUtils.getIntegerFromItem(item.get("syllableShift")))
                .toPosition(TypeUtils.getIntegerFromItem(item.get("toPosition")))
                .build();
    }

    public static List<SoundMigration> getListFromItemList(List<AttributeValue> itemList, String location) {
		ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}