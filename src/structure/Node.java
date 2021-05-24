package structure;

import java.net.Socket;

public class Node {
	private int id;
	private String host;
	private int serverPort;
	private int clientPort;
	private static Socket server;
	
	public Node() {}
	
	public Node(int id, String host, int serverPort, int clientPort) {
		setId(id);
		setHost(host);
		setServerPort(serverPort);
		setClientPort(clientPort);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	
	public Socket getServer() {
		return server;
	}
	public void setServer(Socket server) {
		Node.server = server;
	}
	
	
	
}
