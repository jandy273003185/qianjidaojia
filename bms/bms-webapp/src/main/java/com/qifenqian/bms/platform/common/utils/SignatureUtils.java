package com.qifenqian.bms.platform.common.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * 数字签名工具类
 */
public class SignatureUtils {

  private static final Logger logger = Logger.getLogger(SignatureUtils.class.getName());

  /**
   * 签名主要算法
   */
  public enum SingnatureAlgorithm {
    RawDSA, SHA1withDSA, MD2withRSA, MD5withRSA, SHA1withRSA, SHA256withRSA, SHA384withRSA, SHA512withRSA
  }

  /**
   * 对数据进行签名
   */
  public static byte[] sign(byte[] data, String algorithm, PrivateKey privateKey) throws Exception {

    Signature signature;

    // 初始化签名算法
    try {
      signature = Signature.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      logger.log(Level.WARNING, "不支持的签名算法: " + algorithm, e);
      throw new Exception("签名算法不被支持", e);
    }

    // 初始化私钥
    try {
      signature.initSign(privateKey);
    } catch (InvalidKeyException e) {
      logger.log(Level.WARNING, "非法的私钥", e);
      throw new Exception("非法的私钥", e);
    }

    // 进行签名
    try {

      signature.update(data);
      return signature.sign();

    } catch (SignatureException e) {
      logger.log(Level.WARNING, "签名非法",e);
      throw new Exception("签名非法", e);
    }

  }

  /**
   * 对数据进行签名
   */
  public static boolean verify(byte[] data, String algorithm, PublicKey publicKey, byte[] sign)
      throws Exception {

    Signature signature = Signature.getInstance(algorithm);
    signature.initVerify(publicKey);
    signature.update(data);

    // 验证签名是否正常
    return signature.verify(sign);

  }

}
