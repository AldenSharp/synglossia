package model.condition;

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
public class Condition {
	@Builder.Default private ConditionType type = ConditionType.DEFAULT;

	public static Condition getFromItem(Map<String, AttributeValue> item, String location) {
		ExceptionUtils.checkObjectElements(Collections.singletonList("type"), Collections.singletonList(STRING), location, item);
		String locationWithType = location + " of type " + item.get("type").getS();
		switch (ConditionType.getFromItem(item.get("type"))) {
			case DEFAULT:
				return Condition.builder().build();
			case AND:
				return AndCondition.getFromItem(item, locationWithType);
			case OR:
				return OrCondition.getFromItem(item, locationWithType);
            case BEFORE:
                return BeforeCondition.getFromItem(item, locationWithType);
            case AFTER:
                return AfterCondition.getFromItem(item, locationWithType);
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
				return SoundValuesCondition.getFromItem(item, locationWithType);
			case EMPTY:
				return EmptyCondition.builder().build();
			case SYLLABLE_COUNT:
				return SyllableCountCondition.getFromItem(item, locationWithType);
		}
		return Condition.builder().build();
	}

	public static List<Condition> getListFromItemList(List<AttributeValue> itemList, String location) {
		ExceptionUtils.checkListElements(itemList, location, OBJECT);
        return itemList.stream()
                .map(item -> getFromItem(item.getM(), location + " at position " + itemList.indexOf(item)))
                .collect(Collectors.toList());
    }
}