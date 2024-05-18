package com.tzb.backend.common.converter;

import com.alibaba.fastjson2.JSON;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

/**
 * 字符串列表转换器，用于将字符串列表转换为数据库字段存储，并在从数据库中读取时将其转换为列表对象。
 *
 * @author 29002
 * @see jakarta.persistence.AttributeConverter 属性转换器接口，用于将实体属性值和数据库列值相互转换。
 * @see jakarta.persistence.Converter JPA转换器注解，用于标记此类为JPA实体属性的转换器。
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    /**
     * 将列表对象转换为数据库列值。
     *
     * @param attribute 列表对象
     * @return 转换后的数据库列值
     */
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return JSON.toJSONString(attribute);
    }

    /**
     * 将数据库列值转换为列表对象。
     *
     * @param dbData 数据库列值
     * @return 转换后的列表对象
     */
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return JSON.parseArray(dbData, String.class);
    }
}
