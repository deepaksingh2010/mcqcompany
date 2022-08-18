// package com.soartechnosoft.mcqcompnay.models;

// import java.io.Serializable;

// import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
// import org.springframework.data.cassandra.core.mapping.Column;
// import org.springframework.data.cassandra.core.mapping.PrimaryKey;
// import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
// import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
// import org.springframework.data.cassandra.core.mapping.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Table(value = "question")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Question {
//     @PrimaryKeyClass
//     public class QuestionKey implements Serializable {
//         @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
//         private String exam;
//     }
// }
