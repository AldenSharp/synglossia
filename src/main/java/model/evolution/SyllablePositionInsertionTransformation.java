package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllablePositionInsertionTransformation extends Transformation {
    private Integer position;
    private Boolean syllableCore;

    @Builder
    public SyllablePositionInsertionTransformation(SyllableCondition condition, Integer position, Boolean syllableCore) {
        super(TransformationType.SYLLABLE_POSITION_INSERTION, condition);
        this.position = position;
        this.syllableCore = syllableCore;
    }

    public static class SyllablePositionInsertionTransformationBuilder extends TransformationBuilder {
        SyllablePositionInsertionTransformationBuilder() { super(); }
    }

    public static SyllablePositionInsertionTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllableCore", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "syllableCore"),
                Arrays.asList(NUMBER, BOOLEAN),
                location, item);
        return SyllablePositionInsertionTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
