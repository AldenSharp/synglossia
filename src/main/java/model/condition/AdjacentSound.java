package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.LIST;
import static util.FieldType.STRING;

@Data
@Builder
public class AdjacentSound {
	private AdjacentSoundType type;
	private List<String> values;

	public static AdjacentSound getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(
        		Arrays.asList("type", "values"),
                Arrays.asList(STRING, LIST),
                location, item);
	    return AdjacentSound.builder()
                .type(AdjacentSoundType.getFromItem(item.get("type")))
                .values(TypeUtils.getStringListFromItemList(item.get("values").getL(), location + ": values"))
                .build();
    }
}