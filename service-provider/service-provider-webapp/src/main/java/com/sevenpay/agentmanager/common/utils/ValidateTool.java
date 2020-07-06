package com.sevenpay.agentmanager.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateTool {

	public static String replaceBlankStr(String sourceStr) {
		String returnStr = null;
		String regEx = "[' ']+"; // 一个或多个空格
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(sourceStr);
		returnStr = m.replaceAll(" ");
		return returnStr;
	}

	public static int transStrToInt(String str) {
		int result = 1;
		if (str.equalsIgnoreCase("true")) {
			result = 1;
		} else if (str.equalsIgnoreCase("false")) {
			result = 0;
		}
		return result;
	}

	// 检查身份证是否正确的函数
	public static boolean isValidCerCardNo(String cerCardNo) {
		boolean result = isIdentityId(cerCardNo);
		return result;
	}

	// 检查所有字符均为有效的数字
	public static boolean isNumber(String number) {
		boolean result = false;
		String regex = "[0-9]*";
		result = runRegex(number, regex);
		return result;
	}

	// 检查字符是否为有效的日期格式
	public static boolean isValidDate(String date) {
		boolean result = false;
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		result = runRegex(date, regex);
		return result;
	}

	// 是否为正确的email地址
	public static boolean isValidEmail(String email) {
		boolean result = false;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		result = runRegex(email, regex);
		return result;
	}

	// 是否为正确的电话号码
	public static boolean isPhoneNo(String phoneNo) {
		boolean result = false;
		String regex = "\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)";
		result = runRegex(phoneNo, regex);
		return result;
	}

	// 是否为正确的手机号码(目前只支持1-3、5、8开头的手机号码)
	public static boolean isMobilePhoneNo(String mobilePhoneNo) {
		boolean result = false;
		String regex = "^[1][358][0-9]{9}$";
		result = runRegex(mobilePhoneNo, regex);
		return result;
	}

	public static boolean runRegex(String str, String regex) {
		if (str == null) {
			return false;
		}
		boolean result = false;
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			result = true;
		}
		return result;
	}

	/**
	 * 修补15位居民身份证号码为18位
	 * 
	 * @param personIDCode
	 * @return
	 */
	public static boolean checkPersonIDCode_18(String personIDCode) {
		boolean flag = false;
		if (personIDCode == null || personIDCode.trim().length() != 18) {
			return false;
		}

		char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 11个
		int[] factor = { 0, 2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7 }; // 18个;
		int[] idcd = new int[18];
		int i;
		int j;
		int sum;
		int remainder;

		for (i = 1; i < 18; i++) {
			j = 17 - i;
			idcd[i] = Integer.parseInt(personIDCode.substring(j, j + 1));
		}

		sum = 0;
		for (i = 1; i < 18; i++) {
			sum = sum + idcd[i] * factor[i];
		}
		remainder = sum % 11;
		String lastCheckBit = String.valueOf(code[remainder]);
		if (personIDCode.substring(17, 18).equals(lastCheckBit)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断是否是有效的18位居民身份证号码
	 * 
	 * @param identityId ：18位居民身份证号码
	 * @return：true： 有效的18位居民身份证号码
	 */
	public static boolean isIdentityId(String identityId) {
		String identityId18 = "";
		boolean flag = false;
		if (isEmpty(identityId))
			return false;
		try {
			if (identityId.length() == 15) {
				identityId18 = get18IDCard(identityId);
			} else {
				identityId18 = identityId;
			}
			;
			flag = checkPersonIDCode_18(identityId18);
			return flag;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * 将15位的身份证转成18位身份证
	 * 
	 * @param idcard
	 * @return
	 */
	public static String get18IDCard(String fifteenIDCard) throws Exception {
		if (fifteenIDCard != null && fifteenIDCard.length() == 15) {
			StringBuilder sb = new StringBuilder();
			sb.append(fifteenIDCard.substring(0, 6)).append("19").append(fifteenIDCard.substring(6));
			sb.append(getVerifyCode(sb.toString()));
			return sb.toString();
		} else {
			throw new Exception("不是15位的身份证");
		}
	}

	/**
	 * 获取校验码
	 * 
	 * @param idCardNumber 不带校验位的身份证号码（17位）
	 * @return 校验码
	 * @throws Exception 如果身份证没有加上19，则抛出异常
	 */
	public static char getVerifyCode(String idCardNumber) throws Exception {
		if (idCardNumber == null || idCardNumber.length() < 17) {
			throw new Exception("不合法的身份证号码");
		}
		char[] Ai = idCardNumber.toCharArray();
		int[] Wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char[] verifyCode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		int S = 0;
		int Y;
		for (int i = 0; i < Wi.length; i++) {
			S += (Ai[i] - '0') * Wi[i];
		}
		Y = S % 11;
		return verifyCode[Y];
	}

	/**
	 * 判断是否为空串""
	 */
	public static boolean isEmpty(String sValue) {
		if (sValue == null)
			return true;
		return sValue.trim().equals("") ? true : false;
	}

	/**
	 * 从身份证获得年龄
	 * 
	 * @param paraList
	 * @return String
	 * @throws Exception
	 */
	public static String getSexFromCertNo(String identityId) throws Exception {
		// 15位的看最后一个数单数的为男，双数的为女
		// 18位的看倒数第二位单数的为男，双数的为女
		int IDcardLen = identityId.length();
		String xingbie = "";
		String sex = "9";// 未说明
		if (IDcardLen == 15) {
			xingbie = identityId.substring(14, 15);
		} else if (IDcardLen == 18) {
			xingbie = identityId.substring(16, 17);
		}
		if ("13579".indexOf(xingbie) != -1)
			sex = "1";// 男
		else
			sex = "2";// 女
		return sex;

	}

	/**
	 * 从身份证获得生日日期yyyy-mm-dd
	 * 
	 * @param identityId
	 * @return String
	 * @throws Exception
	 */
	public static String getBirthdayFromCertNo(String identityId) throws Exception {
		// 15位身份证号从第7位到第12位是出生年月日，年份用的是2位数
		// 18位身份证号从第7位到第14位是出生的年月日，年份用的是4位数
		if (identityId != null && !"".equals(identityId)) {
			String ID_card = identityId;
			int IDcardLen = ID_card.length();
			String birthday = "";
			if (IDcardLen == 15) {
				birthday = "19" + ID_card.substring(6, 8) + "-" + ID_card.substring(8, 10) + "-"
						+ ID_card.substring(10, 12);
			} else if (IDcardLen == 18) {
				birthday = ID_card.substring(6, 10) + "-" + ID_card.substring(10, 12) + "-" + ID_card.substring(12, 14);
			}
			return birthday;
		} else {
			throw new Exception("身份证号为空");
		}
	}

	/**
	 * 根据生日与对比日期来计算年龄
	 * 
	 * @param paraList
	 * @return String
	 * @throws Exception
	 */
	public static String getAgeFromBirthday(String birthday, String compareDate) throws Exception {
		if (birthday == null || "".equals(birthday) || compareDate == null || "".equals(compareDate)) {
			return "-1";
		}
		if (birthday.length() < 10 || compareDate.length() < 10) {
			throw new Exception("getAgeFromBirthday参数中日期格式有问题");
		}
		// 日期格式必须是yyyy-mm-dd
		String age = "";
		int bYear = Integer.parseInt(birthday.substring(0, 4));
		int bMonth = Integer.parseInt(birthday.substring(5, 7));
		int bDay = Integer.parseInt(birthday.substring(8, 10));
		int cYear = Integer.parseInt(compareDate.substring(0, 4));
		int cMonth = Integer.parseInt(compareDate.substring(5, 7));
		int cDay = Integer.parseInt(compareDate.substring(8, 10));
		if (cMonth > bMonth || (cMonth == bMonth && cDay >= bDay)) {
			age = cYear - bYear + "";
		} else {
			age = cYear - bYear - 1 + "";
		}
		return age;
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param paraList
	 * @return
	 * @throws Exception
	 */
	public static boolean isLeapYear(String date) throws Exception {
		// 日期格式必须是yyyy-mm-dd
		int year = Integer.parseInt(date.split("-")[0]);
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否为金额格式
	 * 
	 * @param money
	 * @return
	 */
	public static boolean isMoney(String money) {
		boolean result = false;
		String regex = "^(([1-9]\\d*)|\\d)(\\.\\d{1,2})?$";
		result = runRegex(money, regex);
		return result;
	}

	/**
	 * 判断正否是正确的银行卡号. 目前只能判断是否是纯数字
	 * 
	 * @param payeeBankCard
	 * @return
	 */
	public static boolean isBankCard(String bankCard) {
		if (bankCard == null)
			return false;
		return isNumber(bankCard);
	}

	public static String sbuStr(String str) {
		String strsub = null;
		if (str.length() >= 4) {// 判断是否长度大于等于4
			strsub = str.substring(str.length() - 4);
		}
		return strsub;
	}

	public static void main(String args[]) {
		String id = "440981840313021";
		String flag = sbuStr(id);
		System.out.println(flag);

	}

}