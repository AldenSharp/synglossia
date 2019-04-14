package service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

class DynamoDBService {

    List<Map<String, AttributeValue>> getAllLanguages() {
        return dynamoDB().scan(new ScanRequest()
                .withTableName("Language")
                .withIndexName("type-index")
        ).getItems();
    }

    Map<String, AttributeValue> getLanguage(String name) throws IOException {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#name", "name");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":name", new AttributeValue(name));
        List<Map<String, AttributeValue>> items = dynamoDB().query(new QueryRequest()
                .withTableName("Language")
                .withKeyConditionExpression("#name = :name")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
        if (items.isEmpty()) {
            throw new IOException("Language '" + name + "' is not found in the database.");
        }
        return items.get(0);
    }

    Map<String, AttributeValue> getDescendantLanguage(String name) throws IOException {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#name", "name");
        nameMap.put("#type", "type");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":name", new AttributeValue(name));
        valueMap.put(":type", new AttributeValue("DESCENDANT"));
        List<Map<String, AttributeValue>> items = dynamoDB().query(new QueryRequest()
                .withTableName("Language")
                .withKeyConditionExpression("#name = :name")
                .withFilterExpression("#type = :type")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
        if (items.isEmpty()) {
            throw new IOException("Language '" + name + "' of type 'DESCENDANT' is not found in the database.");
        }
        return items.get(0);
    }

    List<Map<String, AttributeValue>> getEvolutions(String parentLanguage) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#parentLanguage", "parentLanguage");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":parentLanguage", new AttributeValue(parentLanguage));
        return dynamoDB().query(new QueryRequest()
                .withTableName("Evolution")
                .withIndexName("parentLanguage-index")
                .withKeyConditionExpression("#parentLanguage = :parentLanguage")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    List<Map<String, AttributeValue>> getAncestorEvolutions(String descendantLanguage) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#descendantLanguage", "descendantLanguage");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":descendantLanguage", new AttributeValue(descendantLanguage));
        return dynamoDB().query(new QueryRequest()
                .withTableName("Evolution")
                .withKeyConditionExpression("#descendantLanguage = :descendantLanguage")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    List<Map<String, AttributeValue>> getWritingSystems(String language) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#language", "language");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":language", new AttributeValue(language));
        return dynamoDB().query(new QueryRequest()
                .withTableName("WritingSystem")
                .withIndexName("language-index")
                .withKeyConditionExpression("#language = :language")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    List<Map<String, AttributeValue>> getGrammaticalMorphemes(String language) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#language", "language");
        nameMap.put("#function", "function");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":language", new AttributeValue(language));
        valueMap.put(":function", new AttributeValue("GRAMMATICAL"));
        return dynamoDB().query(new QueryRequest()
                .withTableName("Morpheme")
                .withKeyConditionExpression("#language = :language")
                .withFilterExpression("#function = :function")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    List<Map<String, AttributeValue>> getSemanticMorphemes(String language) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#language", "language");
        nameMap.put("#function", "function");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":language", new AttributeValue(language));
        valueMap.put(":function", new AttributeValue("SEMANTIC"));
        return dynamoDB().query(new QueryRequest()
                .withTableName("Morpheme")
                .withKeyConditionExpression("#language = :language")
                .withFilterExpression("#function = :function")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    List<Map<String, AttributeValue>> getAttestations(String language) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#language", "language");
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":language", new AttributeValue(language));
        return dynamoDB().query(new QueryRequest()
                .withTableName("Attestation")
                .withKeyConditionExpression("#language = :language")
                .withExpressionAttributeNames(nameMap)
                .withExpressionAttributeValues(valueMap)
        ).getItems();
    }

    private AmazonDynamoDB dynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}