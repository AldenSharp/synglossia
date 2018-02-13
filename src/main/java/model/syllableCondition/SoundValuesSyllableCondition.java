package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
public class SoundValuesSyllableCondition extends SyllableCondition {
	private SoundPosition position;
	private List<String> values;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public SoundValuesSyllableCondition(SoundPosition position, List<String> values, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.SOUND_VALUES);
        this.position = position;
        this.values = values;
        this.syllablePositionType = syllablePositionType;
    }

    public static class SoundValuesSyllableConditionBuilder extends SyllableConditionBuilder {
    	SoundValuesSyllableConditionBuilder() {
                super();
            }
    }

    public static SoundValuesSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "values", "syllablePositionType"),
                Arrays.asList(OBJECT, LIST, STRING),
                location, item);
        return SoundValuesSyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM(), location + ": position object"))
                .values(TypeUtils.getStringListFromItemList(item.get("values").getL(), location + ": value item"))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}