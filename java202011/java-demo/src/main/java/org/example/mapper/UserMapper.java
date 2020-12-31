package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.Role;
import org.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/13 21:54
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * 将User成UserDTO
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(source = "roles", target = "roles", qualifiedByName = "convertRoleListToString")
    })
    UserDto findOneUser(User user);


    /**
     * 将权限列表装换为字符串列表
     *
     * @param roleList
     * @return
     */
    @Named("convertRoleListToString")
    default List<String> convertRoleListToString(List<Role> roleList) {
        List<String> stringList = roleList
                .stream().
                        map(item -> item.getCode()).collect(Collectors.toList());
        return stringList;
    }
}
