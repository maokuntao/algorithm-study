package com.taomk.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * 
 * 一致性Hash算法：
 * 
 * http://www.cnblogs.com/hapjin/p/4737207.html
 * 
 * </pre>
 * 
 * @author taomk 2017年9月19日 下午4:27:43
 *
 */
public class ConsistentHash {

	public static void main(String[] args) {

	}

}

/**
 * 实现一致性hash算法时使用的hash函数，使用MD5算法来保证平衡性。
 * 
 * @author taomk 2017年9月19日 下午4:29:43
 *
 */
class HashFunction {

	private MessageDigest md5 = null;

	public long hash(String key) {
		if (md5 == null) {
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		md5.reset();
		md5.update(key.getBytes());
		byte[] digestBytes = md5.digest();

		// 具体的哈希函数实现细节--每个字节 & 0xFF 再移位
		long result = (
				(long) (digestBytes[3] & 0xFF) << 24) 
				| ((long) (digestBytes[2] & 0xFF) << 16
				| ((long) (digestBytes[1] & 0xFF) << 8) 
				| (long) (digestBytes[0] & 0xFF)
				);
		return result & 0xFFFFFFFFL;
	}
}
