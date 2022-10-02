package com.soartechnosoft.mcqcompnay.models.maps;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EnrollmentsEntity {
    @Data
    public class EnrollmentsKey {
        private UUID org;
        private UUID exam;
        private UUID paperid;
        private String studentemailid;
    }

    private EnrollmentsKey key;
    private String phonenumber;
}
