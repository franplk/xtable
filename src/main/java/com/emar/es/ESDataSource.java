package com.emar.es;

import java.net.InetAddress;
import java.util.Map;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author franplk
 * @Date 2016/3/17
 */
public class ESDataSource implements InitializingBean {

	private int port;
	private String[] nodes;
	private Map<String, String> configMap;
	
	private TransportClient client;

	public ESDataSource() {}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			Settings settings = Settings.settingsBuilder().put(configMap).build();
			client = TransportClient.builder().settings(settings).build();
			for (String node : nodes) {
				InetAddress name = InetAddress.getByName(node);
				client.addTransportAddress(new InetSocketTransportAddress(name, 9300));
			}
		} catch (Exception e) {
			throw new Exception("Build TransportClient ERROR");
		}
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String[] getNodes() {
		return nodes;
	}

	public void setNodes(String[] nodes) {
		this.nodes = nodes;
	}

	public Map<String, String> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, String> configMap) {
		this.configMap = configMap;
	}

	public TransportClient getClient() {
		return client;
	}
	
	public void close() {
		if (this.client != null) {
			this.client.close();
		}
	}
}
