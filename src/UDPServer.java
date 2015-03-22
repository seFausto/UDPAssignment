import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
	
	private final int _incomingPort = 5432;
	private final int _outgoingPort = 5433;
	private DatagramSocket outgoingServerSocket;
	private DatagramSocket incomingServerSocket;
	
	public UDPServer() throws Exception
	{
		incomingServerSocket = new DatagramSocket(_incomingPort);
		outgoingServerSocket = new DatagramSocket(_outgoingPort);
		
		byte[] receivedData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		while (true)
		{
			DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
			
			incomingServerSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			
			System.out.println("Received: " + sentence);
			
			InetAddress IPAddress = receivePacket.getAddress();
			
			int port = receivePacket.getPort();
			String capitalizedSentece = sentence.toUpperCase();
			
			sendData = capitalizedSentece.getBytes();
			DatagramPacket sendPacket =
					new DatagramPacket(sendData, sendData.length, IPAddress, port);
			
			outgoingServerSocket.send(sendPacket);
			
		}
		
	}

}
