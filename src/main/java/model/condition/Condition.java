package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class Condition {
	@Builder.Default private ConditionType type = ConditionType.DEFAULT;

	public static Condition getFromItem(Map<String, AttributeValue> item) {
		switch (ConditionType.getFromItem(item.get("type"))) {
			case DEFAULT:
				return Condition.builder().build();
			case AND:
				return AndCondition.getFromItem(item);
			case OR:
				return OrCondition.getFromItem(item);
            case BEFORE:
                return BeforeCondition.getFromItem(item);
            case AFTER:
                return AfterCondition.getFromItem(item);
            case CONSONANTAL:
                return ConsonantalCondition.builder().build();
            case SYLLABIC:
                return SyllabicCondition.builder().build();
            case WORD_INITIAL:
                return WordInitialCondition.builder().build();
            case WORD_FINAL:
                return WordFinalCondition.builder().build();
            case SYLLABLE_INITIAL:
                return SyllableInitialCondition.builder().build();
            case SYLLABLE_FINAL:
                return SyllableFinalCondition.builder().build();
			case SOUND_VALUES:
				return SoundValuesCondition.getFromItem(item);
			case EMPTY:
				return EmptyCondition.builder().build();
			case SYLLABLE_COUNT:
				return SyllableCountCondition.getFromItem(item);
		}
		return Condition.builder().build();
	}

	public static List<Condition> getListFromItemList(List<AttributeValue> itemList) {
        return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }
}