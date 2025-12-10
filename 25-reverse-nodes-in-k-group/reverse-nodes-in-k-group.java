/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode curr = head, next = null, prev = null;
        int counter = k;
        while(curr != null && counter >0){
            next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
            counter--;
        }
        if(curr == null && counter > 0){
            curr = prev; prev = null; next=null;
            while(curr !=null){
                next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
        }
        if(counter>0){
            return prev;

        }else{
            head.next = reverseKGroup(curr, k);
        }
        return prev;
    }
}