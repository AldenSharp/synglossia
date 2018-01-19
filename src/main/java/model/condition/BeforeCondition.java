package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class BeforeCondition extends Condition {
	private AdjacentSound adjacentSound;
	
	@Builder
	public BeforeCondition(AdjacentSound adjacentSound) {
        super(ConditionType.BEFORE);
        this.adjacentSound = adjacentSound;
    }

    public static class BeforeConditionBuilder extends ConditionBuilder {
    	BeforeConditionBuilder() {
                super();
            }
    }

    public static BeforeCondition getFromItem(Map<String, AttributeValue> item) {
	    return BeforeCondition.builder()
                .adjacentSound(AdjacentSound.getFromItem(item.get("adjacentSound").getM()))
                .build();
    }
}