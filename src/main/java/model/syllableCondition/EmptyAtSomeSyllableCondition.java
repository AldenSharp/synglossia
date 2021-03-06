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

import static util.FieldType.BOOLEAN;
import static util.FieldType.LIST;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmptyAtSomeSyllableCondition extends SyllableCondition {
	private List<SoundPosition> positions;
    private Boolean syllablePositionAbsolute;
	
	@Builder
	public EmptyAtSomeSyllableCondition(List<SoundPosition> positions, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.EMPTY_AT_SOME);
        this.positions = positions;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class EmptyAtSomeSyllableConditionBuilder extends SyllableConditionBuilder {
    	EmptyAtSomeSyllableConditionBuilder() { super(); }
    }

    public static EmptyAtSomeSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("positions", "syllablePositionAbsolute"),
                Arrays.asList(LIST, BOOLEAN),
                location, item);
        return EmptyAtSomeSyllableCondition.builder()
                .positions(SoundPosition.getListFromItemList(item.get("positions").getL(), location + ": position object"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}