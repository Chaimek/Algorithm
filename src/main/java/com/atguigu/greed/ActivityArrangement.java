package com.atguigu.greed;

import java.util.Arrays;

public class ActivityArrangement {

    public static class Activity{
        int start ;
        int end ;
        public Activity(int start , int end){
            this.start = start ;
            this.end = end ;
        }
    }
    /**
     *
     * @param activities 总共有多少个活动
     * @param start 活动从什么时候开始
     * @return 返回最多能安排多少活动
     */
    public static int activityArrangement(Activity[] activities , int start){
        if (activities == null ||activities.length ==0){
            return 0 ;
        }
        int activitiesCount = 0 ; //统计有多少个活动
        int timeline = start ;//活动的结束时间 ，初始值是 start 因为最开始安排活动，相当于start之后就是能安排活动了
        Arrays.sort(activities,(o1,o2) -> o1.end- o2.end);
        for (int i = 0; i < activities.length; i++) {
            if (timeline <=  activities[i].start){
                activitiesCount++;
                timeline = activities[i].end ;
            }
        }
        return activitiesCount ;
    }
}
