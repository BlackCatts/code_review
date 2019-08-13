/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Implement an algorithm to find the kth to last element of a singly linked list. Can you do it using recursion?
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class FindKthElement {

  private ReverseLinkedList reverseLinkedList;

  public FindKthElement() {
    this.reverseLinkedList = new ReverseLinkedList();
  }

  /**
   * Iterative algorithm based on reverse the singly linked list and iterate over the reversed list to find the kth element.
   * This algorithm has a complexity order in time terms equals to O(N) where N is the number of elements in the list.
   * Remember that our reverse algorithm also has O(N) as complexity order.
   * In space terms we get O(1) because we are not using any auxiliary data structure.
   */
  public ListNode Find(ListNode listNode, int k) {
    ListNode reversedList = reverseLinkedList.reverseIterative(listNode);
    ListNode result = reversedList;
    while (k > 0)
    {
      result = result.getNext();
      if (result == null)
        throw new IndexOutOfBoundsException();
      k--;
    }
    return result;
  }

  /**
   * Another iterative approach based on calculate the list size before to start iterating over the list to find the Kth element.
   * The complexity order in time and space terms is the same than the previous approach.
   */
  public ListNode Find2(ListNode listNode, int k) {
    int size = CalculateListSize(listNode);
    int length = size;
    if (k > (size - 1)) throw new IndexOutOfBoundsException();
    ListNode result = listNode;
    int n = size - 1 - k;
    // System.out.println("n=" + n);
    while (n > 0)
    {
      result = result.getNext();
      n--;
    }
    return result;
  }

  /**
   * Iterative algorithm to resolve this problem based on two pointers to avoid the usage of an auxiliary data structure.
   * The complexity order of this algorithm is O(N) as in the previous implementations, but in space terms,
   * the complexity order is equals to O(1).
   */
  public ListNode Find3(ListNode listNode, int k) {
    ListNode pointer2 = listNode;
    for (int i = 0; i <= k; i++)
    {
      if (pointer2 == null) throw new IndexOutOfBoundsException();
      pointer2 = pointer2.getNext();
    }

    ListNode result = listNode;
    while (pointer2 != null)
    {
      result = result.getNext();
      pointer2 = pointer2.getNext();
    }
    return result;
  }

  private int CalculateListSize(ListNode listNode) {
    int size = 0;
    int length = 0;
    while (listNode != null)
    {
      size++;
      listNode = listNode.getNext();
    }
    // System.out.println("size=" + size);
    return size;
  }
}
