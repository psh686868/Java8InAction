package lambdasinaction.chap12;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PSH
 * Create by psh
 * Date: 2018/1/22
 */
public class DateType
{
    /**
     *
     * @类名:DateSelect
     * @描述:
     * @Author：游海东
     * @date: 2014年3月8日 下午11:09:37
     */
    public static class DateSelect
    {
        public boolean isDate(String date)
        {
            /**
             * 判断日期格式和范围
             */
            String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";



            Pattern pat = Pattern.compile(rexp);

            Matcher mat = pat.matcher(date);

            boolean dateType = mat.matches();

            return dateType;
        }
    }

    /**
     * @Title : main
     * @Type : DateType
     * @date : 2014年3月8日 下午10:54:50
     * @Description :
     * @param args
     */
    public static void main(String[] args)
    {
        /**
         * 日期格式正确
         */
        String date1 = "2014-01-03";

        String date8 = "2014-01-03 23:23:02";
        /**
         * 日期范围不正确---平年二月没有29号
         */
        String date2 = "2014-02-29";
        /**
         * 日期月份范围不正确---月份没有13月
         */
        String date3 = "2014-13-03";
        /**
         * 日期范围不正确---六月没有31号
         */
        String date4 = "2014-06-31";
        /**
         * 日期范围不正确 ----1月超过31天
         */
        String date5 = "2014-01-32";
        /**
         * 这个测试年份
         */
        String date6 = "0014-01-03";

        DateSelect date = new DateSelect();

        /**
         */
        Date date9 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=formatter.format(date8);
        System.out.println(time);
  //      System.out.println(date.isDate(date8));
//        System.out.println(date.isDate(date2));
//        System.out.println(date.isDate(date3));
//        System.out.println(date.isDate(date4));
//        System.out.println(date.isDate(date5));
//        System.out.println(date.isDate(date6));
    }

}
