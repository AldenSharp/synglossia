package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
public class SoundArrayMatchSyllableCondition extends SyllableCondition {
	private SoundPosition initialPosition;
	private List<String> array;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public SoundArrayMatchSyllableCondition(SoundPosition initialPosition, List<String> array, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.SOUND_ARRAY_MATCH);
        this.initialPosition = initialPosition;
        this.array = array;
        this.syllablePositionType = syllablePositionType;
    }

    public static class SoundArrayMatchSyllableConditionBuilder extends SyllableConditionBuilder {
    	SoundArrayMatchSyllableConditionBuilder() {
                super();
            }
    }

    public static SoundArrayMatchSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return SoundArrayMatchSyllableCondition.builder()
                .initialPosition(SoundPosition.getFromItem(item.get("initialPosition").getM()))
                .array(TypeUtils.getStringListFromItemList(item.get("array").getL()))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}