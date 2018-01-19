package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class FollowsFromLastStepSyllableCondition extends SyllableCondition {
	private Integer syllableShift;
	
	@Builder
	public FollowsFromLastStepSyllableCondition(int syllableShift) {
        super(SyllableConditionType.FOLLOWS_FROM_LAST_STEP);
        this.syllableShift = syllableShift;
    }

    public static class FollowsFromLastStepSyllableConditionBuilder extends SyllableConditionBuilder {
    	FollowsFromLastStepSyllableConditionBuilder() {
                super();
            }
    }

    public static FollowsFromLastStepSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return FollowsFromLastStepSyllableCondition.builder()
                .syllableShift(TypeUtils.getIntegerFromItem(item.get("syllableShift")))
                .build();
    }
}