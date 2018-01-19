package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class StressShiftTransformation extends Transformation {
	private Integer order;
	private Integer shift;
	
	@Builder
	public StressShiftTransformation(SyllableCondition condition, Integer order, Integer shift) {
        super(TransformationType.STRESS_SHIFT, condition);
        this.order = order;
        this.shift = shift;
    }

    public static class StressShiftTransformationBuilder extends TransformationBuilder {
    	StressShiftTransformationBuilder() {
            super();
        }
    }

    public static StressShiftTransformation getFromItem(Map<String, AttributeValue> item) {
	    return StressShiftTransformation.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .shift(TypeUtils.getIntegerFromItem(item.get("shift")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}