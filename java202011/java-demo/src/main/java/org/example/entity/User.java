package org.example.entity;

import lombok.*;
import org.example.dto.UserDto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 14:26
 */

//@SqlResultSetMapping(
//        name = "userDtoMap",
//        classes = {
//                @ConstructorResult(targetClass = UserDto.class,
//                        columns = {
//                                @ColumnResult(name = "id", type = Long.class),
//                                @ColumnResult(name = "username", type = String.class),
//                                @ColumnResult(name = "email", type = String.class),
//                                @ColumnResult(name = "phone", type = String.class),
//                                @ColumnResult(name = "password", type = String.class),
//                                @ColumnResult(name = "isExpired", type = String.class),
//                                @ColumnResult(name = "isLocked", type = String.class),
//                                @ColumnResult(name = "isEnabled", type = String.class),
//                                @ColumnResult(name = "isEnabled", type = String.class),
//                                @ColumnResult(name = "roles", type = List.class)
//                        }
//                )
//        }
//)
//
//@NamedNativeQuery(
//        name = "User.findAllDTO",
//        query = "select * from user left join role ON user.role",
//        resultSetMapping = "userDtoMap"
//)
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column
    private String password;
    @Column
    private boolean isExpired;
    @Column
    private boolean isLocked;
    @Column
    private boolean isEnabled;
    @Column
    private Date createTime;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_code")
    )
    private List<Role> roles;
}
