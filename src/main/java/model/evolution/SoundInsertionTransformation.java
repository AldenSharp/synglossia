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

import static util.FieldType.NUMBER;
import static util.FieldType.STRING;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoundInsertionTransformation extends Transformation {
    private Integer position;
    private String sound;

    @Builder
    public SoundInsertionTransformation(SyllableCondition condition, Integer position, String sound) {
        super(TransformationType.SOUND_INSERTION, condition);
        this.position = position;
        this.sound = sound;
    }

    public static class SoundInsertionTransformationBuilder extends TransformationBuilder {
        SoundInsertionTransformationBuilder() { super(); }
    }

    public static SoundInsertionTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "sound"),
                Arrays.asList(NUMBER, STRING),
                location, item);
        return SoundInsertionTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .sound(TypeUtils.getStringFromItem(item.get("sound")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
