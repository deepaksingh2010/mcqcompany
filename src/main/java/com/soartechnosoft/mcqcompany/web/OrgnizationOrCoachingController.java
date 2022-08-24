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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soartechnosoft.mcqcompany.repository.OrgnizationOrCoachingRepository;
import com.soartechnosoft.mcqcompany.repository.OrgnizationorcoachingByidRepository;
import com.soartechnosoft.mcqcompnay.models.Orgnizationorcoaching;
import com.soartechnosoft.mcqcompnay.models.OrgnizationorcoachingByid;
import com.soartechnosoft.mcqcompnay.models.maps.OrgnizationorCoachingEntity;

import io.micrometer.core.annotation.Timed;

@Controller
@RequestMapping("OrgnizationOrCoaching")
public class OrgnizationOrCoachingController {
    private static Logger logger = LoggerFactory.getLogger(OrgnizationOrCoachingController.class);

    @Autowired
    OrgnizationOrCoachingRepository orCoachingRepository;

    @Autowired
    OrgnizationorcoachingByidRepository orgnizationorcoachingByidRepository;

    @PostMapping(path="orgnization",produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<HttpStatus> postOrgnizationorcoaching(@RequestBody OrgnizationorCoachingEntity org){
        if(logger.isDebugEnabled()){
            logger.info("received request to create org {}", org);
        }
        try{

            //creating 1st object
            Orgnizationorcoaching orgnizationorcoachingEntity=Orgnizationorcoaching.builder()
                                                                                    .address(org.getAddress())
                                                                                    .detaileddescription(org.getDetaileddescription())
                                                                                    .id(UUID.randomUUID())
                                                                                    .isactive(org.getIsactive()) 
                                                                                    .build(); 
            orgnizationorcoachingEntity.setKey(orgnizationorcoachingEntity.new EOrgnizationorcoachingKey(org.getKey().getName(), org.getKey().getCity())); 

            Orgnizationorcoaching orgnizationorcoaching= orCoachingRepository.insert(orgnizationorcoachingEntity);
            if(logger.isDebugEnabled()){
                logger.info("org created successfully {}", orgnizationorcoaching);
            }

            //creating 2nd by id object
            OrgnizationorcoachingByid orgnizationorcoachingByid=OrgnizationorcoachingByid.builder()
                                                                    .detaileddescription(orgnizationorcoaching.getDetaileddescription())
                                                                    .isactive(orgnizationorcoaching.getIsactive())
                                                                    .address(orgnizationorcoaching.getAddress()).build();
            OrgnizationorcoachingByid.OrgnizationorcoachingByIdKey orgnizationorcoachingByIdKey= orgnizationorcoachingByid.new OrgnizationorcoachingByIdKey();
            orgnizationorcoachingByIdKey.setCity(orgnizationorcoaching.getKey().getCity());    
            orgnizationorcoachingByIdKey.setName(orgnizationorcoaching.getKey().getName());; 
            orgnizationorcoachingByIdKey.setId(orgnizationorcoaching.getId());  
            orgnizationorcoachingByid.setKey(orgnizationorcoachingByIdKey);    
            
            OrgnizationorcoachingByid orgnizationorcoachingByidSaved=orgnizationorcoachingByidRepository.insert(orgnizationorcoachingByid);
            if(logger.isDebugEnabled()){
                logger.info("org by id is created successfully {}", orgnizationorcoachingByidSaved);
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/all/", produces = "application/json")
    @Timed
    public @ResponseBody ResponseEntity<List<Orgnizationorcoaching>> getOrgnizationorcoaching() {
        logger.info("calling to get all coaching centers registered");
        try{
            List<Orgnizationorcoaching> lOrgnizationorcoachings = orCoachingRepository.findAll();
            if (lOrgnizationorcoachings.isEmpty()) {
                return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.NOT_FOUND);
            }
          
            ResponseEntity<List<Orgnizationorcoaching>> responseEntity = new ResponseEntity<List<Orgnizationorcoaching>>(lOrgnizationorcoachings,
                    HttpStatus.OK);
            return responseEntity;
        }
        catch(CassandraConnectionFailureException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraReadTimeoutException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraQuerySyntaxException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(CassandraInternalException ex){
            logger.error("exception occored msg {}", ex.getMessage());
            return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            logger.error("exception occored msg {}", e.getMessage());
            return new ResponseEntity<List<Orgnizationorcoaching>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
