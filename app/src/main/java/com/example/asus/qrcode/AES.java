package com.example.asus.qrcode;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ASUS on 2018/10/23.
 */

public class AES {
    /**
     * 加密
     *
     *
     */
    public static String Ecodes(String content, String key) {
        if (content == null || content.length() < 1)
            return null;

        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] byteRresult = cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteRresult.length; i++) {
                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     *
     */
    public static String Dcodes(String content, String key) {
        if (content == null || content.length() < 1)
            return null;

        if (content.trim().length() < 19)
            return content;

        byte[] byteRresult = new byte[content.length() / 2];
        Log.i("byteRresultbyteRresult", "Dcodes: " + byteRresult);
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(key.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//AES/ECB/ZeroBytePadding  AES/CBC/NoPadding  AES/ECB/NoPadding
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteRresult);
            Log.i("result", "Dcodes: " + result);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 详细解释
     * 【ceet为加密的密匙】
     * 【admin为需要加密的字符串】
     * 【67BE5ED967DBA9B9810C295BE6DEF5D5为解密后的字符串】
     * 【如果更改ceet，那么67BE5ED967DBA9B9810C295BE6DEF5D5字符串会发生变化】
     * @param args
     */
    // 调用测试
    public static void main(String[] args) {
        System.out.println("需要加密的内容："+Ecodes("1011000111101101010101011010110101", "ceet"));//第一个参数为需要加密的字符串，第二个为密钥
        System.out.println("经过解密的内容："+Dcodes("5D6052CA1E6B5231F8F9FF91DCD191844703F0F0F1D763964CF31E82A48E52103B968BE54D06889EA3936C5CE9941C13", "ceet"));//第一个为需要解密的编码，第二个为密钥
    }
}