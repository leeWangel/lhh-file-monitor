package com.lhh.file.utils;

import java.util.UUID;

public class UtilsString {
	/**
	 * 产生UUID唯一标识
	 * @return
	 */
	public static String toStringUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(toStringUUID());
	}
}
