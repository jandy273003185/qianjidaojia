package meeting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// String[] days = printWeekdays();
		// System.out.println(days[0]);
		// System.out.println(days[days.length-1]);
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(new Date());
		// int num = calendar.get(Calendar.DAY_OF_MONTH);
		// System.out.println(num);
		String[] days = getDaysByYearMonth(2016, 2);
		for (String day : days) {
			System.out.println(day);
		}
	}

	private static final int FIRST_DAY = Calendar.MONDAY;

	public static String[] printWeekdays() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int num = calendar.get(Calendar.DAY_OF_WEEK);
		String[] weekdays = new String[num - 1];
		// calendar.set(Calendar.DAY_OF_MONTH, 1);
		setToFirstDay(calendar);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < num - 1; i++) {
			weekdays[i] = dateFormat.format(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
		}
		return weekdays;
	}

	private static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	@SuppressWarnings("unused")
	private static void printDay(Calendar calendar) {
		System.out.println();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd EE");
		System.out.println(dateFormat.format(calendar.getTime()));
	}

	public static int getCurrentMonthDay() {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static String[] getDaysByYearMonth(int year, int month) {

		String year1 = "201706".substring(0, 4);
		String month1 = "201706".substring(4, 6);
		int s = Integer.valueOf(month1);
		System.out.println(year1);
		System.out.println(s);
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int num = a.get(Calendar.DAY_OF_MONTH);
		String[] monthdays = new String[num];
		a.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < num; i++) {
			monthdays[i] = dateFormat.format(a.getTime());
			a.add(Calendar.DATE, 1);
		}
		return monthdays;
	}
}
