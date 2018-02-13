package model.descendantLanguage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.NUMBER;

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

    public static SoundChangeTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "changes"),
                Arrays.asList(NUMBER, LIST),
                location, item);
	    return SoundChangeTransformation.builder()
                .position(TypeUtils.getIntegerFromItem(item.get("position")))
                .changes(SoundChange.getListFromItemList(item.get("changes").getL(), location + ": sound change object"))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}