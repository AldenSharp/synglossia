package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
@Builder
public class AdjacentSound {
	private AdjacentSoundType type;
	private String value;

	public static AdjacentSound getFromItem(Map<String, AttributeValue> item) {
	    return AdjacentSound.builder()
                .type(AdjacentSoundType.getFromItem(item.get("type")))
                .value(TypeUtils.getStringFromItem(item.get("value")))
                .build();
    }
}