package model.prosody;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static util.FieldType.STRING;

@Data
@Builder
public class Prosody {
	private ProsodyType type;

	public static Prosody getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("type"), Collections.singletonList(STRING), location, item);
        String locationWithType = location + " of type " + item.get("type").getS();
	    switch (ProsodyType.getFromItem(item.get("type"))) {
            case PITCH:
                return PitchProsody.getFromItem(item, locationWithType);
            case STRESS:
                return StressProsody.getFromItem(item, locationWithType);
        }
        return Prosody.builder().build();
	}
}