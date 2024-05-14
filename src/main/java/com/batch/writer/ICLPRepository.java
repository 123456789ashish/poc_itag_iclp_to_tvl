package com.batch.writer;

import com.batch.model.ICLPEntity;
import com.batch.model.ITAGEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICLPRepository extends JpaRepository<ICLPEntity, Integer> {
}
