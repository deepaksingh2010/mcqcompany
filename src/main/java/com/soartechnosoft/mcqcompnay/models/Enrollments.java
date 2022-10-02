package com.soartechnosoft.mcqcompnay.models;


import java.io.Serializable;
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

@Table(value = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollments {
    // CREATE TABLE quizapp.enrollments(
    // org uuid,
    // exam uuid,
    // paperid uuid,
    // studentemailid text,
    // phonenumber text,
    // status text,
    // PRIMARY KEY((org,exam),paperid,studentemailid)
    // );
    @PrimaryKeyClass
    @Data
    public class EnrollmentsKey implements Serializable {

        @PrimaryKeyColumn(name = "org", type = PrimaryKeyType.PARTITIONED)
        private UUID org;

        @PrimaryKeyColumn(name = "exam")
        private UUID exam;

        @PrimaryKeyColumn(name = "paperid")
        private UUID paperid;

        @PrimaryKeyColumn(name = "studentemailid")
        private String studentemailid;

        public EnrollmentsKey(
                final UUID org,
                final UUID exam,
                UUID paperid,
                String studentemailid) {
            this.org = org;
            this.exam = exam;
            this.paperid = paperid;
            this.studentemailid = studentemailid;
        }
        // getters and setters
    }

    @PrimaryKey
    private EnrollmentsKey key;

    @Column(value = "phonenumber")
    private String phonenumber;

    @Column(value = "status")
    private String status;
}