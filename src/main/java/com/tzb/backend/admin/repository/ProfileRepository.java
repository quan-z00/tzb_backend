package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Repository("fProfileRepository")
@SuppressWarnings("all")
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByUser_Id(Integer userId);

    List<Profile> findAllByNicknameIsLike(String nickname);
}
