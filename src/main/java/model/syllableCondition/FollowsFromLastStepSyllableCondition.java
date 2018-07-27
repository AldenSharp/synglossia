package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class FollowsFromLastStepSyllableCondition extends SyllableCondition {
	private Integer syllableShift;
	
	@Builder
	public FollowsFromLastStepSyllableCondition(int syllableShift) {
        super(SyllableConditionType.FOLLOWS_FROM_LAST_STEP);
        this.syllableShift = syllableShift;
    }

    public static class FollowsFromLastStepSyllableConditionBuilder extends SyllableConditionBuilder {
    	FollowsFromLastStepSyllableConditionBuilder() { super(); }
    }

    public static FollowsFromLastStepSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("syllableShift"), Collections.singletonList(NUMBER), location, item);
	    return FollowsFromLastStepSyllableCondition.builder()
                .syllableShift(TypeUtils.getIntegerFromItem(item.get("syllableShift")))
                .build();
    }
}