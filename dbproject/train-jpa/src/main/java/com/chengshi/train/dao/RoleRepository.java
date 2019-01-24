package com.chengshi.train.dao;

import com.chengshi.train.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: tian
 * @date: 2019-01-13 16:43
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>,JpaSpecificationExecutor<Role> {

    int deleteRoleById(Integer id);

    int deleteRoleByRoleName(String roleName);

    Role findRoleByRoleName(String roleName);

    @Transactional
    @Modifying
    @Query(value = "update roles set description=:description where role_name=:roleName", nativeQuery = true)
    int updateRole(@Param("description") String description,@Param("roleName") String roleName);

}
