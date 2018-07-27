package model.condition;

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
public class AfterCondition extends Condition {
	private AdjacentSound adjacentSound;
	
	@Builder
	public AfterCondition(AdjacentSound adjacentSound) {
        super(ConditionType.AFTER);
        this.adjacentSound = adjacentSound;
    }

    public static class AfterConditionBuilder extends ConditionBuilder {
    	AfterConditionBuilder() { super(); }
    }

    public static AfterCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("adjacentSound"), Collections.singletonList(OBJECT), location, item);
        return AfterCondition.builder()
                .adjacentSound(AdjacentSound.getFromItem(item.get("adjacentSound").getM(), location + ": adjacent sound"))
                .build();
    }
}