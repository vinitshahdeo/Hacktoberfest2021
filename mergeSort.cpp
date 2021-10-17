//https://leetcode.com/problems/sort-list/
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    
    /* ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
       if(l1==NULL) return l2;
       if(l2==NULL) return l1;
        
       ListNode* finalHead = NULL;   /// head of the list to return
        
       if(l1->val < l2->val){
           finalHead = l1;
           l1 = l1->next;
       }else{
           finalHead = l2;
           l2 = l2->next;
       }
        ListNode *p = finalHead;   /// pointer to form new list
        
        while(l1 && l2){
            if(l1->val < l2->val){
                p->next = l1;   /// inseting at tail
                l1 = l1->next;
            }else{
                p->next = l2;
                l2 = l2->next;
            }
            p = p->next;
        }
        if(l1){
            p->next = l1;
        }else{
            p->next = l2;
        }
        return finalHead;
    }
    */
//      ListNode* mergeTwoLists(ListNode* a, ListNode* b) {
//         if(a==NULL) return b;
//         if(b==NULL) return a;
        
//         ListNode* newHead = NULL;
//         if(a->val < b->val){
//             newHead = a;
//             newHead ->next = mergeTwoLists(a->next, b);
//         }else{
//             newHead = b;
//             newHead->next = mergeTwoLists(a,b->next);
//         }
//         return newHead;
//     }
//     ListNode* sortList(ListNode* head) {
//         if(head==NULL || head->next==NULL) return head;
//         /// break list into two
//         ListNode *slow = head;
//         ListNode *fast = head->next;
//         while(fast && fast->next){
//             slow = slow->next;
//             fast = fast->next->next;
//         }
//         ListNode *n = slow->next;
//         slow->next = NULL;
        
//         ListNode* a = sortList(head);
//         ListNode* b = sortList(n);
        
//         head = mergeTwoLists(a,b);
//         return head;
//     }
// };