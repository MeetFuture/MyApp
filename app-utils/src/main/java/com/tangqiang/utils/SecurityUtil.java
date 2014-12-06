package com.tangqiang.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.log4j.Logger;

/**
 * ASE密钥生成
 * 
 * @author 汤强
 * @since 2013-8-1 下午2:13:56
 * @project app-utils
 * @package com.tangqiang.utils
 */
public class SecurityUtil {
	private static Logger logger = Logger.getLogger(SecurityUtil.class);

	/**
	 * 生成一个AES密钥
	 * 
	 * @return 密钥
	 * @throws Exception
	 */
	public static Key getAESKey() throws Exception {
		logger.debug("开始生成AES密钥!");
		long lBeginTime = System.currentTimeMillis();
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		SecureRandom sr = new SecureRandom();
		keyGen.init(sr);
		Key key = keyGen.generateKey();
		long lEndTime = System.currentTimeMillis();
		logger.debug("生成AES密钥结束,花费时间:" + (lEndTime - lBeginTime));
		return key;
	}

	/**
	 * 生成密钥文件
	 * 
	 * @param sFile
	 *            生成的文件名
	 * @param sMode
	 *            密钥的编码 "AES"
	 * @return boolean 是否成功
	 */
	public boolean createKeyFile(String sMode, String sFile) {
		try {
			logger.debug("开始生成:" + sMode + "密钥文件!");
			long lBeginTime = System.currentTimeMillis();
			KeyGenerator keyGen = KeyGenerator.getInstance(sMode);
			SecureRandom sr = new SecureRandom();
			keyGen.init(sr);
			Key key = keyGen.generateKey();

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(sFile));
			out.writeObject(key);
			out.close();
			long lEndTime = System.currentTimeMillis();
			logger.debug("生成密钥文件结束,花费时间:" + (lEndTime - lBeginTime));
			return true;
		} catch (Exception e) {
			logger.debug("生成密钥文件失败!" + e);
			return false;
		}
	}

	/**
	 * 文件操作
	 * 
	 * @category doFile(Cipher.ENCRYPT_MODE, sEncryptFile, sDecryptFile, sKeyFile); doFile(Cipher.DECRYPT_MODE, sEncryptFile, sDecryptFile, sKeyFile);
	 * @param type
	 *            类型 加/解码 Cipher.ENCRYPT_MODE(1)/Cipher.DECRYPT_MODE(2)
	 * @param sSourFile
	 *            源文件
	 * @param sDesFile
	 *            目标文件
	 * @param sKeyFile
	 *            密钥文件
	 * @return boolean 是否成功
	 */
	public static boolean doFile(int type, String sSourFile, String sDesFile, String sKeyFile) {
		boolean bSuccess = false;
		try {
			/** 获得密钥文件 **/
			ObjectInputStream objectIns = new ObjectInputStream(new FileInputStream(sKeyFile));
			Key key = (Key) objectIns.readObject();
			objectIns.close();
			String sKeyMode = key.getAlgorithm();
			/** 密钥文件获取结束 **/
			logger.debug("获取密钥文件成功!密钥类型为:" + sKeyMode);
			if (type == Cipher.ENCRYPT_MODE || type == Cipher.DECRYPT_MODE) {
				logger.debug("操作文件的模式为:" + type + " (1:加密 2:解密)");
			} else {
				logger.debug("请输入正确的类型!");
				return false;
			}

			InputStream ins = new FileInputStream(sSourFile);
			OutputStream ous = new FileOutputStream(sDesFile);
			Cipher cipher = Cipher.getInstance(sKeyMode);
			cipher.init(type, key);
			bSuccess = cryptFile(ins, ous, cipher);
		} catch (Exception e) {
			logger.debug("操作失败!" + e.toString());
		}
		return bSuccess;
	}

	/**
	 * 操作文件
	 * 
	 * @param ins
	 *            输入流
	 * @param ous
	 *            输出流
	 * @param cipher
	 * @return
	 * @throws Exception
	 */
	private static boolean cryptFile(InputStream ins, OutputStream ous, Cipher cipher) throws Exception {
		logger.debug("开始操作文件!");
		// 处理文件
		int iBlockSize = cipher.getBlockSize();
		int iOutputSize = cipher.getOutputSize(iBlockSize);
		byte[] InByte = new byte[iBlockSize];
		byte[] OutByte = new byte[iOutputSize];
		int iInLength = 0;
		boolean bMore = true;
		// 读取文件并加解码
		while (bMore) {
			iInLength = ins.read(InByte);
			if (iInLength == iBlockSize) {
				int iOutLength = cipher.update(InByte, 0, iBlockSize, OutByte);
				ous.write(OutByte, 0, iOutLength);
			} else {
				// 如果读取的长度没有完整的填充字节数组，表示到了文件尾 需要特殊处理
				bMore = false;
			}
		}
		if (iInLength > 0) {
			OutByte = cipher.doFinal(InByte, 0, iInLength);
		} else {
			OutByte = cipher.doFinal();
		}
		ous.write(OutByte);
		ins.close();
		ous.close();
		logger.debug("文件操作成功!");
		return true;
	}
}
