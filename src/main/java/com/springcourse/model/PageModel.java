package com.springcourse.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageModel<T> implements Serializable {
	private static final long serialVersionUID = -8011306379701830327L;
	
	private int totalElements;
	private int pageSize;
	private int totalPages;
	private List<T> elements;
}
