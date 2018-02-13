package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.OBJECT;

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

    public static BeforeCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("adjacentSound"), Collections.singletonList(OBJECT), location, item);
	    return BeforeCondition.builder()
                .adjacentSound(AdjacentSound.getFromItem(item.get("adjacentSound").getM(), location + ": adjacent sound"))
                .build();
    }
}