package com.chengshi.train.dao;

import com.chengshi.train.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 22:30
 */
@Repository
public interface RolePermissionRepository  extends JpaRepository<RolePermission,Integer> {

    @Query(value = "select permission from role_permissions where role_name=:roleName",nativeQuery = true)
    List<String> findPermissionByRoleName(@Param("roleName") String roleName);

    int deleteRolePermissionById(Integer id);

    int deleteRolePermissionByPermissionAndRoleName(String permission ,String roleName);

}
