package com.soartechnosoft.mcqcompany.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.soartechnosoft.mcqcompnay.models.Orgnizationorcoaching;

public interface OrgnizationOrCoachingRepository extends CassandraRepository<Orgnizationorcoaching,Orgnizationorcoaching.EOrgnizationorcoachingKey> {
    
}
