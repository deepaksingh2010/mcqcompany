package com.soartechnosoft.mcqcompany.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.soartechnosoft.mcqcompnay.models.Papers;

public interface PapersRepository extends CassandraRepository<Papers, Papers.PapersKey> {
    @Query("SELECT * FROM paper WHERE org = ?0 and exam = ?1")
    List<Papers> findByOrgByExam(UUID orgid,UUID examid);
}
