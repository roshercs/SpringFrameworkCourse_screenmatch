package com.roshercs.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roshercs.screenmatch.models.DataSerie;

public class DataConverter implements IDataConverter{
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public <T> T obtainData(String json, Class<T> classType) {
        try {
            return objectMapper.readValue(json,classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
