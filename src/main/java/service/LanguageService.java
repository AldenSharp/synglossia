package service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Comparison;
import model.PhonotacticsPosition;
import model.Syngloss;
import model.condition.*;
import model.descendantLanguage.*;
import model.prosody.StressProsody;
import model.syllableCondition.*;
import model.writingSystem.AlphabetRule;
import model.writingSystem.AlphabetRuleGrapheme;
import model.writingSystem.AlphabetWritingSystem;
import model.writingSystem.WritingSystem;

public class LanguageService {

    private DynamoDBService dynamoDBService = new DynamoDBService();

    public Syngloss getSyngloss(String languageName) throws IOException {
        Map<String, AttributeValue> parentLanguageItem = dynamoDBService.getParentLanguage(languageName);
        Syngloss syngloss = Syngloss.getFromItem(parentLanguageItem);
        syngloss.setWritingSystems(getWritingSystemsFromData(languageName));
        syngloss.setDescendantLanguages(getDescendantLanguagesFromData(languageName));
        return syngloss;
    }

    private List<WritingSystem> getWritingSystemsFromData(String language) {
        List<Map<String, AttributeValue>> writingSystemItems = dynamoDBService.getWritingSystems(language);
        return writingSystemItems.stream()
                .map(WritingSystem::getFromItem)
                .collect(Collectors.toList());
    }

    private List<DescendantLanguage> getDescendantLanguagesFromData(String parentLanguage) {
        List<Map<String, AttributeValue>> descendantLanguageItems = dynamoDBService.getDescendantLanguages(parentLanguage);
        return descendantLanguageItems.stream()
                .map(DescendantLanguage::getFromItem)
                .collect(Collectors.toList());
    }
}