package com.kk.algorithm.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * 输入一个只包含数字的字符串，请列出所有可能恢复出来的IP地址。
 * 例如，输入字符串"10203040"，可能恢复出3个IP地址，
 * 分别为"10.20.30.40"、"102.0.30.40"和"10.203.0.40"
 * <p>
 * IP地址的特点：
 * 1、一个IP地址被3个'.'字符分隔成4段，每段是从0到255之间的一个数字
 * 2、另外，除"0"本身外，其他数字不能以'0'开头
 * 题解：逐个扫描字符串中的字符
 * 2个选择：1、将当前字符拼接到当前分段数字的末尾，拼接之后的数字应该在0到255之间。
 * 2、是当前字符作为一个新的分段数字的开始
 * <p>
 * 由于逐一处理字符串中的每个字符，因此需要n步，并且每一步都面临两个可能的选项
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class IpAddress {

    public static List<String> resolveIpAddress(String s) {
        List<String> list = new LinkedList<>();
        help(s, 0, 0, "", "", list);
        return list;
    }

    /**
     * @param s      要扫描的字符串
     * @param i      当前扫描的字符串位置
     * @param segI   正在处理哪个段
     * @param seg    每个ip段的值
     * @param ip     ip字符串
     * @param result 存储最终的结果
     */
    public static void help(String s, int i, int segI, String seg, String ip, List<String> result) {
        //遍历完字符串，并且总共为3段，最后一段seg是合法的，则加入结果集合
        if (i == s.length() && segI == 3 && isValidSeg(seg)) {
            result.add(ip + seg);
        } else if (i < s.length() && segI <= 3) {
            //获取当前字符
            char c = s.charAt(i);
            //判断是否为合法的ip段，当前字符拼接在末尾
            if (isValidSeg(seg + c)) {
                help(s, i + 1, segI, seg + c, ip, result);
            }
            //当前字符作为一个新的分段数字的开始
            if (seg.length() > 0 && segI < 3) {
                help(s, i + 1, segI + 1, "" + c, ip + seg + ".", result);
            }
        }
    }

    /**
     * 判断是否为合法的ip段（<=255 或者 0 或者 不以0开头）
     */
    private static boolean isValidSeg(String seg) {
        return Integer.valueOf(seg) <= 255 && ("0".equals(seg) || seg.charAt(0) != '0');
    }

    public static void main(String[] args) {
        System.out.println(resolveIpAddress("110203040"));
    }

}
