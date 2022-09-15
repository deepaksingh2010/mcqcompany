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
public class UpcomingPapers {
   
   private String startdate;
   private String startTime;    
   private Integer durationOfExam;
   private Integer marksForEachQuestion;
   private Integer numberOfQuestions;
   private String examName;  
   private String orgName; 
   private boolean isEnrolled;
}
