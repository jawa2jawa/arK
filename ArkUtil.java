package requesthandler;

import java.util.HashMap;

public class ArkUtil {
	static HashMap<String, String> paramMap = new HashMap<String, String>();

	public static void populateMap(String request) {
		int firstIndex=request.indexOf("?");
		int lastIndex=request.indexOf("HTTP");
		request=request.substring(firstIndex, lastIndex);
		String tokes[]=request.split("&");
		int len=tokes.length;
		String data[]=new String[2];
		for(int i=0;i<len;i++) {
			data=tokes[i].split("=");
			paramMap.put(data[0], data[1]);
		}
	}
}
