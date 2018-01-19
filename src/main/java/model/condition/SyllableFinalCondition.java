package model.condition;

import lombok.Builder;
import lombok.Data;

@Data
public class SyllableFinalCondition extends Condition {
	
	@Builder
	public SyllableFinalCondition() {
        super(ConditionType.SYLLABLE_FINAL);
    }

    public static class SyllableFinalConditionBuilder extends ConditionBuilder {
    	SyllableFinalConditionBuilder() {
                super();
            }
    }
}