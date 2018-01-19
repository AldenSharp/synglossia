package model.descendantLanguage;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
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
    	SoundMigrationTransformationBuilder() {
            super();
        }
    }

    public static SoundMigrationTransformation getFromItem(Map<String, AttributeValue> item) {
	    return SoundMigrationTransformation.builder()
                .migrations(SoundMigration.getListFromItemList(item.get("migrations").getL()))
                .overwrite(TypeUtils.getBooleanFromItem(item.get("overwrite")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}