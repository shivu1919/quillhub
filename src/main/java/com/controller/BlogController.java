package com.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Blog;
import com.entity.User;
import com.repository.BlogRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins="http://localhost:5173/")
public class BlogController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PostMapping("/create")
	public boolean createBlog(
			@RequestParam String title,
			@RequestParam String body,
			@RequestParam String username
			) {
		
		Optional<User> user = userRepository.findById(username);
		
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setBody(body);
		blog.setDate(LocalDate.now(ZoneId.of("Asia/Kolkata")));
		blog.setTime(LocalTime.now(ZoneId.of("Asia/Kolkata")));
		blog.setUser(user.get());
		
		blogRepository.save(blog);
		
		return true;
	}
	
	
	@PostMapping("/read")
	public List<Blog> getAllBlogs(){
		List<Blog> list = blogRepository.findAll();
		
		List<Blog> result = new ArrayList<>();
		
		for(int i=list.size()-1; i>=0; i--) {
			result.add(list.get(i));
		}
		return result;
	}
}













