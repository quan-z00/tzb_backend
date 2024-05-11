package com.tzb.backend.exhibit.service;

import com.tzb.backend.exhibit.domain.SpecialExhibit;
import com.tzb.backend.exhibit.domain.dto.projection.ArtifactCardProjection;

import java.util.List;

/**
 * @author 29002
 */
public interface SpecialExhibitService {
    List<ArtifactCardProjection> getSpecialExhibits();
    SpecialExhibit getSpecialExhibitById(int id);
}
