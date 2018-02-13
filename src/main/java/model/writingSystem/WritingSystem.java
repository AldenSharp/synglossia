package model.writingSystem;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.STRING;

@Data
@Builder
public class WritingSystem {
	private String name;
	private WritingSystemType type;
	
	public static WritingSystem getFromItem(Map<String, AttributeValue> item) {
        ExceptionUtils.checkObjectElements(
        		Arrays.asList("name", "type"),
                Arrays.asList(STRING, STRING),
                "Writing system", item);
        String locationWithName = "Writing system '" + item.get("name").getS() + "'";
		switch (WritingSystemType.getFromItem(item.get("type"))) {
			case ALPHABET:
				return AlphabetWritingSystem.getFromItem(item, locationWithName);
		}
		return WritingSystem.builder().build();
	}
}