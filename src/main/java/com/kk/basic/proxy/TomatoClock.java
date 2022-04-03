package com.kk.basic.proxy;

/**
 * @author kpq
 * @since 1.0.0
 */
public class TomatoClock {

    /**
     * 工作时长
     */
    private static final int WORK_TIME = 25;

    /**
     * 休息时长
     */
    private static final int BREAK_TIME = 5;

    private static final String TOMATO = "🍅";

    private static final String SEPARATOR = "--";

    /**
     * @param minutes 工作时间
     * @throws InterruptedException InterruptedException
     */
    public static void tomato(int minutes) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        while (true) {
            //已经过去了几秒
            long passedSeconds = (System.currentTimeMillis() - startTime) / 1000;
            //还剩下几秒
            long leftSeconds = minutes * 60 - passedSeconds;
            if (leftSeconds < 0) {
                System.out.print(String.format("take a %s minutes to have a break", BREAK_TIME));
                break;
            }
            //已过时间占用总时间的百分比
            double fraction = (double) passedSeconds / (minutes * 60.0) * 100;
            //倒计时信息
            String countDown = String.format("[%.2f%%] %s:%s ⏰%s", fraction, leftSeconds / 60, appendZero(leftSeconds % 60), "\r");
            StringBuilder tomatoInfo = new StringBuilder();
            //每 60s 产生一个番茄
            int passedTomato = (int) (passedSeconds / 60);
            for (int i = 0; i < minutes; i++) {
                tomatoInfo.append(i < passedTomato ? TOMATO : SEPARATOR);
            }
            System.out.print(tomatoInfo.toString() + countDown);
            //每隔 1s 计算一次
            Thread.sleep(1000);
        }
    }

    private static String appendZero(long seconds) {
        return seconds > 9L ? String.valueOf(seconds) : "0" + seconds;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("%s tomato %s minutes", TOMATO, WORK_TIME));
        tomato(WORK_TIME);
    }

}
