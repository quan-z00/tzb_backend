package com.tzb.backend.web.repository;

import com.tzb.backend.web.domain.entity.SpecialExhibit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 29002
 */
@Repository
public interface SpecialExhibitRepository extends JpaRepository<SpecialExhibit, Integer> {
    SpecialExhibit findSpecialExhibitById(int id);

    List<SpecialExhibit> findAllBy();
}
