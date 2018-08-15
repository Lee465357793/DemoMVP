package com.weiyi.mvpdemo.utils;

/**
 * ProjectName：DemoMVP
 * DESC: (类描述)
 * Created by 李岩 on 2018/5/22 0022
 */
public class MathUtils {
    public static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

}
