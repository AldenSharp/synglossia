package model.condition;

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
public class SoundValuesCondition extends Condition {
	private List<String> values;
	
	@Builder
	public SoundValuesCondition(List<String> values) {
        super(ConditionType.SOUND_VALUES);
        this.values = values;
    }

    public static class SoundValuesConditionBuilder extends ConditionBuilder {
    	SoundValuesConditionBuilder() { super(); }
    }

    public static SoundValuesCondition getFromItem(Map<String, AttributeValue> item, String location) {
        ExceptionUtils.checkObjectElements(Collections.singletonList("values"), Collections.singletonList(LIST), location, item);
	    return SoundValuesCondition.builder()
                .values(TypeUtils.getStringListFromItemList(item.get("values").getL(), ": value item"))
                .build();
    }
}