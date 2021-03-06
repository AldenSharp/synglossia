package model.syllableCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import util.ExceptionUtils;
import util.TypeUtils;

import static util.FieldType.LIST;
import static util.FieldType.NUMBER;

@Data
@EqualsAndHashCode(callSuper = true)
public class StressParadigmSyllableCondition extends SyllableCondition {
	private Integer order;
	private List<StressParadigmPosition> positions;
	
	@Builder
	public StressParadigmSyllableCondition(Integer order, List<StressParadigmPosition> positions) {
        super(SyllableConditionType.STRESS_PARADIGM);
        this.order = order;
        this.positions = positions;
    }

    public static class StressParadigmSyllableConditionBuilder extends SyllableConditionBuilder {
    	StressParadigmSyllableConditionBuilder() { super(); }
    }

    public static StressParadigmSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("order", key -> new AttributeValue().withN("1"));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("order", "positions"),
                Arrays.asList(NUMBER, LIST),
                location, item);
        return StressParadigmSyllableCondition.builder()
                .order(TypeUtils.getIntegerFromItem(item.get("order")))
                .positions(StressParadigmPosition.getListFromItemList(item.get("positions").getL(), location + ": stress paradigm position object"))
                .build();
    }
}