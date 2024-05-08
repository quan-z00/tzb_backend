package com.tzb.backend.service.impl;

import com.tzb.backend.domain.SpecialExhibit;
import com.tzb.backend.dto.projection.ArtifactCardProjection;
import com.tzb.backend.repository.SpecialExhibitRepository;
import com.tzb.backend.service.SpecialExhibitService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 29002
 */
@Service
public class SpecialExhibitServiceImpl implements SpecialExhibitService {

    private final SpecialExhibitRepository specialExhibitRepository;

    public SpecialExhibitServiceImpl(SpecialExhibitRepository specialExhibitRepository) {
        this.specialExhibitRepository = specialExhibitRepository;
    }

    @Override
    public List<ArtifactCardProjection> getSpecialExhibits() {
        return specialExhibitRepository.findAllBy();
    }

    @Override
    public SpecialExhibit getSpecialExhibitById(int id) {
        return specialExhibitRepository.findSpecialExhibitById(id);
    }
}
