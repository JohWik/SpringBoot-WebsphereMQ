package com.johwik.echomqservice.gateway;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.johwik.echomqservice.MQProperties;

@Named
@Transactional
public class MQGateway {
	private JmsTemplate jmsTemplate;

	private MQProperties properties;

	@Inject
	public MQGateway(JmsTemplate jmsTemplate, MQProperties properties) {
		this.jmsTemplate = jmsTemplate;
		this.properties = properties;
	}

	@JmsListener(destination = "${wmq.incomingQueue}", containerFactory = "defaultJmsListenerContainerFactory")
	public void onMessage(TextMessage message) throws JMSException {
		System.out.println("onMessage");
		System.out.println("onMessage - Message: " + message);

		String answer = "";
		try {
			String messageTxt = message.getText();
			answer = "Echo echo echo => " + messageTxt;

		} catch (JMSException e) {
			System.out.println("Cannot consume message: " + message + "\n" + e.toString());
			e.printStackTrace();
		}
		send(answer);

	}

	public void send(String message) {
		System.out.println("send");
		System.out.println("send - Message: " + message);

		jmsTemplate.convertAndSend(properties.getOutgoingQueue(), message);
	}
}