package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllablePositionDeletionTransformation extends Transformation {
    private Integer position;

    @Builder
    public SyllablePositionDeletionTransformation(SyllableCondition condition, Integer position) {
        super(TransformationType.SYLLABLE_POSITION_DELETION, condition);
        this.position = position;
    }

    public static class SyllablePositionDeletionTransformationBuilder extends TransformationBuilder {
        SyllablePositionDeletionTransformationBuilder() { super(); }
    }

    public static SyllablePositionDeletionTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("position"), Collections.singletonList(NUMBER), location, item);
        return SyllablePositionDeletionTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
