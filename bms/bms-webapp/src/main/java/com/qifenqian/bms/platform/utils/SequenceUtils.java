package com.qifenqian.bms.platform.utils;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.common.util.RedisUtil;
import com.qifenqian.bms.platform.type.SequenceType;

import redis.clients.jedis.Jedis;

/**
 * 序号获取器
 * 
 * @project sevenpay-bms-web
 * @fileName SequenceUtils.java
 * @author huiquan.ma
 * @date 2015年5月7日
 * @memo
 */
public class SequenceUtils {

	private static Logger logger = LoggerFactory.getLogger(SequenceUtils.class);

	/** 循环值 */
	private static long CYC_NUM_4 = 1;

	private static long CYC_NUM_8 = 1;

	/**
	 * 获取序号
	 * 
	 * @return type + yyyyMMddHHmmssSSS(17) + SEQ(4)
	 */
	public static String getSequence(SequenceType type) {

		return (null == type ? "" : type.name()) + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ fillLeftWith0(String.valueOf(getCycNum4()), 4);
	}

	/**
	 * 获取序号
	 * 
	 * @return type + yyyyMMddHHmmssSSS(17) + SEQ(8)
	 */
	public static String getSequence_8(String prefix) {

		return prefix + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ fillLeftWith0(String.valueOf(getCycNum8()), 8);
	}

	/**
	 * 获取序号
	 * 
	 * @return prefix + yyyyMMddHHmmssSSS(17) + SEQ(4)
	 */
	public static String getSequence(String prefix) {

		return prefix + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS")
				+ fillLeftWith0(String.valueOf(getCycNum4()), 4);
	}

	public static String getAgenSequence(String prefix) {
		return prefix + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")
				+ fillLeftWith0(String.valueOf(getCycNum4()), 4);
	}

	/**
	 * 使用“0”左填充至指定长度
	 * 
	 * @param currSeq
	 * @param length
	 * @return
	 */
	private static String fillLeftWith0(String suffix, int length) {
		suffix = "00000000000000000000" + suffix;

		// 长度超长则截取
		if (suffix.length() > length) {
			suffix = suffix.substring(suffix.length() - length);
		}

		return suffix;
	}

	/**
	 * 获取自增长为 1，循环周期为 10000 的数值
	 * 
	 * @return
	 */
	private synchronized static long getCycNum4() {
		CYC_NUM_4 %= 10000;
		return CYC_NUM_4++;
	}

	/**
	 * 获取自增长为 1，循环周期为 100000000 的数值
	 * 
	 * @return
	 */
	private synchronized static long getCycNum8() {
		CYC_NUM_8 %= 100000000;
		return CYC_NUM_8++;
	}

	public static String getMerchantSeqNo(String seqType) {
		Jedis jedis = RedisUtil.getJedis();
		String result = null;
		
		try {
			Long seqNo = jedis.incr(seqType);
			String dateString = DatetimeUtils.dateSecond();
			String resultNo = fillLeftWith0(String.valueOf(seqNo), 5);
			result = seqType + dateString + resultNo;

		} catch (Exception e) {
			logger.error("获取序列错误" + e);
		} finally {
			RedisUtil.returnResource(jedis);
		}

		return result;
	}
}
