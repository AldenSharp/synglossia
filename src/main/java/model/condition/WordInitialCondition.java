package model.condition;

import lombok.Builder;
import lombok.Data;

@Data
public class WordInitialCondition extends Condition {
	
	@Builder
	public WordInitialCondition() {
        super(ConditionType.WORD_INITIAL);
    }

    public static class WordInitialConditionBuilder extends ConditionBuilder {
    	WordInitialConditionBuilder() {
                super();
            }
    }
}