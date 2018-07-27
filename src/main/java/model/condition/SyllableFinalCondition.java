package model.condition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllableFinalCondition extends Condition {
	
	@Builder
	public SyllableFinalCondition() {
        super(ConditionType.SYLLABLE_FINAL);
    }

    public static class SyllableFinalConditionBuilder extends ConditionBuilder {
    	SyllableFinalConditionBuilder() { super(); }
    }
}