package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class StressedSyllableCondition extends SyllableCondition {
	private Integer order;
	private Integer syllablePosition;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public StressedSyllableCondition(Integer order, Integer syllablePosition, SyllablePositionType syllablePositionType) {
        super(SyllableConditionType.STRESSED);
        this.order = order;
        this.syllablePosition = syllablePosition;
        this.syllablePositionType = syllablePositionType;
    }

    public static class StressedSyllableConditionBuilder extends SyllableConditionBuilder {
    	StressedSyllableConditionBuilder() {
                super();
            }
    }

    public static StressedSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return StressedSyllableCondition.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .build();
    }
}