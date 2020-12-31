package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 16:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private boolean isExpired;
    private boolean isLocked;
    private boolean isEnabled;
    List<String> roles;
}
