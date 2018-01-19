package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class AfterCondition extends Condition {
	private AdjacentSound adjacentSound;
	
	@Builder
	public AfterCondition(AdjacentSound adjacentSound) {
        super(ConditionType.AFTER);
        this.adjacentSound = adjacentSound;
    }

    public static class AfterConditionBuilder extends ConditionBuilder {
    	AfterConditionBuilder() {
                super();
            }
    }

    public static AfterCondition getFromItem(Map<String, AttributeValue> item) {
        return AfterCondition.builder()
                .adjacentSound(AdjacentSound.getFromItem(item.get("adjacentSound").getM()))
                .build();
    }
}