package com.example.demo2.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class TestRegex {
    public static boolean isChineseName(String realname) {
        return Pattern.matches("[\u4e00-\u9fa5|·]{2,15}", realname);
    }

    public static boolean isCardId(String cardid) {
        return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$", cardid);
    }

    public static String[] randomArray(int length) {
        int index = 0;
        String temp = ",";
        for (int ii = 0; ii < 1000; ii++) {
            for (int i = 0; i < length; i++) {
                index = (int) (Math.random() * 10);
                temp += (index + "");
            }
            temp += ",";
        }
        return temp.split(",");
    }

    //身份证前1位每位加权因子
    private static int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    //身份证第18位校检码
    private static String[] refNumber = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3"};
    //省(直辖市)代码表
    private String provinceCode[] = {"11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91"};

    /**
     * 检查身份证的省份信息是否正确（使用与18/15位身份证）
     *
     * @param provinceid
     * @return
     */
    public boolean checkProvinceId(String provinceid) {
        for (String id : provinceCode) {
            if (id.equals(provinceid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验身份证第18位是否正确(只适合18位身份证)
     *
     * @param cardId
     * @return
     */
    public static boolean checkCardIdLastNum(String cardId) {
        if (cardId.length() != 18) {
            return false;
        }
        char[] tmp = cardId.toCharArray();
        int[] cardidArray = new int[tmp.length - 1];
        int i = 0;
        for (i = 0; i < tmp.length - 1; i++) {
            cardidArray[i] = Integer.parseInt(tmp[i] + "");
        }
        String checkCode = sumPower(cardidArray);
        String lastNum = tmp[tmp.length - 1] + "";
        if (lastNum.equals("x")) {
            lastNum = lastNum.toUpperCase();
        }
        if (!checkCode.equals(lastNum)) {
            return false;
        }
        return true;
    }

    /**
     * 计算身份证的第十八位校验码
     *
     * @param cardidArray
     * @return
     */
    public static String sumPower(int[] cardidArray) {
        int result = 0;
        for (int i = 0; i < power.length; i++) {
            result += power[i] * cardidArray[i];
        }
        return refNumber[(result % 11)];
    }

    /**
     * 判断日期是否有效
     *
     * @param inDate
     * @return
     */
    public boolean isValidDate(String inDate) {
        if (inDate == null) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        dateFormat.setLenient(false);//严格的日期匹配
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //@Test
    public void testName() {
        String[] array = {"钟·", "在哪"};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + ":" + isChineseName(array[i]));
        }
    }

    //
    //@Test
    public void testCardId() {
        String[] array = randomArray(18);
        int index = 0, sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += 1;
            if (isCardId(array[i])) {
                index += 1;
            }
            System.out.println(array[i] + ":" + isCardId(array[i]));
        }
        double ret = index / sum;
        System.out.println(index + "," + sum + "," + ret);
    }

    //@Test
    public void testArray() {
        String[] array = randomArray(15);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //@Test
    public void testLastNum() {
        String[] cardId = {"440982199563272359", "43010219890414298x"};
        for (int i = 0; i < cardId.length; i++) {
            System.out.println(cardId[i] + ":" + checkCardIdLastNum(cardId[i]));
        }
    }

    //@Test
    public void testProcince() {
        String[] cardId = {"440982199563272359", "43010219890414298x"};
        for (int i = 0; i < cardId.length; i++) {
            System.out.println(cardId[i].substring(0, 2) + ":" + checkProvinceId(cardId[i].substring(0, 2)));
        }

    }
}
