package model.condition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmptyCondition extends Condition {
	
	@Builder
	public EmptyCondition() {
        super(ConditionType.EMPTY);
    }

    public static class EmptyConditionBuilder extends ConditionBuilder {
    	EmptyConditionBuilder() { super(); }
    }
}