package com.atguigu.violencerecursiontodynamic.fromlefttoright;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * 桥监控
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子: str= "babac", arr = {"ba" ,"c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class StickerSelect {


    public static int sticker1(String[] sticker, String target) {
        if (target == null || target == "" || sticker == null || sticker.length == 0) {
            return 0;
        }
        int N = sticker.length;
//      map[0][1~26] 存放的是 sticker[0] 第一个贴纸里面的所有字符
        int[][] map = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] stickerChar = sticker[i].toCharArray();
            for (int j = 0; j < sticker[i].length(); j++) {
                map[i][stickerChar[j] - 'a']++;
            }
        }
        //key 为target 当前字符 value最少要多少张贴纸
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(map, target, dp);
    }

    public static int process(int[][] sticker, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int res = Integer.MAX_VALUE;
        char[] targetArr = target.toCharArray();
        int[] tMap = new int[26];
        for (int i = 0; i < targetArr.length; i++) {
            tMap[targetArr[i] - 'a']++;
        }

        for (int i = 0; i < sticker.length; i++) {
            if (sticker[i][targetArr[0] - 'a'] == 0) {
                continue;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < 26; j++) {
                if (tMap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tMap[j] - sticker[i][j]); k++) {
                        stringBuffer.append((char) (j + 'a'));
                    }
                }
            }

            String s = stringBuffer.toString();
            int temp = process(sticker, s, dp);
            if (temp != -1) {
                res = Math.min(res, 1 + temp);
            }

        }
        dp.put(target, res == Integer.MAX_VALUE ? -1 : res);

        return dp.get(target);
    }
}
