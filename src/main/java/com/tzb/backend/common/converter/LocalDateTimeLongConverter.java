package com.tzb.backend.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * @author 29002
 * @since 2024/5/17
 */
@Converter
public class LocalDateTimeLongConverter implements AttributeConverter<Long, LocalDateTime> {

    @Override
    public LocalDateTime convertToDatabaseColumn(Long attribute) {
        return attribute == null ? null : LocalDateTime.ofEpochSecond(attribute / 1000, 0, ZoneOffset.UTC);
    }

    @Override
    public Long convertToEntityAttribute(LocalDateTime dbData) {
        return dbData == null ? null : dbData.toEpochSecond(ZoneOffset.UTC) * 1000;
    }
}
