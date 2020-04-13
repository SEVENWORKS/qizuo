/*
 * Copyright (c) 2020.
 * author：qizuo
 */

package com.qizuo.util.safe;

import com.qizuo.base.exception.QizuoException;
import com.qizuo.config.properties.baseProperties.GlobalConstant;
import com.qizuo.util.common.ObjectIsEmptyUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 加密工具.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AesUtil {

	/**
	 * 加密
	 *
	 * @param contentParam 需要加密的内容
	 * @param keyParam     加密密码
	 * @param md5Key       是否对key进行md5加密
	 * @param ivParam      加密向量
	 *
	 * @return 加密后的字节数据 string
	 */
	public static String encrypt(String contentParam, String keyParam, boolean md5Key, String ivParam) {
		try {
			byte[] content = contentParam.getBytes(GlobalConstant.Encode.CHAR_SET);
			byte[] key = keyParam.getBytes(GlobalConstant.Encode.CHAR_SET);
			byte[] iv = ivParam.getBytes(GlobalConstant.Encode.CHAR_SET);

			if (md5Key) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				key = md.digest(key);
			}
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			//"算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
			//使用CBC模式, 需要一个向量iv, 可增加加密算法的强度
			IvParameterSpec ivps = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivps);
			byte[] bytes = cipher.doFinal(content);
			return new BASE64Encoder().encode(bytes);
		} catch (Exception ex) {
			log.error("加密密码失败", ex);
			throw new QizuoException("加密失败");
		}
	}

	/**
	 * 解密
	 *
	 * @param contentParam 需要加密的内容
	 * @param keyParam     加密密码
	 * @param md5Key       是否对key进行md5加密
	 * @param ivParam      加密向量
	 *
	 * @return string
	 */
	public static String decrypt(String contentParam, String keyParam, boolean md5Key, String ivParam) {
		try {
			if (ObjectIsEmptyUtils.isNull(contentParam, keyParam, md5Key, ivParam)) {
				return "";
			}
			byte[] content = new BASE64Decoder().decodeBuffer(contentParam);
			byte[] key = keyParam.getBytes(GlobalConstant.Encode.CHAR_SET);
			byte[] iv = ivParam.getBytes(GlobalConstant.Encode.CHAR_SET);

			if (md5Key) {
				MessageDigest md = MessageDigest.getInstance("MD5");
				key = md.digest(key);
			}
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			//"算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
			//使用CBC模式, 需要一个向量iv, 可增加加密算法的强度
			IvParameterSpec ivps = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivps);
			byte[] bytes = cipher.doFinal(content);
			return new String(bytes, GlobalConstant.Encode.CHAR_SET);
		} catch (Exception ex) {
			log.error("解密密码失败", ex);
			throw new QizuoException("解密失败");
		}
	}

	/**
	 * Encrypt string.
	 *
	 * @param password 密码
	 *
	 * @return the string
	 */
	public static String encrypt(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	/**
	 * 密码是否匹配.
	 *
	 * @param rawPassword     明文
	 * @param encodedPassword 密文
	 *
	 * @return the boolean
	 */
	public static boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}
}