package com.tzb.backend.repository;

import com.tzb.backend.domain.SpecialExhibit;
import com.tzb.backend.dto.projection.ArtifactCardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 29002
 */
@Repository
public interface SpecialExhibitRepository extends JpaRepository<SpecialExhibit, Integer> {
    SpecialExhibit findSpecialExhibitById(int id);

    List<ArtifactCardProjection> findAllBy();
}
