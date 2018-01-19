package model.descendantLanguage;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.syllableCondition.SyllableCondition;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
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
    	SoundCopyTransformationBuilder() {
            super();
        }
    }

    public static SoundCopyTransformation getFromItem(Map<String, AttributeValue> item) {
	    return SoundCopyTransformation.builder()
                .migrations(SoundMigration.getListFromItemList(item.get("migrations").getL()))
                .overwrite(TypeUtils.getBooleanFromItem(item.get("overwrite")))
                .condition(SyllableCondition.getFromItem(item.get("condition").getM()))
                .build();
    }
}