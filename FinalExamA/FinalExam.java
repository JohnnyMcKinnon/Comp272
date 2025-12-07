import java.util.Arrays;
import java.util.Stack;

/**
 * COMP 272 – Final Exam
 *
 * Students:
 *  - Do NOT change class or method names or signatures.
 *  - You may add private helper methods.
 *  - Implement all TODOs.
 */

public class FinalExam {

    // ==========================
    // Exercise 1: Middle of a list
    // ==========================

    // Singly linked node
    public static class SNode<E> {
        public E data;
        public SNode<E> next;

        public SNode(E data) {
            this.data = data;
        }
    }

    /**
     * Simple singly-linked list.
     * For this exercise, you must complete findMiddle().
     */
    public static class LinkedList<E> {
        SNode<E> head;

        public void addLast(E value) {
            if (head == null) {
                head = new SNode<>(value);
            } else {
                SNode<E> curr = head;
                while (curr.next != null) curr = curr.next;
                curr.next = new SNode<>(value);
            }
        }

        public SNode<E> findMiddle() {
            if (head == null) return null;

            SNode<E> slow = head;
            SNode<E> fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }


    // Doubly linked node
    public static class DNode<E> {
        public E data;
        public DNode<E> next;
        public DNode<E> prev;

        public DNode(E data) {
            this.data = data;
        }
    }

    /**
     * Simple doubly-linked list.
     * For this exercise, you must complete findMiddle().
     */
    public static class DoublyLinkedList<E> {
        DNode<E> head;
        DNode<E> tail;

        public void addLast(E value) {
            if (head == null) {
                head = tail = new DNode<>(value);
            } else {
                DNode<E> node = new DNode<>(value);
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        public DNode<E> findMiddle() {
            if (head == null) return null;

            DNode<E> left = head;
            DNode<E> right = tail;

            while (left != right && left.next != right) {
                left = left.next;
                right = right.prev;
            }
            return left;
        }
    }

    // ==========================
    // Exercise 2: BrowserHistory with two stacks
    // ==========================

    public static class BrowserHistory {
        Stack<String> back = new Stack<>();
        Stack<String> forward = new Stack<>();
        String current = null;

        public void visit(String url) {
            if (current != null) back.push(current);
            current = url;
            forward.clear();
        }

        public String back() {
            if (back.isEmpty()) return current;
            forward.push(current);
            current = back.pop();
            return current;
        }

        public String forward() {
            if (forward.isEmpty()) return current;
            back.push(current);
            current = forward.pop();
            return current;
        }
    }

    // ==========================
    // Exercise 3: canFinish (graph cycle detection with DFS)
    // ==========================

    public static class ExamScheduler {

        public static boolean canFinish(int numExams, int[][] prerequisites) {
            java.util.List<Integer>[] graph = new java.util.ArrayList[numExams];
            for (int i = 0; i < numExams; i++) {
                graph[i] = new java.util.ArrayList<>();
            }

            // Build adjacency list
            for (int[] p : prerequisites) {
                graph[p[1]].add(p[0]);
            }

            // 0 = unvisited, 1 = visiting, 2 = done
            int[] state = new int[numExams];

            for (int i = 0; i < numExams; i++) {
                if (state[i] == 0) {
                    if (!dfs(i, graph, state)) {
                        return false;
                    }
                }
            }

            return true;
        }

        private static boolean dfs(int node, java.util.List<Integer>[] graph, int[] state) {
            state[node] = 1; // visiting

            for (int nei : graph[node]) {
                if (state[nei] == 1) return false;     // found a cycle
                if (state[nei] == 0) {
                    if (!dfs(nei, graph, state)) return false;
                }
            }

            state[node] = 2; // done
            return true;
        }
    }

    // ==========================
    // Exercise 4: Modified Selection Sort
    // ==========================

    public static class SelectionSortUtils {

        public static void selectionSort(int[] arr, boolean descending) {
            int n = arr.length;

            for (int i = 0; i < n - 1; i++) {
                int idx = i;

                for (int j = i + 1; j < n; j++) {
                    if (!descending) {
                        if (arr[j] < arr[idx]) idx = j;
                    } else {
                        if (arr[j] > arr[idx]) idx = j;
                    }
                }

                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
            }
        }

        public static void selectionSort(int[] arr) {
            selectionSort(arr, false);
        }

        public static String arrayToString(int[] arr) {
            return Arrays.toString(arr);
        }
    }

    // ==========================
    // Exercise 5: Priority Queue – Min Heap
    // ==========================

    public static class LUCMinHeap {
        int[] heap;
        int size;

        public LUCMinHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        int parent(int i) { return (i - 1) / 2; }
        int left(int i) { return 2 * i + 1; }
        int right(int i) { return 2 * i + 2; }

        void swap(int i, int j) {
            int t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;
        }

        public void add(int value) {
            heap[size] = value;
            int i = size;
            size++;

            while (i > 0 && heap[parent(i)] > heap[i]) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        public String toString() {
            return Arrays.toString(Arrays.copyOf(heap, size));
        }
    }
}