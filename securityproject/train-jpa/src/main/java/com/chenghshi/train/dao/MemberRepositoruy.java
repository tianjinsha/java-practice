package com.chenghshi.train.dao;

import com.chenghshi.train.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepositoruy extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member> {


}
