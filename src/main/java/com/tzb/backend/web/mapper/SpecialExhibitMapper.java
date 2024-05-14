package com.tzb.backend.web.mapper;

import com.tzb.backend.web.domain.dto.SpecialExhibitCardDto;
import com.tzb.backend.web.domain.entity.SpecialExhibit;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface SpecialExhibitMapper {
    SpecialExhibitCardDto toCardDto(SpecialExhibit specialExhibit);
}
