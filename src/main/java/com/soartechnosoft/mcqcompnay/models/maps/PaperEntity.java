package com.soartechnosoft.mcqcompnay.models.maps;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaperEntity {
     @Data
     public class PaperKey {

        private UUID org;
        private UUID exam;
        private String startdate;
        private UUID paperid;
     }

     private PaperKey key;
    private String startTime;    
    private Integer durationOfExam;
    private Integer marksForEachQuestion;
    private BigDecimal minusMarkingBy;
    private Integer numberOfQuestions;
    private String description;
    private Boolean isMinusMarking;
    private Boolean isOneByOne;
    private Boolean isInBlukSubmit;
   //  private String examName;  
   //  private String orgName; 
}
