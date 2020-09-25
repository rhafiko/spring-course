package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.RequestStage;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestStageRepository   extends JpaRepository<RequestStage, Long>{
	
	public List<RequestStage> findAllByRequestId(Long id);
	public Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE request_stage SET state = ?2 WHERE id =?1")
    public int updateStatus (Long id, RequestState state);	

}
