package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class BeforeHiatusSyllableCondition extends SyllableCondition {
	private Integer syllablePosition;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public BeforeHiatusSyllableCondition(Integer syllablePosition, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.BEFORE_HIATUS);
        this.syllablePosition = syllablePosition;
        this.syllablePositionType = syllablePositionType;
    }

    public static class BeforeHiatusSyllableConditionBuilder extends SyllableConditionBuilder {
    	BeforeHiatusSyllableConditionBuilder() {
                super();
            }
    }

    public static BeforeHiatusSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return BeforeHiatusSyllableCondition.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}