package com.soartechnosoft.mcqcompany.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.soartechnosoft.mcqcompnay.models.Enrollments;


public interface EnrollmentsRepository extends CassandraRepository<Enrollments,Enrollments.EnrollmentsKey> {

    public interface EnrollmentStatus {
        String getStatus();
    }

    @Query("SELECT * FROM enrollments WHERE org = ?0 and exam = ?1")
    List<Enrollments> findAllByOrgByExam(UUID org, UUID exam);

    @Query("SELECT * FROM enrollments WHERE org = ?0 and exam = ?1 and paperid = ?2 and studentemailid = ?3")
    List<Enrollments> findAllByOrgByExamByPaperidByStudentemailid(UUID org, UUID exam,UUID paperid,String studentemailid);
    
    @Query("SELECT status FROM enrollments WHERE org = ?0 and exam = ?1 and paperid = ?2 and studentemailid = ?3")
    List<EnrollmentStatus> findStatusByOrgByExamByPaperidByStudentemailid(UUID org, UUID exam,UUID paperid,String studentemailid);
    
}

