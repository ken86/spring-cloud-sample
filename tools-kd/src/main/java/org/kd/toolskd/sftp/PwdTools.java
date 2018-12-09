package org.kd.toolskd.sftp;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class PwdTools {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static byte[] key = {-57,115,116,127,-6,64,46,-44,84,-90,-43,-31,65,12,73,-25};

    public static byte[] initSecretKey() {
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static byte[] encrypt(byte[] data, Key key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }


    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, Key key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        //byte[] key = initSecretKey();
        byte[] key = {39,-3,-23,-110,25,-9,122,-103,-2,49,21,63,-18,-85,-123,67};
        System.out.println("key："+ showByteArray(key));

        Key k = toKey(key);

        String data ="k123";
//        System.out.println("加密前数据: string:" + data);
//        System.out.println("加密前数据: byte[]:" + showByteArray(data.getBytes()));
//        System.out.println();
//        byte[] encryptData = encrypt(data.getBytes(), k);
//        System.out.println("加密后数据: byte[]:" + showByteArray(encryptData));
//        System.out.println("加密后数据: hexStr:" + Hex.encodeHexString(encryptData));



        byte[] encryptData = {26,126,-62,89,102,115,-79,-67,-81,89,55,-80,-35,93,96,57};

        char[] chars = Hex.encodeHex(encryptData);
        System.out.println(chars);
        System.out.println(showByteArray(Hex.decodeHex(chars)));

        byte[] decryptData = decrypt(encryptData, k);
        System.out.println("解密后数据: byte[]:" + showByteArray(decryptData));
        System.out.println("解密后数据: string:" + new String(decryptData));

    }
}
