package model.prosody;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.TypeUtils;

import java.util.Map;

@Data
public class StressProsody extends Prosody {
	private Integer maxOrder;
	
	@Builder
	public StressProsody(Integer maxOrder) {
        super(ProsodyType.STRESS);
        this.maxOrder = maxOrder;
    }

    public static class StressProsodyBuilder extends ProsodyBuilder {
    	StressProsodyBuilder() {
                super();
            }
    }

    public static StressProsody getFromItem(Map<String, AttributeValue> item) {
	    return StressProsody.builder()
                .maxOrder(TypeUtils.getIntegerFromItem(item.get("maxOrder")))
                .build();
    }
}