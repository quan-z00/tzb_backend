package com.tzb.backend.pms.repository;



import com.tzb.backend.pms.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Permission Mapper
 *
 * @author dhb
 */
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    Permission findAllById(Long id);

    List<Permission> findAllByType(String type);

    List<Permission> findAllByTypeOrderByOrderAsc(String type);

    List<Permission> findAllByTypeAndEnableOrderByOrderAsc(String type, Boolean enable);

    List<Permission> findAllByParentIdAndTypeInOrderByOrderAsc(Long parentId, List<String> types);

    boolean existsByPath(String path);

    void deletePermissionById(Long id);


}
