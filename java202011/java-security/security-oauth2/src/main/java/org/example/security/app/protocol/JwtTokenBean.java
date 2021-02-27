package org.example.security.app.protocol;

import lombok.*;
import org.example.core.protocol.CommonBean;

/**
 * @author tjstj
 * @description TODO
 * @date 2021/2/9 15:46
 */
@Setter
@Getter
public class JwtTokenBean extends CommonBean {
    String token;

    public JwtTokenBean(String code,String message ,String token){
        super(code,message);
        this.token = token;
    }

}
