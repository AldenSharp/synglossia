package model.condition;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import util.ExceptionUtils;
import util.TypeUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static util.FieldType.BOOLEAN;
import static util.FieldType.LIST;
import static util.FieldType.STRING;

@Data
@Builder
public class AdjacentSound {
    private Boolean possiblySyllabic;
    private List<String> values;

    public static AdjacentSound getFromItem(Map<String, AttributeValue> item, String location) {
        item.computeIfAbsent("possiblySyllabic", key -> new AttributeValue().withBOOL(true));
        ExceptionUtils.checkObjectElements(
                Arrays.asList("possiblySyllabic", "values"),
                Arrays.asList(BOOLEAN, LIST),
                location, item);
        return AdjacentSound.builder()
                .possiblySyllabic(TypeUtils.getBooleanFromItem(item.get("possiblySyllabic")))
                .values(TypeUtils.getStringListFromItemList(item.get("values").getL(), location + ": values"))
                .build();
    }
}