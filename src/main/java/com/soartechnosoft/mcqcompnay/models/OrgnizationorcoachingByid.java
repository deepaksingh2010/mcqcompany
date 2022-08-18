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

@Table(value = "orgnizationorcoaching_byuuid")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrgnizationorcoachingByid {

     // orgnizationorcoaching_byuuid (
     // id uuid,
     // name text,
     // city text,
     // address text,
     // detaileddescription text,
     // isactive boolean,
     // PRIMARY KEY ((id, name), city)
     // )
     @PrimaryKeyClass
     @Data
     public class OrgnizationorcoachingByIdKey implements Serializable {

          @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
          private UUID id;

          @PrimaryKeyColumn(name = "name", type = PrimaryKeyType.PARTITIONED)
          private String name;

          @PrimaryKeyColumn(name = "city")
          private String city;

          public OrgnizationorcoachingByIdKey(
                    final UUID id,
                    final String name,
                    final String city) {
               this.id = id;
               this.name = name;
               this.city = city;
          }
          // getters and setters
     }

     @PrimaryKey
     private OrgnizationorcoachingByIdKey key;

     @Column(value = "address")
     private String address;

     @Column(value = "detaileddescription")
     private String detaileddescription;

     @Column(value = "isactive")
     private Boolean isactive;
}
