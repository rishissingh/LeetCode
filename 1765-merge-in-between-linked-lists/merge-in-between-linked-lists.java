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
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode currA = list1;
        for(int i=0;i<a-1;i++){
            currA = currA.next;
        }
        ListNode currB = currA;
        for(int i=0;i<=b-a+1;i++){
            currB = currB.next;
        }
        currA.next = list2;

        ListNode tail2 = list2;
        while(tail2.next != null){
            tail2 = tail2.next;
        }
        tail2.next = currB;

        return list1;
    }
}