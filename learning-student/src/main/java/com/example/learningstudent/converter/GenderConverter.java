package com.example.learningstudent.converter;

import com.example.learningstudent.enums.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getDbValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.getGenderByValue(dbData).orElse(Gender.BOY);
    }
}