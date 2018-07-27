package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.OBJECT;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotSyllableCondition extends SyllableCondition {
	private SyllableCondition condition;
	
	@Builder
	public NotSyllableCondition(SyllableCondition condition) {
        super(SyllableConditionType.NOT);
        this.condition = condition;
    }

    public static class NotSyllableConditionBuilder extends SyllableConditionBuilder {
    	NotSyllableConditionBuilder() { super(); }
    }

    public static NotSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("condition"), Collections.singletonList(OBJECT), location, item);
	    return NotSyllableCondition.builder()
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": negation condition"))
                .build();
    }
}