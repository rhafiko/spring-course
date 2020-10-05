package com.springcourse.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

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
public class UserUpdateRoleDto {
	
	@NotNull(message = "Role is required")
	private Role role;

}
