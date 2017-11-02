package com.lcw.one.util.utils.cipher.password;

import com.lcw.one.util.utils.RandomUtils;
import com.lcw.one.util.utils.cipher.DigestUtils;
import com.lcw.one.util.utils.cipher.Encodes;

/**
 * @version 2017-10-30.
 * @auth Licw
 */
public class MD5PasswordUtil implements IPassWordUtil {

    @Override
    public String encryptPassword(String plainPassword) {
        byte[] salt = RandomUtils.randomBytes(PasswordUtils.SALT_SIZE);
        byte[] hashPassword = DigestUtils.md5(plainPassword.getBytes(), salt, PasswordUtils.HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    @Override
    public boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = DigestUtils.md5(plainPassword.getBytes(), salt, PasswordUtils.HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }
}
