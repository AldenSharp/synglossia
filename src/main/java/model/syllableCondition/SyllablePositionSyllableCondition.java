package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class SyllablePositionSyllableCondition extends SyllableCondition {
	private Integer position;
	
	@Builder
	public SyllablePositionSyllableCondition(Integer position) {
        super(SyllableConditionType.SYLLABLE_POSITION);
        this.position = position;
    }

    public static class SyllablePositionSyllableConditionBuilder extends SyllableConditionBuilder {
    	SyllablePositionSyllableConditionBuilder() {
                super();
            }
    }

    public static SyllablePositionSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return SyllablePositionSyllableCondition.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .build();
    }
}