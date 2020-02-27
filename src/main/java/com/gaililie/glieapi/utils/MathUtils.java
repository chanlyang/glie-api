package com.gaililie.glieapi.utils;

import java.math.BigDecimal;

/**
 * @author WANGNING
 *
 */
public class MathUtils {

    /* 默认除法运算精度 */
    private static final int DEF_DIV_SCALE = 10;

    private MathUtils() {

    }

    /**
     * 
     * 提供（相对）精确的加法运算,精确到小数点后15位
     * 
     * @param v1 被加数
     * 
     * @param v2 加数
     * 
     * @return 两个参数的和
     * 
     */

    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();

    }

    /**
     * 
     * 提供（相对）精确的减法运算,精确到小数点后15位
     * 
     * @param v1 被减数
     * 
     * @param v2 减数
     * 
     * @return 两个参数的差
     * 
     */

    public static double sub(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }

    /**
     * 
     * 提供（相对）精确的乘法运算,精确到小数点后15位
     * 
     * @param v1 被乘数
     * 
     * @param v2 乘数
     * 
     * @param scale 保留位数
     * 
     * @return 两个参数的积
     * 
     */

    public static double mul(double v1, double v2, int scale) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    public static double mul(int scale, double... vs) {

        BigDecimal sum = new BigDecimal(1);

        for (double db : vs) {
            sum = sum.multiply(new BigDecimal(db));
        }
        return sum.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 
     * 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * 
     * @param v2 除数
     * 
     * @return 两个参数的商
     * 
     */

    public static double div(double v1, double v2) {

        return div(v1, v2, DEF_DIV_SCALE);

    }

    /**
     * 
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 
     * 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * 
     * @param v2 除数
     * 
     * @param scale 表示表示需要精确到小数点以后几位。
     * 
     * @return 两个参数的商
     * 
     */

    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");

        }
        // 分母不能为0
        if (v2 == 0) {
            return 0;
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    public static Integer divInt(double v1, double v2) {

        // 分母不能为0
        if (v2 == 0) {
            return 0;
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).intValue();

    }

    /**
     * 
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * 
     * @param scale 小数点后保留几位
     * 
     * @return 四舍五入后的结果
     * 
     */

    public static double round(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    public static double round(double v, int scale, int model) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, model).doubleValue();

    }

    /**
     * 
     * 提供（相对）精确的取最大值运算,精确到小数点后15位。
     * 
     * @param v1 比较值
     * 
     * @param v2 比较值
     * 
     * @return 两个参数中的最大值
     * 
     */

    public static double max(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        // b1.setScale(19);
        // b2.setScale(19);
        return b1.max(b2).doubleValue();
    }

    /**
     * 
     * 提供（相对）精确的取最小值运算,精确到小数点后15位。
     * 
     * @param v1 比较值
     * 
     * @param v2 比较值
     * 
     * @return 两个参数中的最小值
     * 
     */
    public static double min(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.min(b2).doubleValue();
    }

    public static double pow(double v1, double v2) {

        return Math.pow(v1, v2);
    }

    public static Double mul(Double adviceRate, Double coreSystemBasePremium) {

        BigDecimal b1 = new BigDecimal(Double.toString(adviceRate));

        BigDecimal b2 = new BigDecimal(Double.toString(coreSystemBasePremium));

        return b1.multiply(b2).setScale(BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
