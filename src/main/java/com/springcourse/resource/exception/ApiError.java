package com.springcourse.resource.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class ApiError implements Serializable{
	
	private static final long serialVersionUID = 2728003449049217202L;
	
	private int code;
	private String msg;
	private Date date;

}
