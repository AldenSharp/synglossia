package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.*;

@Data
public class SoundArrayMatchSyllableCondition extends SyllableCondition {
	private SoundPosition initialPosition;
	private List<String> array;
	private Boolean syllablePositionAbsolute;
	
	@Builder
	public SoundArrayMatchSyllableCondition(SoundPosition initialPosition, List<String> array, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.SOUND_ARRAY_MATCH);
        this.initialPosition = initialPosition;
        this.array = array;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class SoundArrayMatchSyllableConditionBuilder extends SyllableConditionBuilder {
    	SoundArrayMatchSyllableConditionBuilder() {
                super();
            }
    }

    public static SoundArrayMatchSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("initialPosition", "array", "syllablePositionAbsolute"),
                Arrays.asList(OBJECT, LIST, BOOLEAN),
                location, item);
        return SoundArrayMatchSyllableCondition.builder()
                .initialPosition(SoundPosition.getFromItem(item.get("initialPosition").getM(), location + ": position object"))
                .array(TypeUtils.getStringListFromItemList(item.get("array").getL(), location + ": array item"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}