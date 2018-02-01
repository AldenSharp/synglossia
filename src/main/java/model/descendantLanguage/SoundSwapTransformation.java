package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.syllableCondition.SyllableCondition;

import java.util.Map;

@Data
public class SoundSwapTransformation extends Transformation {
    private SoundMigration swap;

    @Builder
    public SoundSwapTransformation(SyllableCondition condition, SoundMigration swap) {
        super(TransformationType.SOUND_SWAP, condition);
        this.swap = swap;
    }

    public static class SoundSwapTransformationBuilder extends TransformationBuilder {
        SoundSwapTransformationBuilder() {
            super();
        }
    }

    public static SoundSwapTransformation getFromItem(Map<String, AttributeValue> item) {
        return SoundSwapTransformation.builder()
                .swap(SoundMigration.getFromItem(item.get("swap").getM()))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}
