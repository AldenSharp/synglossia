package model.condition;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WordFinalCondition extends Condition {
	
	@Builder
	public WordFinalCondition() { super(ConditionType.WORD_FINAL); }

    public static class WordFinalConditionBuilder extends ConditionBuilder {
    	WordFinalConditionBuilder() { super(); }
    }
}