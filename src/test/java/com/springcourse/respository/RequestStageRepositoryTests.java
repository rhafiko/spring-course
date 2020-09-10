package com.springcourse.respository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestStageRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTests {
	@Autowired private RequestStageRepository requestStageRepository;
	
	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(1L);

		Request request = new Request();
		request.setId(1L);		
		
		RequestStage requestStage = new RequestStage(null, "Foi comprado um novo laptop de marca HP e com 16 GB de RAM", new Date(), RequestState.CLOSED, request, owner);
		RequestStage createdRequestStage = requestStageRepository.save(requestStage);
		
		assertThat(createdRequestStage.getId()).isEqualTo(1L);		
	}
	
	@Test
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage request = result.get();
		
		assertThat(request.getDescription()).isEqualTo("Foi comprado um novo laptop de marca HP e com 16 GB de RAM");
	}
	
	@Test
	public void listByRequestIdTest() {
		List<RequestStage> result = requestStageRepository.findAllByRequestId(1L);
		assertThat(result.size()).isEqualTo(1);		
	}

	@Test
	public void updateStatusTest() {
		int affectedRows = requestStageRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
	}	
	
}
