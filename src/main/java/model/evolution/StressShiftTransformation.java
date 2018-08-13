package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class StressShiftTransformation extends Transformation {
	private Integer order;
	private Integer shift;
	private Boolean syllablePositionAbsolute;
	
	@Builder
	public StressShiftTransformation(SyllableCondition condition, Integer order, Integer shift) {
        super(TransformationType.STRESS_SHIFT, condition);
        this.order = order;
        this.shift = shift;
    }

    public static class StressShiftTransformationBuilder extends TransformationBuilder {
    	StressShiftTransformationBuilder() { super(); }
    }

    public static StressShiftTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("order", "shift", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, NUMBER, BOOLEAN),
                location, item);
	    return StressShiftTransformation.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .shift(TypeUtils.getIntegerFromItem(item.get("shift")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}