import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 */
public class IntersectionOfTwoLinkedLists {


    /**
     * Class for nodes in the linked list.
     */
    static class ListNode {
    
        // **** members ****
        int         val;
        ListNode    next;
    
        // **** constructor(s) ****
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    
        // **** ****
        @Override
        public String toString() {
            return "" + this.val;
        }
    }


    /**
     * Populate linked list from the contents of the specified array.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static ListNode populate(int[] arr, int skip, ListNode[] intersect) {
    
        // **** sanity ckeck(s) ****
        if (arr.length == 0) return null;
    
        // **** initialization ****
        ListNode head   = null;
        ListNode tail   = null;
    
        // **** traverse array inserting nodes to the linked list ****
        for (int i = 0; i < arr.length; i++) {
    
            // ???? ????
            // System.out.println("<<< arr[" + i + "]: " + arr[i]);

            // **** check if this is the intersect node (if needed) ****
            if (intersect[0] != null && i == skip) {

                // **** add this node to linked list ****
                tail.next = intersect[0];

                // **** return head of linked list ****
                return head;
            }

            // *** allocate node to insert into linked list ****
            ListNode node = new ListNode(arr[i]);

            // **** save this node (if needed) ****
            if (intersect[0] == null && i == skip)
                intersect[0] = node;

            // **** insert node into linked list ****
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = tail.next;
            }

            // ???? ????
            // System.out.println("<<< head: " + toString(head));
        }
    
        // **** return head of linked list ****
        return head;
    }


    /**
     * Generate string representation of linked list.
     * Stops after last node or cycle detected.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static String toString(ListNode head) {
    
        // **** sanity check(s) ****
        if (head == null) return "";
    
        // **** initialization ****
        StringBuilder sb = new StringBuilder();
    
        // **** traverse link list ****
        for (ListNode p = head; p != null; p = p.next) {
            sb.append(p.val);
            if (p.next != null)
                sb.append("->");
        }
    
        // **** return string representation of linked list ****
        return sb.toString();
    }


    /**
     * Given the heads of two singly linked-lists headA and headB,
     * return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     * 
     * Using a hash set.
     * 
     * Runtime: O(n + m) - Space: O(n)
     * 
     * Runtime: 7 ms, faster than 26.61% of Java online submissions.
     * Memory Usage: 43.1 MB, less than 23.58% of Java online submissions.
     * 
     * 39 / 39 test cases passed.
     * Status: Accepted
     * Runtime: 7 ms
     * Memory Usage: 43.1 MB
     */
    static public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
        
        // **** sanity checks ****
        if (headA == null ||  headB == null) return null;

        // **** initialization ****
        HashSet<ListNode> hs = new HashSet<>();

        // **** traverse headA linked list populating hash set ****
        for (ListNode p = headA; p != null; p = p.next)
            hs.add(p);

        // **** traverse headB linked list ****
        for (ListNode p = headB; p != null; p = p.next)
            if (hs.contains(p)) return p;

        // **** lists do NOT intersect ****
        return null;
    }


    /**
     * Given the heads of two singly linked-lists headA and headB,
     * return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     * 
     * Using two pointers.
     * 
     * Execution: O(n + m) - Space: O(1)
     * 
     * Runtime: 1 ms, faster than 97.75% of Java online submissions.
     * Memory Usage: 41.5 MB, less than 88.45% of Java online submissions.
     * 
     * 39 / 39 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 41.5 MB
     */
    static public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        
        // **** sanity checks ****
        if (headA == null ||  headB == null) return null;

        // **** 1. initialization ****
        ListNode p = headA;
        ListNode q = headB;

        // **** 2. traverse both lists ****
        while (p != null && q != null) {
            p = p.next;
            q = q.next;
        }

        // ???? ????
        System.out.println("<<< [1] p: " + p + " q: " + q);        

        // **** 3. one pointer reached the end of a list, redirect it to the other head ****
        if (p == null) p = headB;
        else if (q == null) q = headA;

        // **** traverse both lists ****
        while (p != null && q != null) {
            p = p.next;
            q = q.next;
        }

        // ???? ????
        System.out.println("<<< [2] p: " + p + " q: " + q);

        // **** 4. one pointer reached the end of a list, redirect it to the other head ****
        if (p == null) p = headB;
        else if (q == null) q = headA;

        // **** traverse both lists****
        while (p != null && q != null) {

            // **** 5 & 6 found intersection node ****
            if (p.equals(q)) return p;

            // **** increment both pointers ****
            p = p.next;
            q = q.next;
        }

        // ???? ????
        System.out.println("<<< [3] p: " + p + " q: " + q);

        // **** 7. lists do NOT intersect ****
        return null;
    }


    /**
     * Given the heads of two singly linked-lists headA and headB,
     * return the node at which the two lists intersect.
     * If the two linked lists have no intersection at all, return null.
     * 
     * Using two pointers (reduced code).
     * 
     * Execution: O(n + m) - Space: O(1)
     * 
     * Runtime: 1 ms, faster than 97.75% of Java online submissions.
     * Memory Usage: 41.4 MB, less than 98.04% of Java online submissions.
     * 
     * 39 / 39 test cases passed.
     * Status: Accepted
     * Runtime: 1 ms
     * Memory Usage: 41.4 MB
     */
    static public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        // **** sanity checks ****
        if (headA == null ||  headB == null) return null;

        // **** initialization ****
        ListNode p = headA;
        ListNode q = headB;

        // **** traverse both lists and swap pointers as needed ****
        while (p != q) {
            p = p == null ? p = headB : p.next;
            q = q == null ? q = headA : q.next;
        }

        // ???? ????
        System.out.println("<<< p: " + p + " q: " + q);        

        // **** intersection or null ****
        return p;
    }


    /**
     * Test scaffold.
     * 
     * !!! NOT PART OF SOLUTION !!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** initialization ****
        ListNode[] intersect = new ListNode[1];

        // **** open buffered reder ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read intersectVal ****
        int intersectVal = Integer.parseInt(br.readLine().trim());

        // **** read values for linked list A****
        int[] arrA = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** read values for linked list A****
        int[] arrB = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** read `skipA` ****
        int skipA = Integer.parseInt(br.readLine().trim());
        
        // **** read `skipB` ****
        int skipB = Integer.parseInt(br.readLine().trim());

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< intersectVal: " + intersectVal);
        System.out.println("main <<<         arrA: " + Arrays.toString(arrA));
        System.out.println("main <<<        skipA: " + skipA);
        System.out.println("main <<<         arrB: " + Arrays.toString(arrB));
        System.out.println("main <<<        skipB: " + skipB);

        // **** populate linked list head1 ****
        ListNode headA = populate(arrA, skipA, intersect);
        
        // ???? ????
        System.out.println("main <<<     headA: " + toString(headA));
        System.out.println("main <<< intersect: " + intersect[0]);

        // **** populate linked list head2 ****
        ListNode headB = populate(arrB, skipB, intersect);
        
        // ???? ????
        System.out.println("main <<<     headB: " + toString(headB));
        System.out.println("main <<< intersect: " + intersect[0]);

        // **** invoke function of interest and display result ****
        System.out.println("main <<< getIntersectionNode0: " + getIntersectionNode0(headA, headB));
    
        // **** invoke function of interest and display result ****
        System.out.println("main <<< getIntersectionNode1: " + getIntersectionNode1(headA, headB));

        // **** invoke function of interest and display result ****
        System.out.println("main <<<  getIntersectionNode: " + getIntersectionNode(headA, headB));
    }

}