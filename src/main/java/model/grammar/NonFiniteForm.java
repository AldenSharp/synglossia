package model.grammar;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import util.ExceptionUtils;
import util.TypeUtils;

import java.util.List;
import java.util.stream.Collectors;

import static util.FieldType.STRING;

public enum NonFiniteForm {
    INFINITIVE("INFINITIVE"), GERUND("GERUND"), PARTICIPLE("PARTICIPLE"), SUPINE("SUPINE"), GERUNDIVE("GERUNDIVE"),
    VERBAL_NOUN("VERBAL_NOUN");

    private final String value;
    NonFiniteForm(String value) { this.value = value; }

    public static NonFiniteForm getFromItem(AttributeValue item) { return valueOf(TypeUtils.getStringFromItem(item)); }

    public static List<NonFiniteForm> getListFromItemList(List<AttributeValue> itemList, String location) {
        ExceptionUtils.checkListElements(itemList, location, STRING);
        return itemList.stream()
                .map(NonFiniteForm::getFromItem)
                .collect(Collectors.toList());
    }
}
