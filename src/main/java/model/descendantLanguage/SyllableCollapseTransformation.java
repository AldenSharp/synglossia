package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
public class SyllableCollapseTransformation extends Transformation {
	private Integer position;
	private Boolean reiterate;
	
	@Builder
	public SyllableCollapseTransformation(SyllableCondition condition, Integer position, Boolean reiterate) {
        super(TransformationType.SYLLABLE_COLLAPSE, condition);
        this.position = position;
        this.reiterate = reiterate;
    }

    public static class SyllableCollapseTransformationBuilder extends TransformationBuilder {
    	SyllableCollapseTransformationBuilder() {
            super();
        }
    }

    public static SyllableCollapseTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "reiterate"),
                Arrays.asList(NUMBER, BOOLEAN),
                location, item);
	    return SyllableCollapseTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .reiterate(TypeUtils.getBooleanFromItem(item.get("reiterate")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}