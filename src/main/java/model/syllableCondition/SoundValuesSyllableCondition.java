package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoundValuesSyllableCondition extends SyllableCondition {
	private SoundPosition position;
	private List<String> values;
	private Boolean syllablePositionAbsolute;
	
	@Builder
	public SoundValuesSyllableCondition(SoundPosition position, List<String> values, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.SOUND_VALUES);
        this.position = position;
        this.values = values;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class SoundValuesSyllableConditionBuilder extends SyllableConditionBuilder {
    	SoundValuesSyllableConditionBuilder() { super(); }
    }

    public static SoundValuesSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "values", "syllablePositionAbsolute"),
                Arrays.asList(OBJECT, LIST, BOOLEAN),
                location, item);
        return SoundValuesSyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM(), location + ": position object"))
                .values(TypeUtils.getStringListFromItemList(item.get("values").getL(), location + ": value item"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}