package model.condition;

import lombok.Builder;
import lombok.Data;

@Data
public class WordFinalCondition extends Condition {
	
	@Builder
	public WordFinalCondition() {
        super(ConditionType.WORD_FINAL);
    }

    public static class WordFinalConditionBuilder extends ConditionBuilder {
    	WordFinalConditionBuilder() {
                super();
            }
    }
}