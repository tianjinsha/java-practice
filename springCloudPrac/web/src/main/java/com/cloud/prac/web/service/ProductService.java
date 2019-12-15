package com.cloud.prac.web.service;

import com.cloud.prac.web.model.Product;
import com.cloud.prac.web.sercurity.AdminOnly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tjshan
 * @date 2019/10/4 12:20
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private AuthService authService;

//    @AdminOnly
    public void insert(Product product) {
//        authService.checkAccess();
        log.info("insert product");
    }

//    @AdminOnly
    public void delete(Long id) {
//        authService.checkAccess();
        log.info("delete Product:" + id);
    }
}
