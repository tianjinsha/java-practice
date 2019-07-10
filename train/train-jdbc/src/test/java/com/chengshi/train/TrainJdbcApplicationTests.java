package com.chengshi.train;

import com.chengshi.train.dao.IRoleDao;
import com.chengshi.train.dao.IRolePermissionDao;
import com.chengshi.train.dao.IUserDao;
import com.chengshi.train.dao.IUserRoleDao;
import com.chengshi.train.util.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TrainJdbcApplicationTests {

    @Autowired
    IUserDao userDao;
    @Autowired
    IRoleDao roleDao;

    @Autowired
    IUserRoleDao userRoleDao;

    @Autowired
    IRolePermissionDao rolePermissionDao;

    @Test
    public void contextLoads() {

        log.info("user:"+userDao.findUserByName("admin"));
        log.info("users:"+userDao.findUserList());

        log.info("role:"+roleDao.findRoleList());

        log.info("userRole:"+userRoleDao.findRoles("admin"));

        log.info("permission:"+rolePermissionDao.getPermissions("admin"));
    }

    @Test
    public void testPage(){
        log.info("start");
        Page page=userDao.page(2,2);
        log.info("page:"+page);
        log.info(""+page.getTotalRows());
        List records = page.getRecords();
        log.info("record:"+ records.toString());
    }

}

