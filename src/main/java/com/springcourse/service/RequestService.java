package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;

@Service	
public class RequestService {
	@Autowired private RequestRepository requestRepository;
	
	
	public Request save(Request request) {		
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
	
	public Request update(Request request) {
		
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;
		
	}

	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}
	
	public List<Request> listAll(){
		List<Request> requests = requestRepository.findAll();
		return requests;
	}

	
	public List<Request> listAllByOwner(Long ownerId){
		
		List<Request> requestsByOwner = requestRepository.findAllByOwnerId(ownerId);
		return requestsByOwner;
	}
	
}