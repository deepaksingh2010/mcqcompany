package com.soartechnosoft.mcqcompnay.models;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@UserDefinedType("answertype") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerType {
    
    @CassandraType(type = Name.TEXT) 
    private String answertypename; 
}
