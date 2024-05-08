package com.tzb.backend.service;

import com.tzb.backend.domain.SpecialExhibit;
import com.tzb.backend.dto.projection.ArtifactCardProjection;

import java.util.List;

/**
 * @author 29002
 */
public interface SpecialExhibitService {
    List<ArtifactCardProjection> getSpecialExhibits();
    SpecialExhibit getSpecialExhibitById(int id);
}
