package com.example.csdict.main;

import android.util.Base64;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class SecyrityOne {


    public  static  String encyrptPassUser(String plain_txt){
        String cyber_txt = null;

        String key = "Mohamed 123";

        try {
            cyber_txt = AESCrypt.encrypt(plain_txt,key);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return cyber_txt;

    }


    public  static  String decryptPassUser(String cyber_txt){
        String key = "Mohamed 123";
        String plain_txt =null ;
        try {
            //plain_txt = AESCrypt.decrypt(cyber_txt,key);
            plain_txt = AESCrypt.decrypt(key,cyber_txt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        return plain_txt;
    }
    public static String encrypt(String value) {
        String key = "aesEncryptionKey";
        String initVector = "encryptionIntVec";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            byte[] encode = Base64.encode(encrypted,0);
            return new String(encode);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String value) {
        String key = "aesEncryptionKey";
        String initVector = "encryptionIntVec";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(value,0));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }



}


