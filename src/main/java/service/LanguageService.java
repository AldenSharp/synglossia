package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Syngloss;
import model.evolution.*;
import model.writingSystem.WritingSystem;

public class LanguageService {

    private DynamoDBService dynamoDBService = new DynamoDBService();

    public List<String> getParentLanguageNames() {
        return dynamoDBService.getAllParentLanguages()
                .stream().map(item -> item.get("name").getS()).collect(Collectors.toList());
    }

    public Syngloss getSyngloss(String languageName) throws IOException {
        Map<String, AttributeValue> parentLanguageItem = dynamoDBService.getLanguage(languageName, "PARENT");
        Syngloss syngloss = Syngloss.getFromItem(parentLanguageItem);
        syngloss.setWritingSystems(getWritingSystemsFromData(languageName));
        syngloss.setDescendantLanguages(getDescendantLanguagesFromData(languageName));
        List<Map<String, AttributeValue>> morphemeItems = dynamoDBService.getGrammaticalMorphemes(languageName);
        syngloss.getMorphology().setMorphemes(morphemeItems);
        return syngloss;
    }

    private List<WritingSystem> getWritingSystemsFromData(String language) {
        List<Map<String, AttributeValue>> writingSystemItems = dynamoDBService.getWritingSystems(language);
        return writingSystemItems.stream()
                .map(WritingSystem::getFromItem)
                .collect(Collectors.toList());
    }

    public List<DescendantLanguage> getDescendantLanguagesFromData(String parentLanguage) {
        List<Map<String, AttributeValue>> evolutionItems = dynamoDBService.getEvolutions(parentLanguage);
        List<Evolution> evolutions = Evolution.getListFromItemList(evolutionItems, parentLanguage + " data");
        return evolutions.stream()
                .map(this::getDescendantLanguageFromEvolution)
                .collect(Collectors.toList());
    }

    private DescendantLanguage getDescendantLanguageFromEvolution(Evolution evolution) {
        Map<String, AttributeValue> descendantLanguageItem;
        try {
            descendantLanguageItem = dynamoDBService.getLanguage(
                    evolution.getDescendantLanguage(), "DESCENDANT"
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return DescendantLanguage.getFromItemAndEvolution(descendantLanguageItem, evolution);
    }
}