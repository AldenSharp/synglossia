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
public class SoundMigrationTransformation extends Transformation {
	private List<SoundMigration> migrations;
	private Boolean overwrite;
	
	@Builder
	public SoundMigrationTransformation(SyllableCondition condition, List<SoundMigration> migrations, Boolean overwrite) {
        super(TransformationType.SOUND_MIGRATION, condition);
        this.migrations = migrations;
        this.overwrite = overwrite;
    }

    public static class SoundMigrationTransformationBuilder extends TransformationBuilder {
    	SoundMigrationTransformationBuilder() { super(); }
    }

    public static SoundMigrationTransformation getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("overwrite", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("migrations", "overwrite"),
                Arrays.asList(LIST, BOOLEAN),
                location, item);
	    return SoundMigrationTransformation.builder()
                .migrations(SoundMigration.getListFromItemList(item.get("migrations").getL(), location + ": sound migration object"))
                .overwrite(TypeUtils.getBooleanFromItem(item.get("overwrite")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM(), location + ": syllable condition"))
                .build();
    }
}