package com.kk.algorithm.backtracking;

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

    public List<String> resolveIpAddress(String s) {
        return null;
    }

    public void help(String s, int i, int segI, String seg, String ip, List<String> result) {
        if (i == s.length() && segI == 3) {

        }
    }

}
