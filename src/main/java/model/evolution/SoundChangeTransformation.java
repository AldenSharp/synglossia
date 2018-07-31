package model.evolution;

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
public class SoundChangeTransformation extends Transformation {
	private List<Integer> positions;
	private List<SoundChange> changes;
	
	@Builder
	public SoundChangeTransformation(SyllableCondition condition, List<Integer> positions, List<SoundChange> changes) {
        super(TransformationType.SOUND_CHANGE, condition);
        this.positions = positions;
        this.changes = changes;
    }

    public static class SoundChangeTransformationBuilder extends TransformationBuilder {
    	SoundChangeTransformationBuilder() { super(); }
    }

    public static SoundChangeTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("positions", "changes"),
                Arrays.asList(LIST, LIST),
                location, item);
	    return SoundChangeTransformation.builder()
                .positions(TypeUtils.getIntegerListFromItemList(item.get("positions").getL(), location + ": positions object"))
                .changes(SoundChange.getListFromItemList(item.get("changes").getL(), location + ": sound change object"))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}