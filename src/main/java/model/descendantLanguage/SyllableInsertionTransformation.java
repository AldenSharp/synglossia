package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import model.syllableCondition.SyllableCondition;
import util.TypeUtils;

import java.util.List;
import java.util.Map;

@Data
public class SyllableInsertionTransformation extends Transformation {
    private List<String> phonemes;

    @Builder
    public SyllableInsertionTransformation(SyllableCondition condition, Integer position, List<String> phonemes) {
        super(TransformationType.SYLLABLE_INSERTION, condition);
        this.phonemes = phonemes;
    }

    public static class SyllableInsertionTransformationBuilder extends TransformationBuilder {
        SyllableInsertionTransformationBuilder() {
            super();
        }
    }

    public static SyllableInsertionTransformation getFromItem(Map<String, AttributeValue> item) {
        return SyllableInsertionTransformation.builder()
                .phonemes(TypeUtils.getStringListFromItemList(item.get("phonemes").getL()))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}
