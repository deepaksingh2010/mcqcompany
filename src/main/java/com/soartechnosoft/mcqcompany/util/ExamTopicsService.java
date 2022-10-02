package com.soartechnosoft.mcqcompany.util;

import com.soartechnosoft.mcqcompnay.models.ExamTopics;
import com.soartechnosoft.mcqcompnay.models.maps.ExamtopicsMap;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExamTopicsService {    
    
    public List<ExamtopicsMap> getTopicsForUI(Iterable<ExamTopics> lstTopics) {
        List<ExamtopicsMap> lstExamTopicsMap = new ArrayList<ExamtopicsMap>();
        for (ExamTopics examTopics : lstTopics) {
            lstExamTopicsMap.add(
                    ExamtopicsMap.builder()
                            .numberofquestions(examTopics.getNumberofquestions())
                            .orgname(examTopics.getOrgname())
                            .setupby(examTopics.getKey().getSetupby())
                            .subject(examTopics.getKey().getSubject())
                            .topic(examTopics.getKey().getTopic())
                            .details(examTopics.getDetails())
                            .exam(examTopics.getKey().getExam())
                            .build());
        }
        return lstExamTopicsMap;
    }  

}