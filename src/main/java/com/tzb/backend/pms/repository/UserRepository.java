package com.tzb.backend.pms.repository;


import com.tzb.backend.pms.domain.dto.UserPageDto;
import com.tzb.backend.pms.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * UserMapper
 *
 * @author dhb
 */
public interface UserRepository extends JpaRepository<User, Long> {

//    /**
//     * 分页查询
//     *
//     * @param pageable 分页
//     * @param username 用户名
//     * @param gender   性别
//     * @param enable   状态
//     * @return 分页结果
//     */
//    @Query("SELECT new com.tzb.backend.pms.domain.dto.UserPageDto(u.id, u.username, u.enable, u.createTime, u.updateTime, p.gender, p.avatar, p.address, p.email, p.roles) " +
//            "FROM user u JOIN u.profile p " +
//            "WHERE (:username IS NULL OR u.username = :username) " +
//            "AND (:gender IS NULL OR p.gender = :gender) " +
//            "AND (:enable IS NULL OR u.enable = :enable)")
//    Page<UserPageDto> findAllByUsernameAndGenderAndEnable(String username, Integer gender, Boolean enable, Pageable pageable);

    User findByUsername(String username);

    User findUserById(Long id);

    boolean existsByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

}
