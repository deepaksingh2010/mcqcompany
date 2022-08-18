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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soartechnosoft.mcqcompany.repository.ExamsRepository;
import com.soartechnosoft.mcqcompnay.models.Exams;

import io.micrometer.core.annotation.Timed;


@Controller
@RequestMapping("exam")
public class ExamsController {
    private static Logger logger = LoggerFactory.getLogger(ExamsController.class);
    
    @Autowired
    ExamsRepository examsRepository;
    
    @GetMapping(path = "/all/", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Exams>> getExams() {
        logger.info("calling to get all the exam and topics");
        try{
            List<Exams> lExams = examsRepository.findAll();
            if (lExams.isEmpty()) {
                return new ResponseEntity<List<Exams>>(HttpStatus.NOT_FOUND);
            }
    
            ResponseEntity<List<Exams>> responseEntity = new ResponseEntity<List<Exams>>(lExams,
                    HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Exams>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Exams>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Exams>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Exams>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Exams>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
