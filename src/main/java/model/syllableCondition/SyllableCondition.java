package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FieldType.OBJECT;
import static util.FieldType.STRING;

@Data
@Builder
public class SyllableCondition {
	@Builder.Default private SyllableConditionType type = SyllableConditionType.DEFAULT;

	public static SyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("type"), Collections.singletonList(STRING), location, item);
        String locationWithType = location + " of type " + item.get("type").getS();
		switch (SyllableConditionType.getFromItem(item.get("type"))) {
            case DEFAULT:
                return SyllableCondition.builder().build();
            case NOT:
                return NotSyllableCondition.getFromItem(item, locationWithType);
            case AND:
                return AndSyllableCondition.getFromItem(item, locationWithType);
            case OR:
                return OrSyllableCondition.getFromItem(item, locationWithType);
            case WORD_MEDIAL_CLUSTERS:
                return WordMedialClustersSyllableCondition.getFromItem(item, locationWithType);
            case WORD_INITIAL_CLUSTERS:
                return WordInitialClustersSyllableCondition.getFromItem(item, locationWithType);
            case WORD_FINAL_CLUSTERS:
                return WordFinalClustersSyllableCondition.getFromItem(item, locationWithType);
            case SYLLABLE_INITIAL_CLUSTERS:
                return SyllableInitialClustersSyllableCondition.getFromItem(item, locationWithType);
            case SYLLABLE_FINAL_CLUSTERS:
                return SyllableFinalClustersSyllableCondition.getFromItem(item, locationWithType);
            case SOUND_VALUES:
                return SoundValuesSyllableCondition.getFromItem(item, locationWithType);
            case EMPTY:
                return EmptySyllableCondition.getFromItem(item, locationWithType);
            case EMPTY_AT_ALL:
                return EmptyAtAllSyllableCondition.getFromItem(item, locationWithType);
            case EMPTY_AT_SOME:
                return EmptyAtSomeSyllableCondition.getFromItem(item, locationWithType);
            case OPEN:
                return OpenSyllableCondition.getFromItem(item, locationWithType);
            case BEFORE_HIATUS:
                return BeforeHiatusSyllableCondition.getFromItem(item, locationWithType);
            case SYLLABLE_POSITION:
                return SyllablePositionSyllableCondition.getFromItem(item, locationWithType);
            case SHORT_VOWEL:
                return ShortVowelSyllableCondition.getFromItem(item, locationWithType);
            case LONG_VOWEL:
                return LongVowelSyllableCondition.getFromItem(item, locationWithType);
            case MATCH:
                return MatchSyllableCondition.getFromItem(item, locationWithType);
            case SOUND_ARRAY_MATCH:
                return SoundArrayMatchSyllableCondition.getFromItem(item, locationWithType);
            case LENGTH:
                return LengthSyllableCondition.getFromItem(item, locationWithType);
            case STRESSED:
                return StressedSyllableCondition.getFromItem(item, locationWithType);
            case BEFORE_STRESS:
                return BeforeStressSyllableCondition.getFromItem(item, locationWithType);
            case STRESS_EXISTENCE:
                return StressExistenceSyllableCondition.getFromItem(item, locationWithType);
            case STRESS_UNIQUENESS:
                return StressUniquenessSyllableCondition.getFromItem(item, locationWithType);
            case STRESS_PARADIGM:
                return StressParadigmSyllableCondition.getFromItem(item, locationWithType);
            case SYLLABLE_COUNT:
                return SyllableCountSyllableCondition.getFromItem(item, locationWithType);
            case FOLLOWS_FROM_LAST_STEP:
                return FollowsFromLastStepSyllableCondition.getFromItem(item, locationWithType);
        }
		return SyllableCondition.builder().build();
	}

	public static List<SyllableCondition> getListFromItemList(List<AttributeValue> itemList, String location) {
	    ExceptionUtils.checkListElements(itemList, location, OBJECT);
	    return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}