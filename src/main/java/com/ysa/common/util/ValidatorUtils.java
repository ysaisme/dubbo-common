package com.ysa.common.util;

import com.ysa.common.enums.PatternEnum;
import com.ysa.common.error.YsaParamException;

import java.util.regex.Pattern;

/**
 * 正则验证字段工具类
 * 支持手机号、身份证号、邮箱、emoji表情、
 *
 * @author ysa
 * @date 2020/11/13 2:35 PM
 */
public class ValidatorUtils {

    /**
     * 通过正则表达式验证
     *
     * @param pattern 正则模式
     * @param value   值
     * @return 是否匹配正则
     */
    public static boolean isMatchRegex(Pattern pattern, String value) {
        return RegexUtils.isMatch(pattern, value);
    }


    /**
     * 验证手机号
     *
     * @param mobileNumber 手机号码
     * @return 是否是手机号格式
     */
    public static Boolean isMobile(String mobileNumber) {
        return isMatchRegex(PatternEnum.MOBILE.getPattern(), mobileNumber);
    }

    /**
     * 验证手机号
     *
     * @param mobileNumber 手机号码
     * @param desc         如果不是手机号码格式返回的错误信息
     */
    public static void validateMobile(String mobileNumber, String desc) {
        if (!isMobile(mobileNumber)) {
            throw new YsaParamException(desc);
        }
    }

    /**
     * 验证身份证号码
     *
     * @param citizenId 身份证号码
     * @return 是否是身份证号码格式
     */
    public static Boolean isCitizenId(String citizenId) {
        return isMatchRegex(PatternEnum.CITIZEN_ID.getPattern(), citizenId);
    }

    /**
     * 验证身份证号码
     *
     * @param citizenId 身份证号码
     * @param desc      如果不是身份证号码格式返回的错误信息
     */
    public static void vaildateCitizenId(String citizenId, String desc) {
        if (!isCitizenId(citizenId)) {
            throw new YsaParamException(desc);
        }
    }

    /**
     * 是否是汉字
     *
     * @param chinese 汉字
     * @return 是否是汉字
     */
    public static boolean isChinese(String chinese) {
        return isMatchRegex(PatternEnum.CHINESE_WORD.getPattern(), chinese);
    }

    /**
     * 是否是汉字
     *
     * @param chinese 汉字
     * @param desc    如果不是汉字返回的错误信息
     */
    public static void valdateChinese(String chinese, String desc) {
        if (!isChinese(chinese)) {
            throw new YsaParamException(desc);
        }
    }

    /**
     * 验证Emoji表情
     *
     * @param emoji emoji表情
     * @return 是否是emoji表情
     */
    public static boolean isEmoji(String emoji) {
        return isMatchRegex(PatternEnum.EMOJI.getPattern(), emoji);
    }

    /**
     * 验证Emoji表情
     *
     * @param emoji emoji表情
     * @param desc  如果不是emoji表情返回的错误信息
     */
    public static void valdateEmoji(String emoji, String desc) {
        if (!isEmoji(emoji)) {
            throw new YsaParamException(desc);
        }
    }

}
