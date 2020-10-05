package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserSaveDto {
	
	@NotBlank(message = "name required")
	private String name;
	
	@Email(message = "invalid email address")
	private String email;
	
	@Size(min = 7, max = 99, message = "password must be between 7 and 99 lenght")
	private String password;
	
	@NotNull
	private Role role;
	
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public  User transformToUser() {
		User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
		return user;		
	}
}
