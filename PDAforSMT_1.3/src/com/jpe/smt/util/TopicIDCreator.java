package com.jpe.smt.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TopicIDCreator {
	public static String createNewTopicID() {
		String str1 = "" + "A";
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyyMMddhhmmss");
		String str2 = str1 + localSimpleDateFormat.format(localDate);
		Random localRandom = new Random();
		return new StringBuilder(String.valueOf(str2)).append(
				String.valueOf(localRandom.nextInt(10))).toString()
				+ String.valueOf(localRandom.nextInt(10));
	}
}