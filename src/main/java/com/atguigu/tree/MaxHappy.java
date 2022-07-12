package com.atguigu.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则:
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class MaxHappy {
    public static class Employee{
        int happy ;
        List<Employee> next ;

        public Employee(int happy) {
            this.happy = happy;
            next = new ArrayList<>() ;
        }
    }

    public static class Info{
        int yes ; //直接员工来的时候的最大快乐值
        int no ; //直接员工不来的最大快乐值

        public Info(int no, int yes) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info maxHappy(Employee X){
        if (X == null ){
            return new Info( 0 , 0 ) ;
        }
        int yes = X.happy;  //如果来，那最少都有他自己  ----> 初始值就是X.happy
        int no = 0 ;
        for (Employee employee : X.next) {
            Info nextInfo = maxHappy(employee);
            yes += nextInfo.no ;
            no += Math.max(nextInfo.yes , nextInfo.no);
        }
        return new Info(no ,yes) ;
    }


    public static int maxHappy2(Employee head) {
        Info allInfo = process(head);
        return Math.max(allInfo.no, allInfo.yes);
    }

    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int no = 0;
        int yes = x.happy;
        for (Employee next : x.next) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;

        }
        return new Info(no, yes);
    }


    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.next.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 1000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if ((maxHappy(boss)==null ? 0 : Math.max(maxHappy(boss).yes,maxHappy(boss).no)) !=  maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
