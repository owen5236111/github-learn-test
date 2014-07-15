package com.jpe.smt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Uti
{
  public static String md5(String paramString)
  {
    while (true)
    {
      byte[] arrayOfByte;
      StringBuilder localStringBuilder;
      int j;
      try
      {
        arrayOfByte = MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"));
        localStringBuilder = new StringBuilder(2 * arrayOfByte.length);
        int i = arrayOfByte.length;
        j = 0;
        if (j >= i)
          return localStringBuilder.toString();
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        throw new RuntimeException("Huh, MD5 should be supported?", localNoSuchAlgorithmException);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new RuntimeException("Huh, UTF-8 should be supported?", localUnsupportedEncodingException);
      }
      int k = arrayOfByte[j];
      if ((k & 0xFF) < 16)
        localStringBuilder.append("0");
      localStringBuilder.append(Integer.toHexString(k & 0xFF));
      j++;
    }
  }
}