package com.chengshi.train.jwt;

import com.chengshi.train.dao.UserRepository;
import com.chengshi.train.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user=userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new JwtUser(user.getUsername(), user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }

    public static void main(String[] args) {

        Map<String, Member> users=new HashMap();
        String[] role1={"admin","back"};
        String[] role2={"admin"};
        String[] role3={"back"};
        {
            users.put("zhangsan",new Member("zhangsan","123456", Arrays.asList(role1)));
            users.put("lisi",new Member("lisi","12345", Arrays.asList(role2)));
            users.put("wangwu",new Member("wangwu","1234567", Arrays.asList(role3)));
        }
        Member user=users.get("zhangsan");
        System.out.println("roles:"+user.getRoles());
        List<SimpleGrantedAuthority> collect = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        System.out.println("size::"+collect.size());
        collect.forEach(s-> System.out.println(s.getAuthority()));


    }
}
