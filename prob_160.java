import java.util.Arrays;
import java.util.List;

/**
 * 160. Intersection of Two Linked Lists
 * Easy
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 *
 * For example, the following two linked lists begin to intersect at node c1:
 *
 *
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 *
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 *
 * The inputs to the judge are given as follows (your program is not given these inputs):
 *
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 */
public class prob_160 {
    public static void main(String[] args) {
        Solution_160 solution = new Solution_160();
        int intersectVal, skipA, skipB;
        List<Integer> listA, listB;
        intersectVal = 8; skipA = 2; skipB = 3;
        listA = Arrays.asList(4,1,8,4,5);
        listB = Arrays.asList(5,6,1,8,4,5);
        ListNode headA = new ListNode(listA.get(0));
        ListNode tmpHead = headA;
        for (int i = 1; i < skipA; i++) {
            tmpHead.next = new ListNode(listA.get(i));
            tmpHead = tmpHead.next;
        }
        ListNode headB = new ListNode(listB.get(0));
        ListNode tmpHead_2 = headB;
        for (int i = 1; i < listB.size(); i++) {
            tmpHead_2.next = new ListNode(listB.get(i));
            tmpHead_2 = tmpHead_2.next;
            if(i == skipB) tmpHead.next = tmpHead_2;
        }
        System.out.println(headA);
        System.out.println(headB);
        System.out.println(solution.getIntersectionNode(headA, headB));
    }
}

/**
 * Solution using list traversal
 * Time Complexity - O(m+n), Space Complexity - O(1)
 * Here m,n are the length of the lists
 * For the ListNodes to be equal, they have to have the same length, thus iterate the longer list till the size of remaining elements in the lists are equal
 * Then compare equality of the nodes - hear we are checking for the reference of the node to be matched, not the val of the node
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = 0, sizeB = 0;
        ListNode node = headA, node_2;
        do {
            sizeA++;
            node = node.next;
        } while(node != null);
        node = headB;
        do {
            sizeB++;
            node = node.next;
        } while(node != null);
        if(sizeA > sizeB) {
            node = headA;
            node_2 = headB;
        } else {
            node = headB;
            node_2 = headA;
        }
        for (int i = 0; i < Math.abs(sizeA-sizeB); i++) {
            node = node.next;
        }
        do {
            if(node == node_2) {
                return node;
            }
            node = node.next;
            node_2 = node_2.next;
        } while(node != null && node_2 != null);
        return null;
    }
}