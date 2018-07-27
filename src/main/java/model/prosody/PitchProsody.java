package model.prosody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class PitchProsody extends Prosody {
	private List<Integer> inventory;
	
	@Builder
	public PitchProsody(List<Integer> inventory) {
        super(ProsodyType.PITCH);
        this.inventory = inventory;
    }

    public static class PitchProsodyBuilder extends ProsodyBuilder {
    	PitchProsodyBuilder() { super(); }
    }

    public static PitchProsody getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("inventory"), Collections.singletonList(LIST), location, item);
	    return PitchProsody.builder()
                .inventory(TypeUtils.getIntegerListFromItemList(item.get("inventory").getL(), location + ": inventory value"))
                .build();
    }
}