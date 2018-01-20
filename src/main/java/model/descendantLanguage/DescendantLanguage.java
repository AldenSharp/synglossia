package model.descendantLanguage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Builder;
import lombok.Data;
import service.LanguageService;
import util.TypeUtils;

@Data
@Builder
public class DescendantLanguage {
	private String name;
	private List<EvolutionStep> evolution;
	private List<DescendantLanguage> descendantLanguages;
	
	public static DescendantLanguage getFromItem(Map<String, AttributeValue> item) {
		return DescendantLanguage.builder()
				.name(TypeUtils.getStringFromItem(item.get("name")))
				.evolution(EvolutionStep.getListFromItemList(item.get("evolution").getL()))
                .descendantLanguages(getRecursiveDescendantLanguages(TypeUtils.getStringFromItem(item.get("name"))))
				.build();
	}

	public static List<DescendantLanguage> getListFromItemList(List<AttributeValue> itemList) {
	    return itemList.stream()
                .map(item -> getFromItem(item.getM()))
                .collect(Collectors.toList());
    }

    private static List<DescendantLanguage> getRecursiveDescendantLanguages(String languageName) {
	    return new LanguageService().getDescendantLanguagesFromData(languageName);
    }
}