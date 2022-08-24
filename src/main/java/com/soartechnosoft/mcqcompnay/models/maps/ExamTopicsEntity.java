package com.soartechnosoft.mcqcompnay.models.maps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExamTopicsEntity {

    @Data
    public class ExamTopicsKey {

        private String exam;
        private String subject;
        private String topic;
        private String setupby;
    }
    private ExamTopicsKey key;
    private String orgname;
    private Integer numberofquestions;
    private String details;
}
