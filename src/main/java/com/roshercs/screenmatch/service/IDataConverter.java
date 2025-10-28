package com.roshercs.screenmatch.service;

public interface IDataConverter {
    //<T> T defines generic classes 
    <T> T obtainData(String json, Class<T> classType);
}
