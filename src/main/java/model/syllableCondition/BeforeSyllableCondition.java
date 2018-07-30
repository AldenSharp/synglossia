package model.syllableCondition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.condition.AdjacentSound;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.Arrays;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.OBJECT;

@Data
@EqualsAndHashCode(callSuper = true)
public class BeforeSyllableCondition extends SyllableCondition {
    private SoundPosition position;
    private AdjacentSound adjacentSound;
    private Boolean syllablePositionAbsolute;

    @Builder
    public BeforeSyllableCondition(SoundPosition position, AdjacentSound adjacentSound, Boolean syllablePositionAbsolute) {
        super(SyllableConditionType.BEFORE);
        this.position = position;
        this.adjacentSound = adjacentSound;
        this.syllablePositionAbsolute = syllablePositionAbsolute;
    }

    public static class BeforeSyllableConditionBuilder extends SyllableConditionBuilder {
        BeforeSyllableConditionBuilder() { super(); }
    }

    public static BeforeSyllableCondition getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("syllablePositionAbsolute", key -> new AttributeValue().withBOOL(false));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("position", "adjacentSound", "syllablePositionAbsolute"),
                Arrays.asList(OBJECT, OBJECT, BOOLEAN),
                location, item);
        return BeforeSyllableCondition.builder()
                .position(SoundPosition.getFromItem(item.get("position").getM(), location + ": position object"))
                .adjacentSound(AdjacentSound.getFromItem(item.get("adjacentSound").getM(), location + ": adjacent sound object"))
                .syllablePositionAbsolute(TypeUtils.getBooleanFromItem(item.get("syllablePositionAbsolute")))
                .build();
    }
}
