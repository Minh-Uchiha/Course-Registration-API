package com.minhuchiha.SBSimpleCRUDApp.Enum.Converter;

import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

import static java.util.spi.ToolProvider.findFirst;

@Converter(autoApply = true)
public class CourseDifficultyConverter implements AttributeConverter<CourseDifficulty, String> {
    @Override
    public String convertToDatabaseColumn(CourseDifficulty courseDifficulty) {
        if (courseDifficulty == null) return null;
        return courseDifficulty.getCode();
    }

    @Override
    public CourseDifficulty convertToEntityAttribute(String code) {
        if (code == null) return null;
        return Stream.of(CourseDifficulty.values()).
                filter(c -> c.getCode().equals(code)).findFirst().orElseThrow(IllegalAccessError::new);
    }
}
