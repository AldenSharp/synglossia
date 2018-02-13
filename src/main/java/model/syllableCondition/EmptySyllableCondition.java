package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
public class EmptySyllableCondition extends SyllableCondition {
	private SoundPosition position;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public EmptySyllableCondition(SoundPosition position, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.EMPTY);
        this.position = position;
        this.syllablePositionType = syllablePositionType;
    }

    public static class EmptySyllableConditionBuilder extends SyllableConditionBuilder {
    	EmptySyllableConditionBuilder() {
                super();
            }
    }

    public static EmptySyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "syllablePositionType"),
                Arrays.asList(OBJECT, STRING),
                location, item);
	    return EmptySyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM(), location + ": position object"))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}