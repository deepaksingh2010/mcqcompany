package com.soartechnosoft.mcqcompnay.models;

import java.io.Serializable;
import java.util.UUID;

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

@Table(value = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exams {

     // exams (
     // examname text,
     // setupby uuid,
     // organizingdep text,
     // durationofExam int,
     // examdescription text,
     // id uuid,
     // numberofquestions int,
     // PRIMARY KEY ((examname, setupby), organizingdep)
     // )
     @PrimaryKeyClass
     @Data
     public class ExamsKey implements Serializable {

          @PrimaryKeyColumn(name = "examname", type = PrimaryKeyType.PARTITIONED)
          private String examName;

          @PrimaryKeyColumn(name = "setupby", type = PrimaryKeyType.PARTITIONED)
          private UUID setupBy;

          @PrimaryKeyColumn(name = "organizingdep")
          private String organizingDepartment;

          public ExamsKey(
                    final String examName,
                    final UUID setupBy,
                    final String organizingDepartment) {
               this.examName = examName;
               this.setupBy = setupBy;
               this.organizingDepartment = organizingDepartment;
          }
          // getters and setters
     }

     @PrimaryKey
     private ExamsKey key;

     @Column(value = "durationofExam")
     private Integer durationOfExam;

     @Column(value = "numberofquestions")
     private Integer numberOfQuestions;

     @Column(value = "examdescription")
     private String examdescription;

     @Column(value = "id")
     private UUID id;
}
