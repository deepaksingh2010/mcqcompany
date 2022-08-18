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

@Table(value = "orgnizationorcoaching")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orgnizationorcoaching {

    // orgnizationorcoaching (
    //     name text,
    //     city text,
    //     address text,
    //     detaileddescription text,
    //     id uuid,
    //     isactive boolean,
    //     PRIMARY KEY (name, city)
    // ) 
     @PrimaryKeyClass
     @Data
     public class EOrgnizationorcoachingKey implements Serializable {

          @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.PARTITIONED)
          private String name;

          @PrimaryKeyColumn(name = "city")
          private String city;

          public EOrgnizationorcoachingKey(
                    final String name,
                    final String city) {
               this.name = name;
               this.city = city;
          }
          // getters and setters
     }

     @PrimaryKey
     private EOrgnizationorcoachingKey key;

     @Column(value = "address")
     private String address;

     @Column(value = "detaileddescription")
     private String detaileddescription;

     @Column(value = "id")
     private UUID id;

     @Column(value = "isactive")
     private Boolean isactive;
}
