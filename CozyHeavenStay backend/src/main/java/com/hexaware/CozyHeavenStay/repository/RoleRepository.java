package com.hexaware.CozyHeavenStay.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.CozyHeavenStay.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}