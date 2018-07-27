package model.prosody;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Collections;
import java.util.Map;

import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class StressProsody extends Prosody {
	private Integer maxOrder;
	
	@Builder
	public StressProsody(Integer maxOrder) {
        super(ProsodyType.STRESS);
        this.maxOrder = maxOrder;
    }

    public static class StressProsodyBuilder extends ProsodyBuilder {
    	StressProsodyBuilder() { super(); }
    }

    public static StressProsody getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("maxOrder"), Collections.singletonList(NUMBER), location, item);
	    return StressProsody.builder()
                .maxOrder(TypeUtils.getIntegerFromItem(item.get("maxOrder")))
                .build();
    }
}