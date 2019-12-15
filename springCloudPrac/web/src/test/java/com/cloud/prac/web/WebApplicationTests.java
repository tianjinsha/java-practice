package com.cloud.prac.web;

import com.cloud.prac.web.model.Product;
import com.cloud.prac.web.sercurity.CurrentUserHolder;
import com.cloud.prac.web.service.ProductService;
import org.assertj.core.util.introspection.ClassUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    ProductService productService;

    @Test
    public void contextLoads() {
    }

    @Test(expected = Exception.class)
    public void checkInsertAuth(){
        CurrentUserHolder.set("jack");
        productService.insert(new Product());
    }

    @Test
    public void checkDeleteAuth(){
        CurrentUserHolder.set("admin");
        productService.delete(1L);
        
    }

}
