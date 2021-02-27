package org.example.core.protocol;

import lombok.*;

import java.io.Serializable;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/22 17:28
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonBean implements Serializable {
    private String code;
    private String message;
    private String error;

    public CommonBean(String code,String message){
        this.code = code;
        this.message = message;
    }
}
