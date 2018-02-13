package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.STRING;

@Data
@Builder
public class AdjacentSound {
	private AdjacentSoundType type;
	private String value;

	public static AdjacentSound getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
        		Arrays.asList("type", "value"),
                Arrays.asList(STRING, STRING),
                location, item);
	    return AdjacentSound.builder()
                .type(AdjacentSoundType.getFromItem(item.get("type")))
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .build();
    }
}