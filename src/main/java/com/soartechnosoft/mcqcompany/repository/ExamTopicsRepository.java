package com.soartechnosoft.mcqcompany.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.soartechnosoft.mcqcompnay.models.ExamTopics;


@Repository
public interface ExamTopicsRepository extends CassandraRepository<ExamTopics,ExamTopics.ExamTopicsKey> {

       @Query("SELECT * FROM examtopics WHERE exam = ?0 and subject = ?1")
       List<ExamTopics> findByExamBySubject(String exam,String subject);

    // @Query("SELECT * FROM examtopics")
    // List<ExamTopics> findAll();
}
