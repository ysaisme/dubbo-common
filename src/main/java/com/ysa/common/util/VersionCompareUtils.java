package com.ysa.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * 版本比较器<br>
 * 比较两个版本的大小<br>
 *
 * @author Looly
 * @since 4.0.2
 */
public class VersionCompareUtils {

    /**
     * 比较两个版本<br>
     * version1 < version2  -1
     * version1 = version2   0
     * version1 > version2   1
     * <pre>
     * compareVersion("2.9.9", "2.9.9") = 0
     * compareVersion("2.9.8", "2.9.9") = -1
     * compareVersion(null, "1.12.1") = 0
     * compareVersion("2.9.9", "3.0.1") = -1
     * compareVersion("2.9.9", "2.9.9a") = -1
     * compareVersion("2.8.9", "2.7.8") = 1
     * </pre>
     *
     * @param version1 版本1
     * @param version2 版本2
     */
    public static int compareVersion(String version1, String version2) {
        if (null == version1 || null == version2) {
            return 0;
        }
        if (version1.equals(version2)) {
            return 0;
        }
        final List<String> v1s = Arrays.asList(version1.split("\\."));
        final List<String> v2s = Arrays.asList(version2.split("\\."));

        int diff = 0;
        // 取最小长度值
        int minLength = Math.min(v1s.size(), v2s.size());
        String v1;
        String v2;
        for (int i = 0; i < minLength; i++) {
            v1 = v1s.get(i);
            v2 = v2s.get(i);
            // 先比较长度
            diff = v1.length() - v2.length();
            if (0 == diff) {
                diff = v1.compareTo(v2);
            }
            if (diff != 0) {
                //已有结果，结束
                break;
            }
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        return (diff != 0) ? diff : v1s.size() - v2s.size();
    }

}

