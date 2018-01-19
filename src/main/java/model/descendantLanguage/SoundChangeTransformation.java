package model.descendantLanguage;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
public class SoundChangeTransformation extends Transformation {
	private Integer position;
	private List<SoundChange> changes;
	
	@Builder
	public SoundChangeTransformation(SyllableCondition condition, Integer position, List<SoundChange> changes) {
        super(TransformationType.SOUND_CHANGE, condition);
        this.position = position;
        this.changes = changes;
    }

    public static class SoundChangeTransformationBuilder extends TransformationBuilder {
    	SoundChangeTransformationBuilder() {
            super();
        }
    }

    public static SoundChangeTransformation getFromItem(Map<String, AttributeValue> item) {
	    return SoundChangeTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .changes(SoundChange.getListFromItemList(item.get("changes").getL()))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}