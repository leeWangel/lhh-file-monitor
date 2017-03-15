package com.lhh.file.job.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 开关枚举文件
 * @createDate 2017年3月13日
 */
public enum FileOnOffEnum {

	ON,OFF;
	
	/**
	 * 获取开关标识
	 * @param on
	 * @return
	 */
	public static FileOnOffEnum parser(String on){
		FileOnOffEnum[] v = FileOnOffEnum.values();
		if( StringUtils.isBlank(on)) return null;
		for (FileOnOffEnum f : v) {
			if( on.equalsIgnoreCase(f.name())){
				return f;
			}
		}
		return null;
	}
}
