package com.soartechnosoft.mcqcompany.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionFactoryFactoryBean;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlSession;
@PropertySource(value="application-${spring.profiles.active}.properties")
@EnableCassandraRepositories(basePackages = {"com.soartechnosoft.mcqcompany.repository.ExamTopicsRepository"})
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;
    
    @Value("${cassandra.username}")
    private String userName;

    @Value("${cassandra.password}")
    private String password;

    /*
     * Provide a keyspace name to the configuration.
     */
    @Override
    public String getKeyspaceName() {
        return keySpace;
    }

    @Override
    public String getContactPoints() {
        return contactPoints;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Bean
  @Primary
  public CqlSessionFactoryBean session() {

    CqlSessionFactoryBean session = new CqlSessionFactoryBean();
    session.setContactPoints(getContactPoints());
    session.setKeyspaceName(getKeyspaceName());
    session.setLocalDatacenter(getLocalDataCenter());
    return session;
  }

  @Bean
  @Primary
  public SessionFactoryFactoryBean sessionFactory(CqlSession session, CassandraConverter converter) {
    SessionFactoryFactoryBean sessionFactory = new SessionFactoryFactoryBean();
    sessionFactory.setSession(session);
    sessionFactory.setConverter(converter);
    sessionFactory.setSchemaAction(SchemaAction.NONE);
    return sessionFactory;
  }
}
