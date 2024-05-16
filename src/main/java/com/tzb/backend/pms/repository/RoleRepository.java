package com.tzb.backend.pms.repository;


import com.tzb.backend.pms.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Role Mapper
 *
 * @author dhb
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByCode(String code);

    boolean existsByCodeOrName(String code, String name);


    Role getRoleById(Long id);

    void deleteAllById(Long id);

    boolean existsRoleById(Long id);

    List<Role> findAllByEnable(Boolean enable);
    List<Role> findAllBy();
}
