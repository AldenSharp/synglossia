package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

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

    public static EmptySyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return EmptySyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM()))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}