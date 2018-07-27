package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.OBJECT;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmptySyllableCondition extends SyllableCondition {
	private SoundPosition position;
    private Boolean syllablePositionAbsolute;
	
	@Builder
	public EmptySyllableCondition(SoundPosition position, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.EMPTY);
        this.position = position;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class EmptySyllableConditionBuilder extends SyllableConditionBuilder {
    	EmptySyllableConditionBuilder() { super(); }
    }

    public static EmptySyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "syllablePositionAbsolute"),
                Arrays.asList(OBJECT, BOOLEAN),
                location, item);
	    return EmptySyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM(), location + ": position object"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}