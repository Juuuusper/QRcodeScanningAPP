package com.example.asus.qrcode;

import android.util.Log;

/**
 * Created by ASUS on 2018/10/23.
 */

public class StrBinaryTurn {
    // 灏哢nicode瀛楃涓茶浆鎹㈡垚bool鍨嬫暟缁�
    public boolean[] StrToBool(String input) {
        boolean[] output = Binstr16ToBool(BinstrToBinstr16(StrToBinstr(input)));
        return output;
    }

    // 灏哹ool鍨嬫暟缁勮浆鎹㈡垚Unicode瀛楃涓�
    public String BoolToStr(boolean[] input) {
        String output = BinstrToStr(Binstr16ToBinstr(BoolToBinstr16(input)));
        return output;
    }

    // 灏嗗瓧绗︿覆杞崲鎴愪簩杩涘埗瀛楃涓诧紝浠ョ┖鏍肩浉闅�
    public static String StrToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    // 灏嗕簩杩涘埗瀛楃涓茶浆鎹㈡垚Unicode瀛楃涓�
    public static String BinstrToStr(String binStr) {
        Log.i("!!!!!!!!!!", "BinstrToStr: "+ binStr);
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    // 灏嗕簩杩涘埗瀛楃涓叉牸寮忓寲鎴愬叏16浣嶅甫绌烘牸鐨凚instr
    private String BinstrToBinstr16(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = StrToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 16 - tempStr[i].length(); j > 0; j--)
                output.append('0');
            output.append(tempStr[i] + " ");
        }
        return output.toString();
    }

    // 灏嗗叏16浣嶅甫绌烘牸鐨凚instr杞寲鎴愬幓0鍓嶇紑鐨勫甫绌烘牸Binstr
    private String Binstr16ToBinstr(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = StrToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 0; j < 16; j++) {
                if (tempStr[i].charAt(j) == '1') {
                    output.append(tempStr[i].substring(j) + " ");
                    break;
                }
                if (j == 15 && tempStr[i].charAt(j) == '0')
                    output.append("0" + " ");
            }
        }
        return output.toString();
    }

    // 浜岃繘鍒跺瓧涓茶浆鍖栦负boolean鍨嬫暟缁� 杈撳叆16浣嶆湁绌烘牸鐨凚instr
    private boolean[] Binstr16ToBool(String input) {
        String[] tempStr = StrToStrArray(input);
        boolean[] output = new boolean[tempStr.length * 16];
        for (int i = 0, j = 0; i < input.length(); i++, j++)
            if (input.charAt(i) == '1')
                output[j] = true;
            else if (input.charAt(i) == '0')
                output[j] = false;
            else
                j--;
        return output;
    }

    // boolean鍨嬫暟缁勮浆鍖栦负浜岃繘鍒跺瓧涓� 杩斿洖甯�0鍓嶇紑16浣嶆湁绌烘牸鐨凚instr
    private String BoolToBinstr16(boolean[] input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            if (input[i])
                output.append('1');
            else
                output.append('0');
            if ((i + 1) % 16 == 0)
                output.append(' ');
        }
        output.append(' ');
        return output.toString();
    }

    // 灏嗕簩杩涘埗瀛楃涓茶浆鎹负char
    private static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    // 灏嗗垵濮嬩簩杩涘埗瀛楃涓茶浆鎹㈡垚瀛楃涓叉暟缁勶紝浠ョ┖鏍肩浉闅�
    private static String[] StrToStrArray(String str) {
        return str.split(" ");
    }

    // 灏嗕簩杩涘埗瀛楃涓茶浆鎹㈡垚int鏁扮粍
    private static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    public static void main(String[] args) {
        String str=new String("akjbglakhglor");
        String bin=new String();
        String nib=new String();
        StrBinaryTurn sbt=new StrBinaryTurn();
        bin=sbt.StrToBinstr(str);
        nib=sbt.BinstrToStr(bin);
        System.out.println(bin);
        System.out.println(nib);
    }
}

