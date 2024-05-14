package com.tzb.backend.web.service.impl;

import com.tzb.backend.web.domain.dto.SpecialExhibitCardDto;
import com.tzb.backend.web.domain.entity.SpecialExhibit;
import com.tzb.backend.web.mapper.SpecialExhibitMapper;
import com.tzb.backend.web.repository.SpecialExhibitRepository;
import com.tzb.backend.web.service.SpecialExhibitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 29002
 */
@Service
@RequiredArgsConstructor
public class SpecialExhibitServiceImpl implements SpecialExhibitService {

    private final SpecialExhibitRepository specialExhibitRepository;
    private final SpecialExhibitMapper specialExhibitMapper;

    @Override
    public List<SpecialExhibitCardDto> getSpecialExhibits() {
        return specialExhibitRepository.findAllBy().stream().map(specialExhibitMapper::toCardDto).toList();
    }

    @Override
    public SpecialExhibit getSpecialExhibitById(int id) {
        return specialExhibitRepository.findSpecialExhibitById(id);
    }
}
