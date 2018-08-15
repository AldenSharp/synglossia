package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccentTransformation extends Transformation {
    private Integer order;
    private Integer syllablePosition;
    private Boolean syllablePositionAbsolute;

    @Builder
    public AccentTransformation(SyllableCondition condition, Integer order, Integer syllablePosition, Boolean syllablePositionAbsolute) {
        super(TransformationType.ACCENT, condition);
        this.order = order;
        this.syllablePosition = syllablePosition;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class AccentTransformationBuilder extends TransformationBuilder {
        AccentTransformationBuilder() { super(); }
    }

    public static AccentTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("order", key -> new AttributeValue().withN("1"));
        item.computeIfAbsent("syllablePosition", key -> new AttributeValue().withN("0"));
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("order", "syllablePosition", "syllablePositionAbsolute"),
                Arrays.asList(NUMBER, NUMBER, BOOLEAN),
                location, item);
        return AccentTransformation.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .syllablePosition(TypeUtils.getIntegerFromItem(item.get("syllablePosition")))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
