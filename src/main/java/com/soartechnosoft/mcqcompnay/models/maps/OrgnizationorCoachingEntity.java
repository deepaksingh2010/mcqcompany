package com.soartechnosoft.mcqcompnay.models.maps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrgnizationorCoachingEntity {
 
    @Data
     public class OrgnizationorcoachingKey {
          private String name;
          private String city;
     }

     
     private OrgnizationorcoachingKey key;
     private String address;
     private String detaileddescription;
     private Boolean isactive;
}
