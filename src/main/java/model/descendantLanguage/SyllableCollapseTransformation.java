package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

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

    public static SyllableCollapseTransformation getFromItem(Map<String, AttributeValue> item) {
	    return SyllableCollapseTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .reiterate(TypeUtils.getBooleanFromItem(item.get("reiterate")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}