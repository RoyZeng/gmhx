package com.gome.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncryptUtils
{
  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  public static String MD5Encode(String origin)
  {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return byteArrayToString(md.digest(origin.getBytes()));
  }

  private static String byteArrayToString(byte[] b)
  {
    StringBuffer resultSb = new StringBuffer();
    for (int i = 0; i < b.length; ++i) {
      byte aB = b[i];
      resultSb.append(byteToHexString(aB));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b)
  {
    int n = b;
    if (n < 0)
      n = 256 + n;

    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  public static void main(String[] args)
  {
    System.out.println("-------->" + MD5Encode("123"));
  }
}