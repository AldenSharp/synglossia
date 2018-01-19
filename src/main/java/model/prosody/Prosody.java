package model.prosody;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Prosody {
	private ProsodyType type;

	public static Prosody getFromItem(Map<String, AttributeValue> item) {
	    switch (ProsodyType.getFromItem(item.get("type"))) {
            case PITCH:
                return PitchProsody.getFromItem(item);
            case STRESS:
                return StressProsody.getFromItem(item);
        }
        return Prosody.builder().build();
	}
}