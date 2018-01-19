package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import model.syllableCondition.SyllablePositionType;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class ConsonantDegeminationTransformation extends Transformation {
	private Integer syllablePosition;
	private SyllablePositionType syllablePositionType;
	
	@Builder
	public ConsonantDegeminationTransformation(SyllableCondition condition, Integer syllablePosition, SyllablePositionType syllablePositionType) {
        super(TransformationType.CONSONANT_DEGEMINATION, condition);
        this.syllablePosition = syllablePosition;
        this.syllablePositionType = syllablePositionType;
    }

    public static class ConsonantDegeminationTransformationBuilder extends TransformationBuilder {
    	ConsonantDegeminationTransformationBuilder() {
            super();
        }
    }

    public static ConsonantDegeminationTransformation getFromItem(Map<String, AttributeValue> item) {
	    return ConsonantDegeminationTransformation.builder()
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionType(SyllablePositionType.getFromItem(item.get("syllablePositionType")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}