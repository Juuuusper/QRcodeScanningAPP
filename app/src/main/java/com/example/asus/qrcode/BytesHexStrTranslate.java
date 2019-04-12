package com.example.asus.qrcode;

import java.util.Arrays;

/**
 * Created by ASUS on 2018/10/23.
 */

public class BytesHexStrTranslate {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 鏂规硶涓�锛�
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun1(byte[] bytes) {
        // 涓�涓猙yte涓�8浣嶏紝鍙敤涓や釜鍗佸叚杩涘埗浣嶆爣璇�
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 浣跨敤闄や笌鍙栦綑杩涜杞崲
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }

    /**
     * 鏂规硶浜岋細
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun2(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 鍒╃敤浣嶈繍绠楄繘琛岃浆鎹紝鍙互鐪嬩綔鏂规硶涓�鐨勫彉绉�
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 鏂规硶涓夛細
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun3(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 浣跨敤String鐨刦ormat鏂规硶杩涜杞崲
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return buf.toString();
    }

    /**
     * 灏�16杩涘埗瀛楃涓茶浆鎹负byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    public static void main(String[] args) throws Exception {
        byte[] bytes = "娴嬭瘯".getBytes("utf-8");
        System.out.println("瀛楄妭鏁扮粍涓猴細" + Arrays.toString(bytes));
        System.out.println("鏂规硶涓�锛�" + bytesToHexFun1(bytes));
        System.out.println("鏂规硶浜岋細" + bytesToHexFun2(bytes));
        System.out.println("鏂规硶涓夛細" + bytesToHexFun3(bytes));

        System.out.println("==================================");

        String str = "e6b58be8af95";
        System.out.println("杞崲鍚庣殑瀛楄妭鏁扮粍锛�" + Arrays.toString(toBytes(str)));
        System.out.println(new String(toBytes(str), "utf-8"));
    }
}



