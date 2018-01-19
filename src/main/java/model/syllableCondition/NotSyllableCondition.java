package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class NotSyllableCondition extends SyllableCondition {
	private SyllableCondition condition;
	
	@Builder
	public NotSyllableCondition(SyllableCondition condition) {
        super(SyllableConditionType.NOT);
        this.condition = condition;
    }

    public static class NotSyllableConditionBuilder extends SyllableConditionBuilder {
    	NotSyllableConditionBuilder() {
                super();
            }
    }

    public static NotSyllableCondition getFromItem(Map<String, AttributeValue> item) {
	    return NotSyllableCondition.builder()
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}