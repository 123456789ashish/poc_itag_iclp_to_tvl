package com.batch.writer;

import com.batch.model.ITAGEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITAGRepository extends JpaRepository<ITAGEntity, Integer> {
}
