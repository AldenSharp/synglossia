package model.writingSystem;

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
public class AlphabetWritingSystem extends WritingSystem {
	private List<AlphabetRule> rules;
	
	@Builder
	public AlphabetWritingSystem(String name, List<AlphabetRule> rules) {
        super(name, WritingSystemType.ALPHABET);
        this.rules = rules;
    }

    public static class AlphabetWritingSystemBuilder extends WritingSystemBuilder {
    	AlphabetWritingSystemBuilder() {
                super();
            }
    }
    
    public static AlphabetWritingSystem getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("rules"), Collections.singletonList(LIST), location, item);
    	return AlphabetWritingSystem.builder()
                .name(TypeUtils.getStringFromItem(item.get("name")))
    			.rules(AlphabetRule.getListFromItemList(item.get("rules").getL(), location + ": alphabet rule"))
    			.build();
    }
}