package model.descendantLanguage;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class Transformation {
	private TransformationType type;
	private SyllableCondition condition;

	private static Transformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("type", "condition"),
                Arrays.asList(STRING, OBJECT),
                location, item);
        String locationWithType = location + ", of type " + item.get("type").getS();
	    switch (TransformationType.getFromItem(item.get("type"))) {
            case SOUND_CHANGE:
                return SoundChangeTransformation.getFromItem(item, locationWithType);
            case SOUND_DELETION:
                return SoundDeletionTransformation.getFromItem(item, locationWithType);
            case SOUND_INSERTION:
                return SoundInsertionTransformation.getFromItem(item, locationWithType);
            case SOUND_MIGRATION:
                return SoundMigrationTransformation.getFromItem(item, locationWithType);
            case SOUND_COPY:
                return SoundCopyTransformation.getFromItem(item, locationWithType);
            case SOUND_SWAP:
                return SoundSwapTransformation.getFromItem(item, locationWithType);
            case CONSONANT_DEGEMINATION:
                return ConsonantDegeminationTransformation.getFromItem(item, locationWithType);
            case SYLLABLE_COLLAPSE:
                return SyllableCollapseTransformation.getFromItem(item, locationWithType);
            case SYLLABLE_INSERTION:
                return SyllableInsertionTransformation.getFromItem(item, locationWithType);
            case STRESS_SHIFT:
                return StressShiftTransformation.getFromItem(item, locationWithType);
        }
        return Transformation.builder().build();
    }

    public static List<Transformation> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}