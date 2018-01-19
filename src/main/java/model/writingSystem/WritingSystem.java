package model.writingSystem;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class WritingSystem {
	private String name;
	private WritingSystemType type;
	
	public static WritingSystem getFromItem(Map<String, AttributeValue> item) {
		switch (WritingSystemType.getFromItem(item.get("type"))) {
			case ALPHABET:
				return AlphabetWritingSystem.getFromItem(item);
		}
		return WritingSystem.builder().build();
	}
}