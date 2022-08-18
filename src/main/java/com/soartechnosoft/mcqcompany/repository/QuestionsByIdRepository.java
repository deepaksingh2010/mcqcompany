package com.soartechnosoft.mcqcompany.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.soartechnosoft.mcqcompnay.models.QuestionsById;

public interface QuestionsByIdRepository extends CassandraRepository<QuestionsById, QuestionsById.QuestionsByIdKey> {

    @Query("select * from questionbyid where id = ?0")
    List<QuestionsById> findById(UUID id);

}
