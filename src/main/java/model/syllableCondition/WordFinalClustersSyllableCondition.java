package model.syllableCondition;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

@Data
public class WordFinalClustersSyllableCondition extends SyllableCondition {
	private List<List<String>> values;
	
	@Builder
	public WordFinalClustersSyllableCondition(List<List<String>> values) {
        super(SyllableConditionType.WORD_FINAL_CLUSTERS);
        this.values = values;
    }

    public static class WordFinalClustersSyllableConditionBuilder extends SyllableConditionBuilder {
    	WordFinalClustersSyllableConditionBuilder() {
                super();
            }
    }

    public static WordFinalClustersSyllableCondition getFromItem(Map<String, AttributeValue> item) {
        return WordFinalClustersSyllableCondition.builder()
                .values(TypeUtils.getStringListListFromItemList(item.get("values").getL()))
                .build();
    }
}