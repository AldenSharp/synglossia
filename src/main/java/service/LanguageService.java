package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import model.Syngloss;
import model.SynglossType;
import model.evolution.*;
import model.writingSystem.WritingSystem;

public class LanguageService {

    private DynamoDBService dynamoDBService = new DynamoDBService();

    public List<String> getLanguageNames() {
        return dynamoDBService.getAllLanguages()
                .stream()
                .map(item -> item.get("name").getS())
                .sorted()
                .collect(Collectors.toList());
    }

    public Syngloss getSyngloss(String languageName) throws IOException {
        Map<String, AttributeValue> languageItem = dynamoDBService.getLanguage(languageName);
        Syngloss syngloss = Syngloss.getFromItem(languageItem);
        syngloss.setDescendantLanguages(getDescendantLanguagesFromData(languageItem.get("name").getS()));
        syngloss.setAncestorLanguages(getAncestorLanguagesFromData(languageItem.get("name").getS()));
        syngloss.setWritingSystems(getWritingSystemsFromData(languageName));
        if (syngloss.getType().equals(SynglossType.PARENT)) {
            List<Map<String, AttributeValue>> morphemeItems = dynamoDBService.getGrammaticalMorphemes(languageName);
            syngloss.getMorphology().setMorphemes(morphemeItems);
        }
        return syngloss;
    }

    private List<WritingSystem> getWritingSystemsFromData(String language) {
        List<Map<String, AttributeValue>> writingSystemItems = dynamoDBService.getWritingSystems(language);
        return writingSystemItems.stream()
                .map(WritingSystem::getFromItem)
                .collect(Collectors.toList());
    }

    public List<DescendantLanguage> getDescendantLanguagesFromData(String parentLanguage) {
        List<Map<String, AttributeValue>> evolutionItems = dynamoDBService.getDescendantEvolutions(parentLanguage);
        List<Evolution> evolutions = Evolution.getListFromItemList(evolutionItems, parentLanguage + " data");
        return evolutions.stream()
                .map(this::getDescendantLanguageFromEvolution)
                .collect(Collectors.toList());
    }

    private DescendantLanguage getDescendantLanguageFromEvolution(Evolution evolution) {
        Map<String, AttributeValue> descendantLanguageItem;
        try {
            descendantLanguageItem = dynamoDBService.getLanguage(
                    evolution.getDescendantLanguage()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return DescendantLanguage.getFromItemAndEvolution(descendantLanguageItem, evolution);
    }

    public List<AncestorLanguage> getAncestorLanguagesFromData(String descendantLanguage) {
        List<Map<String, AttributeValue>> evolutionItems = dynamoDBService.getAncestorEvolutions(descendantLanguage);
        List<Evolution> evolutions = Evolution.getListFromItemList(evolutionItems, descendantLanguage + " data");
        return evolutions.stream()
                .map(this::getAncestorLanguageFromEvolution)
                .collect(Collectors.toList());
    }

    private AncestorLanguage getAncestorLanguageFromEvolution(Evolution evolution) {
        Map<String, AttributeValue> ancestorLanguageItem;
        try {
            ancestorLanguageItem = dynamoDBService.getLanguage(
                    evolution.getParentLanguage()
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return AncestorLanguage.getFromItemAndEvolution(ancestorLanguageItem, evolution);
    }
}