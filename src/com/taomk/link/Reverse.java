package com.taomk.link;

/**
 * 单链表反转
 * 
 * @author taomk 2017年5月8日 上午10:56:19
 *
 */
public class Reverse {

	public static void main(String[] args) {

		Node head = init();
		Node.show(head);

		Node newHead = reverseByRecursion(head);

		// Node newHead = reverseByLoop(head);

		Node.show(newHead);
	}

	private static Node init() {
		Node head = new Node("a");
		Node node1 = new Node("b");
		Node node2 = new Node("c");
		Node node3 = new Node("d");
		head.setNext(node1);
		node1.setNext(node2);
		node2.setNext(node3);

		return head;
	}

	/**
	 * 递归方式，在反转当前节点之前先反转后续节点
	 */
	public static Node reverseByRecursion(Node head) {
		// head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
		if (head == null || head.getNext() == null) {
//			System.out.println("ReutnData : " + head.getData());
			return head;// 若为空链或者当前结点在尾结点，则直接还回
		}
		Node reHead = reverseByRecursion(head.getNext());// 先反转后续节点 head.getNext()
//		System.out.println("reHeadData : " + reHead.getData());
		head.getNext().setNext(head);// 反转相邻的两个节点
		head.setNext(null);// 将原节点断开
		return reHead;// 反转后新链表的头结点

	}

	/**
	 * 遍历方式，将当前节点的下一个节点缓存后更改当前节点指针
	 */
	public static Node reverseByLoop(Node head) {
		if (head == null)
			return head;
		Node pre = head;// 上一结点
		Node cur = head.getNext();// 当前结点
		Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
		while (cur != null) {// 当前结点为null，说明位于尾结点
			tmp = cur.getNext();
			cur.setNext(pre);// 反转指针域的指向

			// 指针往下移动
			pre = cur;
			cur = tmp;
		}
		// 最后将原链表的头节点的指针域置为null，返回新链表的头结点，即原链表的尾结点
		head.setNext(null);

		return pre;
	}
}
