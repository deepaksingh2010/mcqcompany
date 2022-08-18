package com.soartechnosoft.mcqcompany.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.data.cassandra.CassandraInternalException;
import org.springframework.data.cassandra.CassandraQuerySyntaxException;
import org.springframework.data.cassandra.CassandraReadTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soartechnosoft.mcqcompany.repository.ExamTopicsRepository;
import com.soartechnosoft.mcqcompany.util.ExamTopicsService;
import com.soartechnosoft.mcqcompnay.models.ExamTopics;
import com.soartechnosoft.mcqcompnay.models.maps.ExamtopicsMap;

import io.micrometer.core.annotation.Timed;

@Controller
@RequestMapping("examtopics")
public class ExamTopicsController {
    private static Logger logger = LoggerFactory.getLogger(ExamTopicsController.class);
    @Autowired
    ExamTopicsRepository examTopicsRepository;

    @Autowired
    ExamTopicsService examTopicsService;


    @GetMapping(path = "/hello", produces = "application/json")
    public @ResponseBody String getBook() {
        String uts = "1";
        String utsii = "1";
        if (uts == utsii) {
            return "equels";
        } else {
            return "! equels";
        }

    }

    @GetMapping(path = "/topics/", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<ExamtopicsMap>> getTopics() {
        logger.info("calling to get all the exam and topics");
        try{
            List<ExamTopics> lsttopics = examTopicsRepository.findAll();
            if (lsttopics.size()==0) {
                return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.NOT_FOUND);
            }
            List<ExamtopicsMap> lstExamTopicsMap = examTopicsService.getTopicsForUI(lsttopics);
    
            ResponseEntity<List<ExamtopicsMap>> responseEntity = new ResponseEntity<List<ExamtopicsMap>>(lstExamTopicsMap,
                    HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    @GetMapping(path = "/topics/{exam}/{subject}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<ExamtopicsMap>> getTopics(@PathVariable String exam,
            @PathVariable String subject) {
        logger.info("exam {} subject {}", exam, subject);
        try{
            List<ExamTopics> lsttopics = examTopicsRepository.findByExamBySubject(exam, subject);
            if (lsttopics.size()==0) {
                return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.NOT_FOUND);
            }
            List<ExamtopicsMap> lstExamTopicsMap = examTopicsService.getTopicsForUI(lsttopics);
    
            ResponseEntity<List<ExamtopicsMap>> responseEntity = new ResponseEntity<List<ExamtopicsMap>>(lstExamTopicsMap,
                    HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<ExamtopicsMap>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

}
