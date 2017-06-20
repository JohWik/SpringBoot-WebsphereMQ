package com.johwik.echomqservice;

import javax.inject.Inject;
import javax.jms.JMSException;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import com.ibm.mq.jms.MQXAConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@Configuration
@EnableConfigurationProperties(MQProperties.class)
@EnableJms
public class ConnectionConfiguration {
    @Inject
    MQProperties properties;

    @Bean
    public UserCredentialsConnectionFactoryAdapter connectionFactory() {
    	UserCredentialsConnectionFactoryAdapter ucConnectionFactory = null;
        try {
        	System.out.println("ConnectionFactory start: " + properties.toString());

        	MQXAConnectionFactory factory = getConnectionFactory();

            ucConnectionFactory = new UserCredentialsConnectionFactoryAdapter();
            ucConnectionFactory.setTargetConnectionFactory(factory);
            ucConnectionFactory.setUsername(properties.getUser());
            ucConnectionFactory.setPassword(properties.getPassword());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    	System.out.println("ConnectionFactory end: " + properties.toString());
        return ucConnectionFactory;
    }

	private MQXAConnectionFactory getConnectionFactory() throws JMSException {
		MQXAConnectionFactory factory = new MQXAConnectionFactory();
		factory.setHostName(properties.getHost());
		factory.setPort(properties.getPort());
		factory.setQueueManager(properties.getQueueManager());
		factory.setChannel(properties.getChannel());
		factory.setTransportType(WMQConstants.WMQ_CM_BINDINGS_THEN_CLIENT);
		return factory;
	}

}