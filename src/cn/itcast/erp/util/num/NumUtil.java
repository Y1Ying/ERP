package cn.itcast.erp.util.num;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumUtil {
	public static int serNum = 1;
	public static int len = 7;
	private static final byte[] zero = {48, 48, 48, 48, 48, 48};

	public static final String generatorOrderNum() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("yyMMdd");
		String fir = df.format(d);
		int num = serNum++;
		int len2 = (num + "").length();

		String sec = new String(zero, 0, len - len2);
		return Long.toHexString(new Long(fir + sec + num)).toUpperCase();
	}
	public static void main(String[] args) {
		System.out.println(generatorOrderNum());
	}

}
