package cn.onetarget.webviewtest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public  class Utils {
	private Utils(){
	}

	/**
	 * @param is
	 * @return 字符串 注意判断为空的情况
	 */
	public static String getString(InputStream is){
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		String str;
		try {
			StringBuilder stringSb=new StringBuilder();
			while ((str=br.readLine())!=null){
				stringSb.append(str);
			}
			return stringSb.toString();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				br.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
