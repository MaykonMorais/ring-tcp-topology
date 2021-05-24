package ring;

import java.util.Scanner;

import structure.Process;

public class RunProcess {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Id Process: ");
		int id = input.nextInt();
		
		System.out.print("Server Port: ");
		int serverPort = input.nextInt();
		
		System.out.print("Client Port: ");
		int clientPortToConnect = input.nextInt();
		
		new Process("localhost", id, serverPort, clientPortToConnect).execute();;
		
		input.close();	
	}
}
