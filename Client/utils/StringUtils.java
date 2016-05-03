package utils;

public class StringUtils {
	public static String join(Object[] arr) {
		
		String data = "";
		
		for(int i = 0; i < arr.length; i++) {
			data += arr.toString();
			
			if(i < arr.length)
				data += "/";
		}
		System.out.println(data);
		return data;
	}
	
	public static String formatToPath(String str) {
		String data = str.replace("[", "");
		data = data.replace("]", "");
		data = data.replace(", ", "/");
		
		return data;
	}
}
