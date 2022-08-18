package com.soartechnosoft.mcqcompany.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.soartechnosoft.mcqcompnay.models.Exams;

public interface ExamsRepository extends CassandraRepository<Exams,Exams.ExamsKey> {
    
}
