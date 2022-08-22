package com.soartechnosoft.mcqcompnay.models.maps;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExamEntity {
     @Data
     public class ExamsKey {

          private String examName;
          private UUID setupBy;
          private String organizingDepartment;
          // getters and setters
     }

     private ExamsKey key;
     private Integer durationOfExam;
     private Integer numberOfQuestions;
     private String examdescription;
}
