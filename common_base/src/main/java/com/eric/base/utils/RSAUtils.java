package com.eric.base.utils;


import android.util.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    // 进行Base64转码时的flag设置，默认为Base64.DEFAULT
    private static int BASE64_MODE = Base64.DEFAULT;

    /**
     * 将字符串形式的公钥转换为公钥对象
     * 
     * @param publicKeyStr
     * @return
     */
    @androidx.annotation.Nullable
    public static PublicKey keyStrToPublicKey(String publicKeyStr) {
        PublicKey publicKey = null;
        byte[] keyBytes = Base64.decode(publicKeyStr, BASE64_MODE);//未经过Base64编码的就省略，直接getBytes()
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

}
