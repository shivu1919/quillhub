package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.TempUser;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, String>{

	List<TempUser> findByEmail(String email);
}
