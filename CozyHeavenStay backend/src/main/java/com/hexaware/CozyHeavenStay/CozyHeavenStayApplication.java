package com.hexaware.CozyHeavenStay;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hexaware.CozyHeavenStay.entities.Role;
import com.hexaware.CozyHeavenStay.repository.RoleRepository;



@SpringBootApplication
@EnableScheduling
public class CozyHeavenStayApplication  {
//	implements CommandLineRunner
	public static void main(String[] args) {
		SpringApplication.run(CozyHeavenStayApplication.class, args);
	}

	@Autowired
    private RoleRepository rolerepo;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Override
//	public void run(String... args) throws Exception{
//		Role roleObj=new Role(1L,"ROLE_USER");
//		Role roleObj2=new Role(2L,"ROLE_ADMIN");
//		Role roleObj3=new Role(3L,"ROLE_OWNER");
//
//		rolerepo.save(roleObj);
//		rolerepo.save(roleObj2);
//		rolerepo.save(roleObj3);	
//	}
	
}
