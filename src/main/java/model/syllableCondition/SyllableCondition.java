package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class SyllableCondition {
	@Builder.Default private SyllableConditionType type = SyllableConditionType.DEFAULT;

	public static SyllableCondition getFromItem(Map<String, AttributeValue> item) {
		switch (SyllableConditionType.getFromItem(item.get("type"))) {
            case DEFAULT:
                return SyllableCondition.builder().build();
            case NOT:
                return NotSyllableCondition.getFromItem(item);
            case AND:
                return AndSyllableCondition.getFromItem(item);
            case OR:
                return OrSyllableCondition.getFromItem(item);
            case WORD_MEDIAL_CLUSTERS:
                return WordMedialClustersSyllableCondition.getFromItem(item);
            case WORD_INITIAL_CLUSTERS:
                return WordInitialClustersSyllableCondition.getFromItem(item);
            case WORD_FINAL_CLUSTERS:
                return WordFinalClustersSyllableCondition.getFromItem(item);
            case SYLLABLE_INITIAL_CLUSTERS:
                return SyllableInitialClustersSyllableCondition.getFromItem(item);
            case SYLLABLE_FINAL_CLUSTERS:
                return SyllableFinalClustersSyllableCondition.getFromItem(item);
            case SOUND_VALUES:
                return SoundValuesSyllableCondition.getFromItem(item);
            case EMPTY:
                return EmptySyllableCondition.getFromItem(item);
            case EMPTY_AT_ALL:
                return EmptyAtAllSyllableCondition.getFromItem(item);
            case EMPTY_AT_SOME:
                return EmptyAtSomeSyllableCondition.getFromItem(item);
            case OPEN:
                return OpenSyllableCondition.getFromItem(item);
            case BEFORE_HIATUS:
                return BeforeHiatusSyllableCondition.getFromItem(item);
            case SYLLABLE_POSITION:
                return SyllablePositionSyllableCondition.getFromItem(item);
            case SHORT_VOWEL:
                return ShortVowelSyllableCondition.getFromItem(item);
            case LONG_VOWEL:
                return LongVowelSyllableCondition.getFromItem(item);
            case MATCH:
                return MatchSyllableCondition.getFromItem(item);
            case SOUND_ARRAY_MATCH:
                return SoundArrayMatchSyllableCondition.getFromItem(item);
            case STRESSED:
                return StressedSyllableCondition.getFromItem(item);
            case STRESS_EXISTENCE:
                return StressExistenceSyllableCondition.getFromItem(item);
            case STRESS_UNIQUENESS:
                return StressUniquenessSyllableCondition.getFromItem(item);
            case STRESS_PARADIGM:
                return StressParadigmSyllableCondition.getFromItem(item);
            case SYLLABLE_COUNT:
                return SyllableCountSyllableCondition.getFromItem(item);
            case FOLLOWS_FROM_LAST_STEP:
                return FollowsFromLastStepSyllableCondition.getFromItem(item);
        }
		return SyllableCondition.builder().build();
	}

	public static List<SyllableCondition> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}