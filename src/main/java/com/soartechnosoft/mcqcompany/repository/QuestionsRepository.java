package com.soartechnosoft.mcqcompany.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.soartechnosoft.mcqcompnay.models.Questions;

public interface QuestionsRepository extends CassandraRepository<Questions,Questions.QuestionsKey>{

}
