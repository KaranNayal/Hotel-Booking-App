package com.hexaware.CozyHeavenStay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.CozyHeavenStay.entities.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findByUserName(String userName);

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);

}
