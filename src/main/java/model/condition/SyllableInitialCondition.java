package model.condition;

import lombok.Builder;
import lombok.Data;

@Data
public class SyllableInitialCondition extends Condition {
	
	@Builder
	public SyllableInitialCondition() {
        super(ConditionType.SYLLABLE_INITIAL);
    }

    public static class SyllableInitialConditionBuilder extends ConditionBuilder {
    	SyllableInitialConditionBuilder() {
                super();
            }
    }
}