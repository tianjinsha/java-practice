package com.chengshi.train.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class User {
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String id;

    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

}
