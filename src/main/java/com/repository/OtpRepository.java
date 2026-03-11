package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String>{

	List<Otp> findByEmailAndOtp(String email, int otp);
}
