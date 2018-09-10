import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class WebServer {
	public static void main(String[] args) throws IOException {
		ServerSocket listenScket = new ServerSocket(6789);
		while (true) {
			String requestMessageLine;
			String fileName;
			Socket connectionSocket = listenScket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			requestMessageLine = inFromClient.readLine();
			StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);
			if (tokenizedLine.nextToken().equals("GET")) {
				fileName = tokenizedLine.nextToken();
				if (fileName.startsWith("/")) {
					fileName = fileName.substring(1);
				}
				fileName = "D:\\serverspace\\serverProj\\src\\FirstRequest.html";
				File file = new File(fileName);
				int numOfBytes = (int) file.length();
				FileInputStream inFile = new FileInputStream(fileName);
				byte[] fileBytes = new byte[numOfBytes];
				inFile.read(fileBytes);
				outToClient.writeBytes("HTTP/1.0 200 Document Follows\r\n");
				if (fileName.endsWith(".jpg")) {
					outToClient.writeBytes("Connect-Type:image/jpeg\r\n");
				}
				if (fileName.endsWith(".gif")) {
					outToClient.writeBytes("Connect-Type:image/gif\r=n");
				}
				outToClient.writeBytes("Connect-Length:" + numOfBytes + "\r\n");
				outToClient.writeBytes("\r\n");
				outToClient.write(fileBytes, 0, numOfBytes);
				connectionSocket.close();
			} else {
				System.out.println("Bad Request Message");
			}
		}
	}
}