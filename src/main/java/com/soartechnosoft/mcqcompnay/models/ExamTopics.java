package com.soartechnosoft.mcqcompnay.models;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "examtopics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamTopics {

     // CREATE TABLE examtopics (
     // exam VARCHAR,
     // subject VARCHAR,
     // topic VARCHAR,
     // orgname VARCHAR,
     // setupby VARCHAR,
     // numberofquestions INT,
     // details TEXT,
     // PRIMARY KEY ((exam, subject), topic, setupby)
     // );
     @PrimaryKeyClass
     @Data
     public class ExamTopicsKey implements Serializable {

          @PrimaryKeyColumn(name = "exam", type = PrimaryKeyType.PARTITIONED)
          private String exam;

          @PrimaryKeyColumn(name = "subject")
          private String subject;

          @PrimaryKeyColumn(value = "topic")
          private String topic;

          @PrimaryKeyColumn(value = "setupby")
          private String setupby;

          public ExamTopicsKey(
                    final String exam,
                    final String subject,
                    final String topic,
                    final String setupby) {
               this.exam = exam;
               this.subject = subject;
               this.topic=topic;
               this.setupby=setupby;
          }
          // getters and setters
     }

     @PrimaryKey
     private ExamTopicsKey key;    

     @Column(value = "orgname")
     private String orgname;    

     @Column(value = "numberofquestions")
     private Integer numberofquestions;

     @Column(value = "details")
     private String details;
}
