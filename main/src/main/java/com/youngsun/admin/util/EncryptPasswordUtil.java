package com.youngsun.admin.util;

import com.youngsun.admin.vo.UserVo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class EncryptPasswordUtil {

    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(UserVo user) {
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(user.getNickName()), hashIterations).toHex();
        user.setPassword(newPassword);
    }

    public static void getEncryptedPassword() {
        UserVo user = new UserVo();
        user.setNickName("aaa");
        user.setPassword("aaa");
        EncryptPasswordUtil.encryptPassword(user);
        System.out.println(user);
    }
}
