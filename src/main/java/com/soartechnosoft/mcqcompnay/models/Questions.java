package com.soartechnosoft.mcqcompnay.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Questions {
    // quizapp.question (
    // org uuid,
    // exam uuid,
    // subject uuid,
    // year int,
    // id uuid,
    // "AnsDescription" text,
    // answertype frozen<answertype>,
    // correct_answer text,
    // option_four text,
    // option_one text,
    // option_three text,
    // option_two text,
    // question text,
    // PRIMARY KEY ((org, exam, subject), year, id)
    // )
    @PrimaryKeyClass
    @Data
    public class QuestionsKey implements Serializable {
        @PrimaryKeyColumn(name = "org", type = PrimaryKeyType.PARTITIONED)
        private String org;

        @PrimaryKeyColumn(name = "exam", type = PrimaryKeyType.PARTITIONED)
        private UUID exam;

        @PrimaryKeyColumn(name = "subject")
        private UUID subject;

        @PrimaryKeyColumn(name = "year")
        private Integer year;

        @PrimaryKeyColumn(name = "id")
        private UUID id;
    }

    @PrimaryKey
    private QuestionsKey key;

    @Column(value = "year")
     private Integer year;

     @Column(value = "ansDescription")
     private String ansDescription;

     @CassandraType(type = Name.UDT, userTypeName = "answertype") 
     private AnswerType answerType; 

     @Column(value = "correct_answer")
     private String correctAnswer;

     @Column(value = "option_four")
     private String optionFour;


     @Column(value = "option_one")
     private String optionOne;


     @Column(value = "option_three")
     private String optionThree;

     @Column(value = "option_two")
     private String optionTwo;

     @Column(value = "question")
     private String question;

}
