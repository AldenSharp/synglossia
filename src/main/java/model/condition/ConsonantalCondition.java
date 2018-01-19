package model.condition;

import lombok.Builder;
import lombok.Data;

@Data
public class ConsonantalCondition extends Condition {
	
	@Builder
	public ConsonantalCondition() {
        super(ConditionType.CONSONANTAL);
    }

    public static class ConsonantalConditionBuilder extends ConditionBuilder {
    	ConsonantalConditionBuilder() {
                super();
            }
    }
}