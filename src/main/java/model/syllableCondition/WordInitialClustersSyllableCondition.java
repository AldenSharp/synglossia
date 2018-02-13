package model.syllableCondition;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;

@Data
public class WordInitialClustersSyllableCondition extends SyllableCondition {
	private List<List<String>> values;
	
	@Builder
	public WordInitialClustersSyllableCondition(List<List<String>> values) {
        super(SyllableConditionType.WORD_INITIAL_CLUSTERS);
        this.values = values;
    }

    public static class WordInitialClustersSyllableConditionBuilder extends SyllableConditionBuilder {
    	WordInitialClustersSyllableConditionBuilder() {
                super();
            }
    }

    public static WordInitialClustersSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("values"), Collections.singletonList(LIST), location, item);
        return WordInitialClustersSyllableCondition.builder()
                .values(TypeUtils.getStringListListFromItemList(item.get("values").getL(), location + ": value item"))
                .build();
    }
}