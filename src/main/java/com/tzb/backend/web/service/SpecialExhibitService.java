package com.tzb.backend.web.service;

import com.tzb.backend.web.domain.dto.SpecialExhibitCardDto;
import com.tzb.backend.web.domain.entity.SpecialExhibit;

import java.util.List;

/**
 * @author 29002
 */
public interface SpecialExhibitService {
    List<SpecialExhibitCardDto> getSpecialExhibits();
    SpecialExhibit getSpecialExhibitById(int id);
}
