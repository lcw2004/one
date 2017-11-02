/**
 * Copyright (c) 2005-2012 springside.org.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.lcw.one.util.utils.cipher;

import com.lcw.one.util.utils.Exceptions;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 *
 */
public class DigestUtils {

    public static final String SHA1 = "SHA-1";
    public static final String MD5 = "MD5";
    private static final int BUFFER_LENGTH = 8 * 1024;

    /**
     * 对字符串进行MD5，并返回Hash值的16进制字符串
     * @param input
     * @return
     */
    public static String md5(String input) {
        return Encodes.encodeHex(md5(input.getBytes()));
    }

    /**
     * 对字符串进行SHA1，并返回Hash值的16进制字符串
     * @param input
     * @return
     */
    public static String sha1(String input) {
        return Encodes.encodeHex(sha1(input.getBytes()));
    }

    /**
     * 进行SHA1散列算法
     * @param input 需要散列的值
     * @return
     */
    public static byte[] sha1(byte[] input) {
        return sha1(input, null);
    }

    /**
     * 进行SHA1散列算法
     * @param input 需要散列的值
     * @param salt 盐值
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return sha1(input, salt, 1);
    }

    /**
     *
     * 进行SHA1散列算法
     * @param input 需要散列的值
     * @param salt 盐值
     * @param iterations 散列次数
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 进行MD5散列算法
     * @param input 需要散列的值
     * @return
     */
    public static byte[] md5(byte[] input) {
        return md5(input, null);
    }

    /**
     * 进行MD5散列算法
     * @param input 需要散列的值
     * @param salt 盐值
     * @return
     */
    public static byte[] md5(byte[] input, byte[] salt) {
        return md5(input, salt, 1);
    }

    /**
     *
     * 进行MD5散列算法
     * @param input 需要散列的值
     * @param salt 盐值
     * @param iterations 散列次数
     * @return
     */
    public static byte[] md5(byte[] input, byte[] salt, int iterations) {
        return digest(input, MD5, salt, iterations);
    }

    /**
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, MD5);
    }

    /**
     * 对文件进行sha1散列.
     */
    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, SHA1);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     *
     * @param input      需要散列的值
     * @param algorithm  算法
     * @param salt       盐值
     * @param iterations 散列次数
     * @return
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 对Is输入流进行散列算法
     *
     * @param input     输入流
     * @param algorithm 算法
     * @return
     * @throws IOException
     */
    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] buffer = new byte[BUFFER_LENGTH];
            int read = input.read(buffer, 0, BUFFER_LENGTH);
            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, BUFFER_LENGTH);
            }

            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            throw Exceptions.unchecked(e);
        }
    }

}
