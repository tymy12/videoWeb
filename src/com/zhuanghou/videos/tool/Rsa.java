package com.zhuanghou.videos.tool;

/**
 * Created by duhui on 2017/12/1.
 */

import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.*;

public class Rsa {
    public Rsa() {
    }


    public static void generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.genKeyPair();
            PublicKey pbkey = kp.getPublic();
            PrivateKey prkey = kp.getPrivate();
// 保存公钥
            FileOutputStream f1 = new FileOutputStream("/Users/duhui/videoWeb/src/com/zhuanghou/videos/tool/pubkey.dat");
            ObjectOutputStream b1 = new ObjectOutputStream(f1);
            b1.writeObject(pbkey);
// 保存私钥
            FileOutputStream f2 = new FileOutputStream("/Users/duhui/videoWeb/src/com/zhuanghou/videos/tool/privatekey.dat");
            ObjectOutputStream b2 = new ObjectOutputStream(f2);
            b2.writeObject(prkey);
        } catch (Exception e) {
        }
    }

    public static String encrypt(String s) {

// 获取公钥及参数e,n

        ObjectInputStream b = null;
        BigInteger c=null;
        try {
            b = new ObjectInputStream(Rsa.class.getResourceAsStream("pubkey.dat"));


            RSAPublicKey pbk = (RSAPublicKey) b.readObject();
            BigInteger e = pbk.getPublicExponent();
            BigInteger n = pbk.getModulus();
//        System.out.println("e= " + e);
//        System.out.println("n= " + n);
// 获取明文m
            byte ptext[] = s.getBytes("UTF-8");
            BigInteger m = new BigInteger(ptext);
// 计算密文c
             c = m.modPow(e, n);
            System.out.println("c= " + c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c + "";
// 保存密文
//        String cs = c.toString();
//        BufferedWriter out =
//                new BufferedWriter(

//        out.write(cs, 0, cs.length());
//        out.close();
    }

    public static void decrypt() throws Exception {
// 读取密文
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(new FileInputStream("com/zhuanghou/videos/tool/encrypt.dat")));
        String ctext = in.readLine();
        BigInteger c = new BigInteger(ctext);
// 读取私钥
        ObjectInputStream b = new ObjectInputStream(Rsa.class.getResourceAsStream("privatekey.dat"));
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
// 获取私钥参数及解密
        BigInteger n = prk.getModulus();
        System.out.println("d= " + d);
        System.out.println("n= " + n);
        BigInteger m = c.modPow(d, n);
// 显示解密结果
        System.out.println("m= " + m);
        byte[] mt = m.toByteArray();
        System.out.println("PlainText is ");
        for (int i = 0; i < mt.length; i++) {
            System.out.print((char) mt[i]);
        }
    }

    public static void main(String args[]) {
        try {
            //generateKey();
            encrypt("1");
            //decrypt();
        } catch (Exception e) {
          //  System.out.println(e.toString());
        }
    }


    public static String privatekey(String publickey) {
        return null;
    }




    public static boolean readToken(HttpServletRequest request ,String s1){
        Cookie[] cookies = request.getCookies();
        String value=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){

                if(cookie.getName().equals(s1)){
                    value= cookie.getValue();
                }
            }
        }



        if(value==null){
            return false;
        }
        String[] strings=value.split(":");
        String key=strings[0]+strings[1];
        //System.out.println("return="+Rsa.encrypt(key).equals(strings[2]));

        return  Rsa.encrypt(key).equals(strings[2]);

    }



}
