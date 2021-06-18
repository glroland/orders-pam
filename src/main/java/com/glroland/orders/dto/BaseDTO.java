package com.glroland.orders.dto;

import java.io.Serializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public abstract class BaseDTO implements Serializable
{
    @Override
    public String toString() 
    {
        ObjectMapper mapper = new ObjectMapper();

        try 
        {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } 
        catch (IOException e) 
        {
            throw new RuntimeException("Unable to serialize DTO to JSON", e);
        }
    }
}
