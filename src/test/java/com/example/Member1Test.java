package com.example;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.domain.Member;
import com.example.domain.MemberRole;
import com.example.persistence.MemberRepository;
import java.util.ArrayList;
import java.util.List;

@Log
@Commit
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Member1Test {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@Test
	public void testAdminInsert() {
		//given
		Member member = new Member();
		MemberRole role = new MemberRole();
		//when
		member.setUid("admin1125");
		member.setUpw("pw1125");
		member.setUname("admin");
		
		role.setRoleName("ADMIN");
		List<MemberRole> list = new ArrayList<>();
		list.add(role);
		
		member.setRoles(list);
		//then
		repo.save(member);
		
	}

}
