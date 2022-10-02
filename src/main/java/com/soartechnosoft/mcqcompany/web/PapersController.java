package com.soartechnosoft.mcqcompany.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.data.cassandra.CassandraInternalException;
import org.springframework.data.cassandra.CassandraQuerySyntaxException;
import org.springframework.data.cassandra.CassandraReadTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soartechnosoft.mcqcompany.repository.PapersRepository;
import com.soartechnosoft.mcqcompany.util.PapersUtilityService;
import com.soartechnosoft.mcqcompnay.models.Papers;
import com.soartechnosoft.mcqcompnay.models.maps.PaperEntity;
import com.soartechnosoft.mcqcompnay.models.maps.UpcomingPapers;

import io.micrometer.core.annotation.Timed;

@Controller
@RequestMapping("paper")
public class PapersController {
    private static Logger logger = LoggerFactory.getLogger(PapersController.class);
   
   
    @Autowired
    PapersRepository papersRepository;

    @Autowired
    PapersUtilityService papersUtilityService;

    @Value("${defaultOrgId}")
    private String defaultOrgId="3fa85f64-5717-4562-b3fc-2c963f66afa6";

    @PostMapping(path = "{org}/{exam}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<HttpStatus> postPaper(@RequestBody PaperEntity pEntity,
            @PathVariable String org, @PathVariable String exam) {

        if (logger.isDebugEnabled()) {
            logger.info("received request to create org {}", pEntity);
        }
        try {

            // creating 1st object
            Papers papers = Papers.builder()
                    .description(pEntity.getDescription())
                    .durationOfExam(pEntity.getDurationOfExam())
                    .isInBlukSubmit(pEntity.getIsInBlukSubmit())
                    .isMinusMarking(pEntity.getIsMinusMarking())
                    .isOneByOne(pEntity.getIsOneByOne())
                    .marksForEachQuestion(pEntity.getMarksForEachQuestion())
                    .minusMarkingBy(pEntity.getMinusMarkingBy())
                    .numberOfQuestions(pEntity.getNumberOfQuestions())
                    .startTime(pEntity.getStartTime())
                    .orgName(org)
                    .examName(exam)
                    .build();
            Papers.PapersKey key = papers.new PapersKey(pEntity.getKey().getOrg(), pEntity.getKey().getExam(),
                    pEntity.getKey().getStartdate(), UUID.randomUUID());
            papers.setKey(key);

            Papers papersInserted = papersRepository.insert(papers);
            if (logger.isDebugEnabled()) {
                logger.info("Paper successfully added {}", papersInserted);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "upcomingpapers/{orgid}/{examid}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<Map<String, List<UpcomingPapers>>> getUpcommingPapers(@PathVariable UUID orgid,
            @PathVariable UUID examid,
            @RequestParam UUID paperid, @RequestParam String studentemailid) {
        logger.info("calling to get all the exam and topics");
        try {
            Map<String, List<UpcomingPapers>> map = new HashMap<String, List<UpcomingPapers>>();
            List<Papers> lsPapers = papersRepository.findByOrgByExam(orgid, examid);
            if (lsPapers.isEmpty()) {
                return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.NOT_FOUND);
            }
            List<UpcomingPapers> lsUpcomingPapers = papersUtilityService.buildResultPapers(lsPapers, paperid, studentemailid);
            map.put("upcomingexams", lsUpcomingPapers);
            ResponseEntity<Map<String, List<UpcomingPapers>>> responseEntity = new ResponseEntity<Map<String, List<UpcomingPapers>>>(
                    map,
                    HttpStatus.OK);
            return responseEntity;
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/{orgid}/{examid}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Papers>> getPapersByOrgAndExam(@PathVariable UUID orgid,
            @PathVariable UUID examid) {
        logger.info("calling to get all the exam and topics");
        try {
            List<Papers> lsPapers = papersRepository.findByOrgByExam(orgid, examid);
            if (lsPapers.isEmpty()) {
                return new ResponseEntity<List<Papers>>(HttpStatus.NOT_FOUND);
            }

            ResponseEntity<List<Papers>> responseEntity = new ResponseEntity<List<Papers>>(lsPapers,
                    HttpStatus.OK);
            return responseEntity;
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Papers>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Papers>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Papers>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Papers>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Papers>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "upcoming/{examid}/default", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<Map<String, List<UpcomingPapers>>> getUpcommingPapersOpenToAll(
            @PathVariable UUID examid,
            @RequestParam UUID paperid, @RequestParam String studentemailid) {
        logger.info("calling to get all the exam and topics");
        try {
            Map<String, List<UpcomingPapers>> map = new HashMap<String, List<UpcomingPapers>>();
            UUID defUuid = UUID.fromString(defaultOrgId);
            List<Papers> lsPapers = papersRepository.findByOrgByExam(defUuid, examid);
            if (lsPapers.isEmpty()) {
                return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.NOT_FOUND);
            }
            List<UpcomingPapers> lsUpcomingPapers = papersUtilityService.buildResultPapers(lsPapers, paperid, studentemailid);
            map.put("upcomingexamsPromoted", lsUpcomingPapers);
            ResponseEntity<Map<String, List<UpcomingPapers>>> responseEntity = new ResponseEntity<Map<String, List<UpcomingPapers>>>(
                    map,
                    HttpStatus.OK);
            return responseEntity;
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<Map<String, List<UpcomingPapers>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
