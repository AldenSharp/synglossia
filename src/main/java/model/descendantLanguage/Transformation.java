package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class Transformation {
	private TransformationType type;
	private SyllableCondition condition;

	private static Transformation getFromItem(Map<String, AttributeValue> item) {
	    switch (TransformationType.getFromItem(item.get("type"))) {
            case SOUND_CHANGE:
                return SoundChangeTransformation.getFromItem(item);
            case SOUND_DELETION:
                return SoundDeletionTransformation.getFromItem(item);
            case SOUND_MIGRATION:
                return SoundMigrationTransformation.getFromItem(item);
            case SOUND_COPY:
                return SoundCopyTransformation.getFromItem(item);
            case CONSONANT_DEGEMINATION:
                return ConsonantDegeminationTransformation.getFromItem(item);
            case SYLLABLE_COLLAPSE:
                return SyllableCollapseTransformation.getFromItem(item);
            case STRESS_SHIFT:
                return StressShiftTransformation.getFromItem(item);
        }
        return Transformation.builder().build();
    }

    public static List<Transformation> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}