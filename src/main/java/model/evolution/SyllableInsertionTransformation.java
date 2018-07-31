package model.evolution;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllableInsertionTransformation extends Transformation {
    private List<String> phonemes;

    @Builder
    public SyllableInsertionTransformation(SyllableCondition condition, List<String> phonemes) {
        super(TransformationType.SYLLABLE_INSERTION, condition);
        this.phonemes = phonemes;
    }

    public static class SyllableInsertionTransformationBuilder extends TransformationBuilder {
        SyllableInsertionTransformationBuilder() { super(); }
    }

    public static SyllableInsertionTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("phonemes"), Collections.singletonList(LIST), location, item);
        return SyllableInsertionTransformation.builder()
                .phonemes(TypeUtils.getStringListFromItemList(item.get("phonemes").getL(), ": phoneme item"))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}
