package vn.betting.automation.infrastructure.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Hex;

public class HashUtils {


  public static String hashSHA1(String input) throws NoSuchAlgorithmException {
    MessageDigest mDigest = MessageDigest.getInstance("SHA1");
    byte[] result = mDigest.digest(input.getBytes());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.length; i++) {
      sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }

  public static String hashMD5(String plainText) throws NoSuchAlgorithmException {
    if (plainText == null) {
      throw new NullPointerException();
    }
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] buffer = plainText.getBytes();
    md.reset();
    md.update(buffer);
    byte[] msgDigest = md.digest();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < msgDigest.length; i++) {
      String hex = Integer.toHexString(0xff & msgDigest[i]);
      if (hex.length() == 1) {
        result.append('0');
      }
      result.append(hex);
    }
    return result.toString();
  }

  public static String getChecksum(String text) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(text.getBytes());
      byte[] digest = md.digest();
      StringBuffer sb = new StringBuffer();
      for (byte b : digest) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();
    } catch (Exception e) {
      return "";
    }
  }

  public static String hashSHA256Base64(String input) throws NoSuchAlgorithmException {
    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
    byte[] shaByteArr = mDigest.digest(StringUtils.encodeUTF8(input));

    return DatatypeConverter.printBase64Binary(shaByteArr);
  }

  public static String SHA1(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      md.update(input.getBytes());
      byte[] mb = md.digest();
      String out = "";
      for (int i = 0; i < mb.length; i++) {
        byte temp = mb[i];
        String s = Integer.toHexString(new Byte(temp));
        while (s.length() < 2) {
          s = "0" + s;
        }
        s = s.substring(s.length() - 2);
        out += s;
      }
      return out;
    } catch (NoSuchAlgorithmException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    return "";
  }

  public static String hashSHA256ToHex(String input) throws NoSuchAlgorithmException {
    MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
    byte[] shaByteArr = mDigest.digest(StringUtils.encodeUTF8(input));
    return Hex.encodeHexString(shaByteArr);
  }

}
