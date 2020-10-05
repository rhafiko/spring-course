package com.springcourse.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.dto.UserSaveDto;
import com.springcourse.dto.UserUpdateDto;
import com.springcourse.dto.UserUpdateRoleDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.JwtManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;
	
	
	@PostMapping
	public ResponseEntity<User> save (@RequestBody @Valid UserSaveDto user){
		User createdUser = userService.save(user.transformToUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update (@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userDto){
		User user = userDto.transformToUser();
		user.setId(id);
		User updatedUser = userService.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById (@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll (@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody @Valid UserLoginDto user){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication auth  = authManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring =
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities()
										.stream()
										.map(authority -> authority.getAuthority())
										.collect(Collectors.toList());
		
		String jwt = jwtManager.createToken(email, roles);
		return ResponseEntity.ok(jwt);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById (@PathVariable(name = "id") Long id, 
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole (@RequestBody @Valid UserUpdateRoleDto userDto, @PathVariable(name = "id") Long id){
		User user = new  User();
		user.setId(id);
		user.setRole(userDto.getRole());
		userService.updateRole(user);
		return ResponseEntity.ok().build();
	}
	
	
}
