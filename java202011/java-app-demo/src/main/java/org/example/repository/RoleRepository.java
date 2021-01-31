package org.example.repository;

import org.example.entity.Role;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 20:50
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    /**
     * 根据授权代码查找角色
     * @param code 角色代码
     * @return Role
     */
   Role findByCode(String code);

    /**
     * 根据授权代码查找角色
     * @param name  角色名称
     * @return Role
     */
   Role findByName(String name);

}
