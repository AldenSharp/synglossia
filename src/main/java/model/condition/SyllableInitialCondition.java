package model.condition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllableInitialCondition extends Condition {
	
	@Builder
	public SyllableInitialCondition() {
        super(ConditionType.SYLLABLE_INITIAL);
    }

    public static class SyllableInitialConditionBuilder extends ConditionBuilder {
    	SyllableInitialConditionBuilder() { super(); }
    }
}