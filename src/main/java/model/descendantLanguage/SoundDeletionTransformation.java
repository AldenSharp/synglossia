package model.descendantLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.EqualsAndHashCode;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoundDeletionTransformation extends Transformation {
	private List<Integer> positions;
	private List<String> sounds;
	
	@Builder
	public SoundDeletionTransformation(SyllableCondition condition, List<Integer> positions, List<String> sounds) {
        super(TransformationType.SOUND_DELETION, condition);
        this.positions = positions;
        this.sounds = sounds;
    }

    public static class SoundDeletionTransformationBuilder extends TransformationBuilder {
    	SoundDeletionTransformationBuilder() { super(); }
    }

    public static SoundDeletionTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("positions", "sounds"),
                Arrays.asList(LIST, LIST),
                location, item);
	    return SoundDeletionTransformation.builder()
                .positions(TypeUtils.getIntegerListFromItemList(item.get("positions").getL(), location + ": position item"))
                .sounds(TypeUtils.getStringListFromItemList(item.get("sounds").getL(), location + ": sound item"))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}