package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllableCollapseTransformation extends Transformation {
	private Integer position;
	
	@Builder
	public SyllableCollapseTransformation(SyllableCondition condition, Integer position) {
        super(TransformationType.SYLLABLE_COLLAPSE, condition);
        this.position = position;
    }

    public static class SyllableCollapseTransformationBuilder extends TransformationBuilder {
    	SyllableCollapseTransformationBuilder() { super(); }
    }

    public static SyllableCollapseTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Collections.singletonList("position"),
                Collections.singletonList(NUMBER),
                location, item);
	    return SyllableCollapseTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}