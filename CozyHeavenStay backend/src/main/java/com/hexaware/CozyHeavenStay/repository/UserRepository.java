package com.hexaware.CozyHeavenStay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
    List<User> findByGender(String gender);
  
	List<User> findByAddress(String address);

	List<User> findByContactNo(String contactNo);

	Optional<User> findByUserName(String userName);

	List<User> findByUserNameContaining(String keyword);
	
    Boolean existsByEmail(String email);
    Optional<User> findByuserNameOrEmail(String username, String email);
 
    Optional<User> findByEmail(String email);
	Boolean existsByUserName(String userName);
	Boolean existsByContactNo(String contactNo);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId")
    List<User> findAdminUsers(@Param("roleId") Long roleId);
	 
	
  }


