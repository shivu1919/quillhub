package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Otp;
import com.entity.TempUser;
import com.entity.User;
import com.repository.OtpRepository;
import com.repository.TempUserRepository;
import com.repository.UserRepository;
import com.service.OtpService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173/", "https://quillhubf-2phv.vercel.app/"})
public class UserController {
	
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private TempUserRepository tempUserRepository;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signup")
	public String signup(@RequestBody TempUser user) {
		int val = otpService.sendOtp(user.getEmail());
		
		Otp otp = new Otp();
		otp.setEmail(user.getEmail());
		otp.setOtp(val);
		
		otpRepository.save(otp);
		tempUserRepository.save(user);
		
		return "verify your otp";
	}
	
	
	@PostMapping("/verify-email")
	public boolean verifyEmail(@RequestBody Otp otp) {
		
		List<Otp> list = otpRepository.findByEmailAndOtp(otp.getEmail(), otp.getOtp());
		List<TempUser> tUser = tempUserRepository.findByEmail(otp.getEmail());
		if(list.size()==1) {
			
			User user = new User();
			
			
			user.setUsername(tUser.get(0).getUsername());
			user.setName(tUser.get(0).getName());
			user.setPassword(tUser.get(0).getPassword());
			user.setMob(tUser.get(0).getMob());
			user.setGender(tUser.get(0).getGender());
			user.setEmail(tUser.get(0).getEmail());
			userRepository.save(user);
			
			otpRepository.delete(otp);
			tempUserRepository.delete(tUser.get(0));
			return true;
		}
		
		tempUserRepository.delete(tUser.get(0));
		otpRepository.delete(otp);
		return false;
	}
	
	
	@PostMapping("/login")
	public User login(@RequestParam String email, @RequestParam String password) {
		
		List<User> list = userRepository.findByEmailAndPassword(email, password);
		
		if(list.size()==1) {
			return list.get(0);
		}
		
		return null;
	}
	
	
	@PostMapping("/check-username")
	public boolean checkUsername(@RequestParam String username) {
		System.out.println(username);
		Optional<User> user = userRepository.findById(username);
		
		if(user.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
 }







