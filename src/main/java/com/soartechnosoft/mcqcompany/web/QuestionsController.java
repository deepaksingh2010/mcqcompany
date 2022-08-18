package com.soartechnosoft.mcqcompany.web;

import java.util.List;
import java.util.UUID;

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

import com.soartechnosoft.mcqcompany.repository.QuestionsByIdRepository;
import com.soartechnosoft.mcqcompany.repository.QuestionsRepository;
import com.soartechnosoft.mcqcompnay.models.Questions;
import com.soartechnosoft.mcqcompnay.models.QuestionsById;

import io.micrometer.core.annotation.Timed;

@Controller
@RequestMapping("questions")
public class QuestionsController {
    
    private static Logger logger=LoggerFactory.getLogger(QuestionsController.class);

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    QuestionsByIdRepository byIdRepository;

    @GetMapping(path="/{id}",produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<QuestionsById>> getQuestionById(@PathVariable UUID id){
        logger.info("get question by question id {}",id);
        try{
            List<QuestionsById> lQuestionsByIds=byIdRepository.findById(id);
            if(lQuestionsByIds.isEmpty()){
                return new ResponseEntity<List<QuestionsById>>(HttpStatus.NOT_FOUND);
            }
            ResponseEntity<List<QuestionsById>> responseEntity=new ResponseEntity<List<QuestionsById>>(lQuestionsByIds, HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<QuestionsById>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<QuestionsById>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<QuestionsById>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<QuestionsById>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<QuestionsById>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all/", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Questions>> getQuestions(){
        logger.info("calling to get all questions");
        try{
            List<Questions> lOrgnizationorcoachings = questionsRepository.findAll();
            if (lOrgnizationorcoachings.isEmpty()) {
                return new ResponseEntity<List<Questions>>(HttpStatus.NOT_FOUND);
            }
          
            ResponseEntity<List<Questions>> responseEntity = new ResponseEntity<List<Questions>>(lOrgnizationorcoachings,
                    HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Questions>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Questions>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Questions>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Questions>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Questions>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
