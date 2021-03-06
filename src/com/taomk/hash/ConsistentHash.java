package com.taomk.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <pre>
 * 
 * 一致性Hash算法：
 * 
 * http://www.cnblogs.com/hapjin/p/4737207.html
 * 
 * http://www.cnblogs.com/lpfuture/p/5796398.html
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
			add(t);

		}
	}

	/**
	 * 增加一个节点（实际节点）
	 * 
	 * @param t
	 */
	public void add(T t) {
		// 一个节点，对应numberOfReplicas个虚拟节点
		for (int i = 0; i < numberOfReplicas; i++) {
			// 不同的虚拟节点（只有i不同），有不同的hash值，但都对应同一个实际的节点
			// 虚拟的节点一般是均匀地分布在环上，数据存储在顺时针最近的那个虚拟node上
			circle.put(hashFunction.hash(t.toString() + i), t);
		}
	}

	/**
	 * 删除一个节点（实际节点）
	 * 
	 * @param t
	 */
	public void remove(T t) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(t.toString() + i));
		}
	}

	/**
	 * 获取节点的值
	 * 
	 * @param key
	 * @return
	 */
	public T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}

		long hashValue = hashFunction.hash(key.toString());

		// 计算hash值相邻的最近的node
		if (!circle.containsKey(hashValue)) {
			SortedMap<Long, T> tailMap = circle.tailMap(hashValue);
			hashValue = tailMap.isEmpty() ? circle.firstKey() : circle.firstKey();
		}

		return circle.get(hashValue);
	}

	/**
	 * 获取虚拟节点数量
	 * 
	 * @return
	 */
	public int getSize() {
		return circle.size();
	}

	/**
	 * 计算各个虚拟节点直接间隔
	 */
	public void testBalance() {
		Set<Long> keySet = circle.keySet();

		System.out.println("-----location of each node are follows : -----");
		for (Long node : keySet) {
			System.out.println(circle.get(node));
		}

		System.out.println("-----each location 's distance are follows : -----");
		SortedSet<Long> sortedSet = new TreeSet<Long>(keySet);
		Iterator<Long> i1 = sortedSet.iterator();
		Iterator<Long> i2 = sortedSet.iterator();
		if (i2.hasNext()) {// i2向后移动一个位置
			i2.next();
		}
		while (i1.hasNext() && i2.hasNext()) {
			Long long1 = (Long) i1.next();
			Long long2 = (Long) i2.next();
			System.out.println((long2 - long1));

		}
	}

	public static void main(String[] args) {

		Set<String> nodes = new HashSet<String>();
		nodes.add("A");
		nodes.add("B");
		nodes.add("C");

		ConsistentHash<String> consistentHash = new ConsistentHash<String>(new HashFunction(), 3, nodes);
		consistentHash.add("D");

		System.out.println("size of hash circle : " + consistentHash.getSize());

		consistentHash.testBalance();
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
