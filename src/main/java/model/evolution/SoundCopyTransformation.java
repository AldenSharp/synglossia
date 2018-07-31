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

import static util.FieldType.BOOLEAN;
import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class SoundCopyTransformation extends Transformation {
	private List<SoundMigration> migrations;
	private Boolean overwrite;
	
	@Builder
	public SoundCopyTransformation(SyllableCondition condition, List<SoundMigration> migrations, Boolean overwrite) {
        super(TransformationType.SOUND_COPY, condition);
        this.migrations = migrations;
        this.overwrite = overwrite;
    }

    public static class SoundCopyTransformationBuilder extends TransformationBuilder {
    	SoundCopyTransformationBuilder() { super(); }
    }

    public static SoundCopyTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
                Arrays.asList("migrations", "overwrite"),
                Arrays.asList(LIST, BOOLEAN),
                location, item);
	    return SoundCopyTransformation.builder()
                .migrations(SoundMigration.getListFromItemList(item.get("migrations").getL(), location + ": sound migration object"))
                .overwrite(TypeUtils.getBooleanFromItem(item.get("overwrite")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}