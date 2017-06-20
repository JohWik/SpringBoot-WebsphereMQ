package com.johwik.echomqservice;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wmq")
public class MQProperties {
    String queueManager;
    String host;
    int port;
    String channel;
    String incomingQueue;
    String outgoingQueue;
    String user;
    String password;
	
    public String getQueueManager() {
		return queueManager;
	}
	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIncomingQueue() {
		return incomingQueue;
	}
	public void setIncomingQueue(String incomingQueue) {
		this.incomingQueue = incomingQueue;
	}
	public String getOutgoingQueue() {
		return outgoingQueue;
	}
	public void setOutgoingQueue(String outgoingQueue) {
		this.outgoingQueue = outgoingQueue;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "MQProperties [queueManager=" + queueManager + ", host=" + host + ", port=" + port + ", channel="
				+ channel + ", incomingQueue=" + incomingQueue + ", outgoingQueue=" + outgoingQueue + ", user="
				+ user + ", password=" + password + "]";
	}
    
    
}