package com.springcourse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLoginResponseDto implements Serializable {

	private static final long serialVersionUID = -8547409389529499744L;
	
	private String token;
	private Long expireIn;
	private String tokenProvider;
}
