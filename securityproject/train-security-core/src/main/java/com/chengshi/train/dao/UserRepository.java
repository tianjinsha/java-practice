package com.chengshi.train.dao;



import com.chenghshi.train.model.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    Map<String, Member> users=new HashMap();
    String[] role1={"ROLE_ADMIN","ROLE_BACK"};
    String[] role2={"ROLE_ADMIN"};
    String[] role3={"ROLE_BACK"};
    {
        users.put("zhangsan",new Member("zhangsan","$2a$10$q5Evb.wibLWiroHUA3tj9ekCBBGjkZ8D.wWLebYnYJeGX0D9lbFXu", Arrays.asList(role1)));
        users.put("lisi",new Member("lisi","$2a$10$UEf1ExTpyRQzlsIrpg4CCu5JVeA/D4g5p.7J7bg/.sgalhELPzpYa", Arrays.asList(role2)));
        users.put("wangwu",new Member("wangwu","$2a$10$i52AXBxdyVmWymFtKyt8WOdYMKficvQj6xMa8IGi3GjwjfEpe7qKu", Arrays.asList(role3)));
        users.put("jinshan",new Member("jinshan","$2a$10$i52AXBxdyVmWymFtKyt8WOdYMKficvQj6xMa8IGi3GjwjfEpe7qKu", Arrays.asList(role3)));
        users.put("xiaoshann",new Member("xiaoshan","$2a$10$i52AXBxdyVmWymFtKyt8WOdYMKficvQj6xMa8IGi3GjwjfEpe7qKu", Arrays.asList(role2)));
        users.put("豆腐渣工程",new Member("豆腐渣工程","$2a$10$i52AXBxdyVmWymFtKyt8WOdYMKficvQj6xMa8IGi3GjwjfEpe7qKu", Arrays.asList(role2)));
        users.put("东方不败",new Member("东方不败","$2a$10$i52AXBxdyVmWymFtKyt8WOdYMKficvQj6xMa8IGi3GjwjfEpe7qKu", Arrays.asList(role2)));
    }

    public void insert(Member member){
        users.put(member.getUsername(),member);
    }


    public Member findByUsername(String username){
        if (username.equals("") || username==null){
            return null;
        }
        Member member=users.get(username);
        return member;
    }

    public Integer getCount(){
        return users.size();
    }


    public static void main(String[] args) {
        UserRepository userRepository=new UserRepository();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword =encoder.encode("12345");

        System.out.println(rawPassword);
    }

}
