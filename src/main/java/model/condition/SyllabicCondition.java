package model.condition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyllabicCondition extends Condition {
	
	@Builder
	public SyllabicCondition() {
        super(ConditionType.SYLLABIC);
    }

    public static class SyllabicConditionBuilder extends ConditionBuilder {
    	SyllabicConditionBuilder() { super(); }
    }
}