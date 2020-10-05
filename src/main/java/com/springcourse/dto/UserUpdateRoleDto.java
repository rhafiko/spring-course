package com.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.springcourse.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDto {
	
	@NotNull(message = "Role is required")
	private Role role;

}
