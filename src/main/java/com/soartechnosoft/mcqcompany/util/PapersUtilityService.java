package com.soartechnosoft.mcqcompany.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soartechnosoft.mcqcompany.repository.EnrollmentsRepository;
import com.soartechnosoft.mcqcompany.repository.EnrollmentsRepository.EnrollmentStatus;
import com.soartechnosoft.mcqcompnay.models.Papers;
import com.soartechnosoft.mcqcompnay.models.maps.UpcomingPapers;

@Service
public class PapersUtilityService {

    
    @Autowired
    EnrollmentsRepository enrollmentsRepository;
    
  

    public List<UpcomingPapers> buildResultPapers(List<Papers> lsPapers,UUID paperid,String studentmailid) {
        List<UpcomingPapers> lsUpcomingPapers = new ArrayList<UpcomingPapers>();
        for (Papers papers : lsPapers) {

            List<EnrollmentStatus> lEnrollmentsStatus= enrollmentsRepository.findStatusByOrgByExamByPaperidByStudentemailid(papers.getKey().getOrg(), papers.getKey().getExam(), paperid, studentmailid);
            
            Boolean isEnrolled=false;
            String enrollmentStatus="Default";
            if(lEnrollmentsStatus.isEmpty()){
                isEnrolled=false;
            }
            else{
                isEnrolled=true;
                enrollmentStatus=lEnrollmentsStatus.get(0).getStatus();
            }
            UpcomingPapers updaPapers = UpcomingPapers.builder()
                    .durationOfExam(papers.getDurationOfExam())
                    .examName(papers.getExamName())
                    .isEnrolled(isEnrolled)
                    .marksForEachQuestion(papers.getMarksForEachQuestion())
                    .numberOfQuestions(papers.getNumberOfQuestions())
                    .orgName(papers.getOrgName())
                    .startTime(papers.getStartTime())
                    .startdate(papers.getKey().getStartdate())
                    .EnrolledStatus(enrollmentStatus)
                    .build();
            lsUpcomingPapers.add(updaPapers);

        }
        return lsUpcomingPapers;
    }
}
