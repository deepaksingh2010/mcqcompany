// package com.soartechnosoft.mcqcompany.config;


// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
// import com.datastax.astra.boot.autoconfigure.AstraClientProperties;

// @Configuration
// @PropertySource(value="application-${spring.profiles.active}.yaml")
// @EnableCassandraRepositories(basePackages = {"com.soartechnosoft.mcqcompany.repository.ExamTopicsRepository"})
// public class CassandraConfiguration extends AstraClientProperties {

//     @Value("${astra.client-id}")
//     private String clientId;
    
//     public String getClientId() {
//         return this.clientId;
//     }
//     @Value("${astra.application-token}")
//     private String applicationToken;
    
//     public String getApplicationToken() {
//         return applicationToken;
//     }
//     @Value("${astra.client-secret}")
//     private String clientSecret;

//     public String getClientSecret() {
//         return clientSecret;
//     }
//     @Value("${astra.cloud-region}")
//     private String cloudRegion;

//     public String getCloudRegion() {
//         return cloudRegion;
//     }
//     @Value("${astra.database-id}")
//     private String databaseId;
    
//     public String getDatabaseId() {
//         return databaseId;
//     }
    
//     @Value("${astra.keyspace}")
//     private String keyspace;
    
//     public String getKeyspace() {
//         return this.keyspace;
//     }
//     // @Override
//     // public String getContactPoints() {
//     //     return contactPoints;
//     // }

//     // @Override
//     // public int getPort() {
//     //     return port;
//     // }
//     // @Bean 
//     // @Override public CassandraClusterFactoryBean cluster() {
//     //     CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean(); 
//     //     PlainTextAuthProvider sap = new PlainTextAuthProvider(env.getProperty("cassandra.username"), env.getProperty("cassandra.password"));
//     //     cluster.setContactPoints(env.getProperty("cassandra.contactpoints"));
//     //     cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));
//     //     cluster.setAuthProvider(sap);
//     //     cluster.setSslEnabled(true);
//     //     return cluster; 
//     //   }
//   //   @Bean
//   // @Primary
//   // public CqlSessionFactoryBean session() {

//   //   CqlSessionFactoryBean session = new CqlSessionFactoryBean();
//   //   session.setContactPoints(getContactPoints());
//   //   session.setKeyspaceName(getKeyspaceName());
//   //   session.setLocalDatacenter(getLocalDataCenter());
//   //   return session;
//   // }

//   // @Bean
//   // @Primary
//   // public SessionFactoryFactoryBean sessionFactory(CqlSession session, CassandraConverter converter) {
//   //   SessionFactoryFactoryBean sessionFactory = new SessionFactoryFactoryBean();
//   //   sessionFactory.setSession(session);
//   //   sessionFactory.setConverter(converter);
//   //   sessionFactory.setSchemaAction(SchemaAction.NONE);
//   //   return sessionFactory;
//   // }

  
// }
