package ETS.common.util;

import java.util.Calendar;
import java.util.TimeZone;

public class DateConvertUtil {
	/**
	 *    Date the information contained in the Map (2000-01-01 00: 00: 00.0) to
	 *    2010-01-01 Change to
	 */
	public static String convertDate(Object date){
		if(date!=null && date.toString().length()>20){
			return date.toString().substring(0, 10);
		}else{
			return "";
		}
	}

	/**
	 *    Date the information contained in the Map (2000-01-01 00: 00: 00.0) to
	 *    2010-01-01 00:00 Change to
	 */
	public static String convertTime(Object date){
		if(date!=null && date.toString().length()>20){
			return date.toString().substring(0, 16);
		}else{
			return "";
		}
	}

	/**
	 *    Bring a date (YYYYMMDD) String format today
	 */
	public static String getStringTimeDate(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}

		result = year + month_str + day_str;

		return result;
	}

	/**
	 *    Bring a date (YYYYMMDD) String format today
	 */
	public static String getStringThisMonth(){
		String result = "";
		String month_str 			= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}

		result = year + month_str;

		return result;
	}

	/**
	 *    Bring a date (YYYY-MM-DD) String format today
	 */
	public static String getStringTimeDateDesh(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}

		result = year +"-"+ month_str +"-"+ day_str;

		return result;
	}
	/**
	 *    Tomorrow brings with date (YYYYMMDD) String format
	 */
	public static String getStringTomorrowDate(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}

		result = year + month_str + day_str;

		return result;
	}

	/**
	 *    Bring to today's date and time-resolved (YYYYMMDDHH24MI) String format
	 */

	public static String getStringTimeMinute(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		String hour_str 				= "";
		String minute_str 			= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		int hour 			= c.get(Calendar.HOUR_OF_DAY);
		int minute 		= c.get(Calendar.MINUTE);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}
		if(hour<10){		hour_str="0"+hour;				}else{	hour_str=""+hour;				}
		if(minute<10){	minute_str="0"+minute;			}else{	minute_str=""+minute;		}

		result = year + month_str + day_str + hour_str + minute_str;

		return result;
	}

	/**
	 *    Brings the current hour and minute (HH24MI) String format
	 */

	public static String getStringTimeHourMinute(){
		String result = "";
		String hour_str 				= "";
		String minute_str 			= "";
		Calendar c			= Calendar.getInstance();
		int hour 			= c.get(Calendar.HOUR_OF_DAY);
		int minute 		= c.get(Calendar.MINUTE);
		if(hour<10){		hour_str="0"+hour;				}else{	hour_str=""+hour;				}
		if(minute<10){	minute_str="0"+minute;			}else{	minute_str=""+minute;		}

		result = hour_str + ":" +minute_str;

		return result;
	}

	/**
	 *    Today's date and the hour, minute second (YYYYMMDDHH24MISS) leads to a String type
	 */

	public static String getStringTimeSecond(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		String hour_str 				= "";
		String minute_str 			= "";
		String second_str 			= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		int hour 			= c.get(Calendar.HOUR_OF_DAY);
		int minute 		= c.get(Calendar.MINUTE);
		int second 		= c.get(Calendar.SECOND);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}
		if(hour<10){		hour_str="0"+hour;				}else{	hour_str=""+hour;				}
		if(minute<10){	minute_str="0"+minute;			}else{	minute_str=""+minute;		}
		if(second<10){	second_str="0"+second;			}else{	second_str=""+second;		}

		result = year + month_str + day_str + hour_str + minute_str + second_str;

		return result;
	}
	/**
	 *    Today's date and time in milliseconds to retrieve Secondary (YYYYMMDDHH24MISS) String format
	 */
	public static String getStringTimeMilli(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		String hour_str 				= "";
		String minute_str 			= "";
		String second_str 			= "";
		String milliSecond_str 	= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		int hour 			= c.get(Calendar.HOUR_OF_DAY);
		int minute 		= c.get(Calendar.MINUTE);
		int second 		= c.get(Calendar.SECOND);
		int milliSecond 	= c.get(Calendar.MILLISECOND);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}
		if(hour<10){		hour_str="0"+hour;				}else{	hour_str=""+hour;				}
		if(minute<10){	minute_str="0"+minute;			}else{	minute_str=""+minute;		}
		if(second<10){	second_str="0"+second;			}else{	second_str=""+second;		}

		if(milliSecond<10){
			milliSecond_str="00"+milliSecond;
		}else{
			if(milliSecond<100){
				milliSecond_str="0"+milliSecond;
			}else{
				milliSecond_str=""+milliSecond;
			}
		}

		result = year + month_str + day_str + hour_str + minute_str + second_str + milliSecond_str;

		return result;
	}
	/**
	 *    Today's date and the hour, minute second (YYYY-MM-DD HH24: MI: SS) leads to a String type
	 */

	public static String getStringMailTimeSecond(){
		String result = "";
		String month_str 			= "";
		String day_str 				= "";
		String hour_str 				= "";
		String minute_str 			= "";
		String second_str 			= "";
		Calendar c			= Calendar.getInstance();
		int year 			= c.get(Calendar.YEAR);
		int month 			= (c.get(Calendar.MONTH)+1);
		int day 				= c.get(Calendar.DATE);
		int hour 			= c.get(Calendar.HOUR_OF_DAY);
		int minute 		= c.get(Calendar.MINUTE);
		int second 		= c.get(Calendar.SECOND);
		if(month<10){	month_str="0"+month;			}else{	month_str=""+month;			}
		if(day<10){		day_str="0"+day;					}else{	day_str=""+day;				}
		if(hour<10){		hour_str="0"+hour;				}else{	hour_str=""+hour;				}
		if(minute<10){	minute_str="0"+minute;			}else{	minute_str=""+minute;		}
		if(second<10){	second_str="0"+second;			}else{	second_str=""+second;		}

		result = year +"-"+ month_str +"-"+ day_str +" "+ hour_str +":"+ minute_str+":" + second_str;

		return result;
	}


	/**
	 * 8-digit date character brings up the current day of the week.
	 * @param sYmd	Date e.g)20090909
	 * @return	(Day of the week)
	 */
	public static String getDateOfWeek(String sYmd) {
		if (sYmd == null || "".equals(sYmd.trim()) || sYmd.length() != 8)
			return "";
		int iYear = Integer.parseInt((sYmd.substring(0, 4)));
		int iMonth = Integer.parseInt((sYmd.substring(4, 6))) - 1;
		int iDay = Integer.parseInt((sYmd.substring(6, 8)));

		String[] dayOfWeek = { "(일)", "(월)", "(화)", "(수)", "(목)", "(금)", "(토)" };
		Calendar c = Calendar.getInstance();

		c.set(iYear, iMonth, iDay);

		return dayOfWeek[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * Converts a string to a date format.
	 * @param src
	 * @return
	 *   - 14자리 : yyyy.mm.dd hh:mi:ss
	 *   - 12자리 : yyyy.mm.dd hh:mi
	 *   - 10자리 : yyyy.mm.dd hh
	 *   - 8자리 : yyyy.mm.dd
	 *   - 6자리 : yy.mm.dd
	 */
	public static String getDateTimeStr(String src) {
		String strSrc = src;
		if (src == null || "".equals(src.trim()))
			return "";
		else
			strSrc = strSrc.trim();
		String rtnStr = null;
		if (strSrc.length() >= 14)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + " "
					+ strSrc.substring(8, 10) + ":" + strSrc.substring(10, 12)
					+ ":" + strSrc.substring(12, strSrc.length());
		else if (strSrc.length() >= 12)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + " "
					+ strSrc.substring(8, 10) + ":"
					+ strSrc.substring(10, strSrc.length());
		else if (strSrc.length() >= 10)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + " "
					+ strSrc.substring(8, strSrc.length());
		else if (strSrc.length() >= 8)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + " "
					+ strSrc.substring(8, strSrc.length());
		else if (strSrc.length() >= 6)
			rtnStr = strSrc.substring(0, 2) + "." + strSrc.substring(2, 4)
					+ " " + strSrc.substring(4, strSrc.length());

		return rtnStr;
	}

	/**
	 * Date format and returns the day of the week + time to type conformity
	 * @param src
	 * @return
	 * - Place 14 src: yyyy.mm.dd (day) hh: mi: ss
	 * - 12 digits: yyyy.mm.dd (day) hh: mi
	 * - 10 digits : yyyy.mm.dd(day) hh:mi
	 * - 8 digits : yyyy.mm.dd(day)
	 */
	public static String getDateWeekTimeStr(String src) {
		String strSrc = src;
		if (src == null || "".equals(src.trim()))
			return "";
		else
			strSrc = strSrc.trim();

		String rtnStr = null;
		String sWeek = "";

		sWeek = getDateOfWeek(strSrc.substring(0, 8));

		if (strSrc.length() >= 14)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + sWeek + " "
					+ strSrc.substring(8, 10) + ":" + strSrc.substring(10, 12)
					+ ":" + strSrc.substring(12, strSrc.length());
		else if (strSrc.length() >= 12)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + sWeek + " "
					+ strSrc.substring(8, 10) + ":"
					+ strSrc.substring(10, strSrc.length());
		else if (strSrc.length() >= 10)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + sWeek + " "
					+ strSrc.substring(8, strSrc.length());
		else if (strSrc.length() >= 8)
			rtnStr = strSrc.substring(0, 4) + "." + strSrc.substring(4, 6)
					+ "." + strSrc.substring(6, 8) + sWeek + " "
					+ strSrc.substring(8, strSrc.length());

		return rtnStr;
	}

	/**
	 * Return to today's 8-digit character string
	 * @return	Eight characters (e.g 20091010)
	 */
	public static String getTodayAndTime() {

		StringBuffer sb = new StringBuffer();

		TimeZone tKorea = java.util.TimeZone.getTimeZone("GMT+9:00");
		Calendar c = Calendar.getInstance(tKorea);

		sb.append(c.get(Calendar.YEAR) + "");
		sb.append((c.get(Calendar.MONTH) + 1) < 10 ? "0"
				+ (c.get(Calendar.MONTH) + 1) : (c.get(Calendar.MONTH) + 1)
				+ "");
		sb.append((c.get(Calendar.DATE)) < 10 ? "0" + (c.get(Calendar.DATE))
				: (c.get(Calendar.DATE)) + "");

		sb.append((c.get(Calendar.HOUR)) < 10 ? "0" + (c.get(Calendar.HOUR))
				: (c.get(Calendar.HOUR)) + "");
		sb.append((c.get(Calendar.MINUTE)) < 10 ? "0"
				+ (c.get(Calendar.MINUTE)) : (c.get(Calendar.MINUTE)) + "");
		sb.append((c.get(Calendar.SECOND)) < 10 ? "0"
				+ (c.get(Calendar.SECOND)) : (c.get(Calendar.SECOND)) + "");

		return sb.toString();
	}


	/**
	 * Returns the date format of the String type year / month / day of split
	 * @param str	Date
	 * @param type	1 : Year, 2: Month, 3:Day
	 * @return
	 */
	public static String getCalNumberByType(String str, int type) {
		if (StringUtil.nvl(str).trim().equals("") || str.length() < 8)
			return "";

		String result = "";

		switch (type) {
		case 1:
			result = str.substring(0, 4);
			break;
		case 2:
			result = str.substring(4, 6);
			break;
		case 3:
			result = str.substring(str.length() == 8 ? 6 : 6, 8);
			break;
		default:
			result = str;
		}
		return result;
	}

	/**
	 * Date of return to the eight-digit date of input
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getCalNumberByYmd(String year, String month, String day){

		if(StringUtil.nvl(year).equals("")||StringUtil.nvl(month).equals("")||StringUtil.nvl(day).equals(""))
			return "";

		int iYear=StringUtil.nvlInt(year);
		int iMonth=StringUtil.nvlInt(month);

		if(iMonth<1&&iMonth>12){
			return "";
		}

		int iDay=StringUtil.nvlInt(day);

		int lastDay=getLastDay(iYear, iMonth);

		if(iDay<1&&iDay>lastDay){
			return "";
		}

		if(iMonth<10){
			month="0"+iMonth;
		}

		if(iDay<10){
			day="0"+iDay;
		}

		return year+month+day;
	}

	/**
	 * Calculate the last date of the day.
	 *
	 * @param sDatePtn
	 * @return
	 */
	public static int getLastDay(int year, int month) {

		int[] lastDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if ((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0)
			lastDay[1] = 29;

		return lastDay[month - 1];
	}

    /***************************************************
    * @MethodName : getGapDateString
    * @Description: Returns the date as a string spacing.
    * @param      : DateDiv, gapDate
    * @return     : String
    * @Author     : Hyun-Gu, LEE
    * @Version    : 2014. 9. 3.
    ***************************************************/
    public static String getGapDateString(String DateDiv, int gapDate){
        String result = "";
        String month_str            = "";
        Calendar c          = Calendar.getInstance();
        if("M".equals(DateDiv)){
            c.add(Calendar.MONTH, gapDate);
        }else if("D".equals(DateDiv)){
            c.add(Calendar.DATE, gapDate);
        }
        int year            = c.get(Calendar.YEAR);
        int month           = (c.get(Calendar.MONTH)+1);
        if(month<10){   month_str="0"+month;            }else{  month_str=""+month;         }

        result = year + month_str;

        return result;
    }
}
