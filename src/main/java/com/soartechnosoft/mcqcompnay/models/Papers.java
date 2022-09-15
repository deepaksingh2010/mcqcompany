package com.soartechnosoft.mcqcompnay.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "paper")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Papers {

//     CREATE TABLE quizapp.paper (
//     org uuid,
//     exam uuid,
//     startdate text,
//     paperid uuid,
//     desclaimerforexam list<frozen<disclaimers>>,
//     description text,
//     durationofexam int,
//     isinbluksubmit boolean,
//     isminusmarking boolean,
//     isonebyone boolean,
//     ispublic boolean,
//     marksforeachquestion int,
//     minusmarkingby decimal,
//     numberofquestions int,
//     questions list<frozen<questions>>,
//     starttime text,
//     subjects list<frozen<subjects>>,
//     PRIMARY KEY ((org, exam),startdate, paperid)
// )
    @PrimaryKeyClass
    @Data
    public class PapersKey implements Serializable {
        @PrimaryKeyColumn(name = "org", type = PrimaryKeyType.PARTITIONED)
        private UUID org;

        @PrimaryKeyColumn(name = "exam", type = PrimaryKeyType.PARTITIONED)
        private UUID exam;

        @PrimaryKeyColumn(name = "startdate")
        private String startdate;

        @PrimaryKeyColumn(name = "paperid")
        private UUID paperid;
        public PapersKey(UUID org,UUID exam,String startdate,UUID paperid){
            this.org=org;
            this.exam=exam;
            this.paperid=paperid;
            this.startdate=startdate;
        }
    }

    @PrimaryKey
    private PapersKey key;    

    @Column(value = "starttime")
    private String startTime;    

    @Column(value = "orgname")
    private String orgName; 

    @Column(value = "examname")
    private String examName;  

    @Column(value = "durationofexam")
    private Integer durationOfExam;

    @Column(value = "marksforeachquestion")
    private Integer marksForEachQuestion;

    @Column(value = "minusmarkingby")
    private BigDecimal minusMarkingBy;


    @Column(value = "numberofquestions")
    private Integer numberOfQuestions;


    @Column(value = "description")
    private String description;


    @Column(value = "isminusmarking")
    private Boolean isMinusMarking;

    @Column(value = "isonebyone")
    private Boolean isOneByOne;

    @Column(value = "isminusmarking")
    private Boolean isInBlukSubmit;
}
