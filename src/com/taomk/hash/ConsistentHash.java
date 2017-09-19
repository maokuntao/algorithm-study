package com.taomk.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

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
public class ConsistentHash<T> {

	private final HashFunction hashFunction;
	// 虚拟节点复制因子，总虚拟节点数 = numberOfReplicas * 实际节点数
	private final int numberOfReplicas;
	// 存储的是虚拟节点到实际节点的映射
	private final SortedMap<Long, T> circle = new TreeMap<Long, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		initNode(nodes);
	}

	/**
	 * 节点初始化
	 * 
	 * @param nodes
	 */
	private void initNode(Collection<T> nodes) {
		for (T t : nodes) {
			// 一个节点，对应numberOfReplicas个虚拟节点
			for (int i = 0; i < numberOfReplicas; i++) {
				// 不同的虚拟节点（只有i不同），有不同的hash值，但都对应同一个实际的节点
				// 虚拟的节点一般是均匀地分布在环上，数据存储在顺时针最近的那个虚拟node上
				circle.put(hashFunction.hash(t.toString() + i), t);
			}
		}
	}

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
		long result = ((long) (digestBytes[3] & 0xFF) << 24) | ((long) (digestBytes[2] & 0xFF) << 16
				| ((long) (digestBytes[1] & 0xFF) << 8) | (long) (digestBytes[0] & 0xFF));
		return result & 0xFFFFFFFFL;
	}
}
