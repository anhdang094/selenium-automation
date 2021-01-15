package vn.betting.automation.infrastructure.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtils {


  private static final String key = "asdlkjqwepoi!@#)(*";

  private static final String ALGORITHM = "AES";

  public static byte[] encrypt(byte[] plainText) throws Exception
  {
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    return cipher.doFinal(plainText);
  }

  public static byte[] decrypt(byte[] cipherText) throws Exception
  {
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    return cipher.doFinal(cipherText);
  }

}
