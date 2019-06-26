package com.example.demo2.shiro.md5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

public class ShiroUtil {

    /**
     * 生成32的随机盐值
     */
    public static String createSalt(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成加盐加密后的密码
     * @param password 明文密码
     * @param salt 盐
     * @return
     */
    public static String createPwdBySalt(String password, String salt){
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource saltBytes = ByteSource.Util.bytes(salt);//以UUID作为盐值
        int hashIterations = 1024;//加密1024次
        SimpleHash hash = new SimpleHash(hashAlgorithmName, password, saltBytes,hashIterations);
        return hash.toString();
    }

    public static void main(String[] args) {
        String salt = createSalt();
        String pwdBySalt = createPwdBySalt("123456", salt);
        System.out.println("盐：" + salt);
        System.out.println("密码：" + pwdBySalt);
    }

}
