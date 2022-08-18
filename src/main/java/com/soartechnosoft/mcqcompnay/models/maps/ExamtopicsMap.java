package com.soartechnosoft.mcqcompnay.models.maps;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExamtopicsMap {

    @Value(value = "orgname")
    private String orgname;

    @Value(value = "numberofquestions")
    private Integer numberofquestions;

    @Value(value = "details")
    private String details;

    @Value(value = "id")
    private String exam;

    @Value(value = "subject")
    private String subject;

    @Value(value = "topic")
    private String topic;

    @Value(value = "setupby")
    private String setupby;
}
