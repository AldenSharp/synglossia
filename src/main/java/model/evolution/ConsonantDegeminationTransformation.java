package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConsonantDegeminationTransformation extends Transformation {

	@Builder
	public ConsonantDegeminationTransformation(SyllableCondition condition) {
        super(TransformationType.CONSONANT_DEGEMINATION, condition);
    }

    public static class ConsonantDegeminationTransformationBuilder extends TransformationBuilder {
    	ConsonantDegeminationTransformationBuilder() { super(); }
    }

    public static ConsonantDegeminationTransformation getFromItem(Map<String, AttributeValue> item, String location) {
	    return ConsonantDegeminationTransformation.builder()
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}