public class prob_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();
        ListNode l1, l2, sum;
        // Note here that the digits are stored in reverse order
        l1 = new ListNode(); l1.val = 9;
        l2 = new ListNode(); l2.val = 1; l2.next = new ListNode(); l2.next.val = 9;
        sum = solution.addTwoNumbers(l1, l2);
        while(sum != null) {
            System.out.println(sum.val);
            sum = sum.next;
        }
    }
}


//   Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListNode node = this;
        do {
            s.append(node.val);
            s.append(",");
            node = node.next;
        } while(node != null);
        s.deleteCharAt(s.length()-1);
        return String.valueOf(s);
    }
}
 

class Solution_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode();
        ListNode tmp = sum;
        int rem = 0;
        tmp.val=(l1.val+l2.val+rem)%10;
        rem = (l1.val+l2.val+rem)/10;
        l1 = l1.next;
        l2 = l2.next;
        while(l1!=null && l2!=null){
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val=(l1.val+l2.val+rem)%10;
            rem = (l1.val+l2.val+rem)/10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1!=null){
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val=(l1.val+rem)%10;
            rem = (l1.val+rem)/10;
            l1 = l1.next;
        }
        while(l2!=null){
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val=(l2.val+rem)%10;
            rem = (l2.val+rem)/10;
            l2 = l2.next;
        }
        while(rem!=0){
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val=(rem)%10;
            rem = rem/10;
        }
        
        return sum;
    }
}
