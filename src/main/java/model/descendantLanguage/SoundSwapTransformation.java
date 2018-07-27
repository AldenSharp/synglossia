package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.OBJECT;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoundSwapTransformation extends Transformation {
    private SoundMigration swap;

    @Builder
    public SoundSwapTransformation(SyllableCondition condition, SoundMigration swap) {
        super(TransformationType.SOUND_SWAP, condition);
        this.swap = swap;
    }

    public static class SoundSwapTransformationBuilder extends TransformationBuilder {
        SoundSwapTransformationBuilder() { super(); }
    }

    public static SoundSwapTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("swap"), Collections.singletonList(OBJECT), location, item);
        return SoundSwapTransformation.builder()
                .swap(SoundMigration.getFromItem(item.get("swap").getM(), location + ": swap object"))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
