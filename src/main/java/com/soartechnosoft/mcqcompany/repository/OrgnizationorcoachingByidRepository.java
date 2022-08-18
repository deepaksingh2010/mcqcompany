package com.soartechnosoft.mcqcompany.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.soartechnosoft.mcqcompnay.models.OrgnizationorcoachingByid;

public interface OrgnizationorcoachingByidRepository
        extends CassandraRepository<OrgnizationorcoachingByid, OrgnizationorcoachingByid.OrgnizationorcoachingByIdKey> {

}
