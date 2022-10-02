package com.soartechnosoft.mcqcompnay.models.maps;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
   private String EnrolledStatus;
}
