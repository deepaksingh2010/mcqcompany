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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soartechnosoft.mcqcompany.repository.EnrollmentsRepository;
import com.soartechnosoft.mcqcompnay.models.Enrollments;
import com.soartechnosoft.mcqcompnay.models.maps.EnrollmentsEntity;

import io.micrometer.core.annotation.Timed;

@Controller
@RequestMapping("enrolls")
public class EnrollmentsController {

    private static Logger logger = LoggerFactory.getLogger(EnrollmentsController.class);

    @Autowired
    EnrollmentsRepository enrollmentsRepository;

    @PostMapping(path = "/{paperid}/", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<HttpStatus> postEnrollment(
            @RequestBody EnrollmentsEntity enrollmentsEntity,
            @PathVariable UUID paperid) {

        try {

            Enrollments enrollments = Enrollments.builder()
                    .phonenumber(enrollmentsEntity.getPhonenumber())
                    .status("Enrolled")
                    .build();
            Enrollments.EnrollmentsKey key = enrollments.new EnrollmentsKey(enrollmentsEntity.getKey().getOrg(),
            enrollmentsEntity.getKey().getExam(), enrollmentsEntity.getKey().getPaperid(),
            enrollmentsEntity.getKey().getStudentemailid());
            enrollments.setKey(key);
            Enrollments enrollmentInserted = enrollmentsRepository.insert(enrollments);
            if (logger.isDebugEnabled()) {
                logger.info("Exam Attempt Details successfully added {}", enrollmentInserted);
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

    @GetMapping(path = "/{org}/{exam}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Enrollments>> getEnrollments(@PathVariable UUID org,
            @PathVariable UUID exam) {
        logger.info("calling to get all the exam and topics");
        try {
            List<Enrollments> lEnrollments = enrollmentsRepository.findAllByOrgByExam(org, exam);
            if (lEnrollments.isEmpty()) {
                return new ResponseEntity<List<Enrollments>>(HttpStatus.NOT_FOUND);
            }

            ResponseEntity<List<Enrollments>> responseEntity = new ResponseEntity<List<Enrollments>>(
                    lEnrollments,
                    HttpStatus.OK);
            return responseEntity;
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/{org}/{exam}/{paperid}", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Enrollments>> getEnrollmentsByStudentAndPaper(@PathVariable UUID org,
            @PathVariable UUID exam,
            @PathVariable UUID paperid, @RequestParam String studentemailid) {
        logger.info("calling to get all the exam and topics");
        try {
            List<Enrollments> lEnrollments = enrollmentsRepository.findAllByOrgByExamByPaperidByStudentemailid(org,
                    exam, paperid, studentemailid);
            if (lEnrollments.isEmpty()) {
                return new ResponseEntity<List<Enrollments>>(HttpStatus.NOT_FOUND);
            }

            ResponseEntity<List<Enrollments>> responseEntity = new ResponseEntity<List<Enrollments>>(
                    lEnrollments,
                    HttpStatus.OK);
            return responseEntity;
        } catch (CassandraConnectionFailureException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraReadTimeoutException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraQuerySyntaxException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CassandraInternalException ex) {
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Enrollments>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

