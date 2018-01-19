package model.descendantLanguage;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
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
    	SoundDeletionTransformationBuilder() {
            super();
        }
    }

    public static SoundDeletionTransformation getFromItem(Map<String, AttributeValue> item) {
	    return SoundDeletionTransformation.builder()
                .positions(TypeUtils.getIntegerListFromItemList(item.get("positions").getL()))
                .sounds(TypeUtils.getStringListFromItemList(item.get("sounds").getL()))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}