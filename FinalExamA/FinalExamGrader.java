import java.util.Arrays;
import java.util.Stack;

public class FinalExamGrader {

    public static void main(String[] args) {
        int score1 = testExercise1(); // 14 points
        int score2 = testExercise2(); // 15 points
        int score3 = testExercise3(); // 16 points
        int score4 = testExercise4(); // 15 points
        int score5 = testExercise5(); // 15 points

        int total = score1 + score2 + score3 + score4 + score5;
        int maxTotal = 75;

        System.out.println("---- Final Exam Code Grader ----");
        System.out.println("Exercise 1 (Linked lists):       " + score1 + " / 14");
        System.out.println("Exercise 2 (Browser history):    " + score2 + " / 15");
        System.out.println("Exercise 3 (canFinish DFS):      " + score3 + " / 16");
        System.out.println("Exercise 4 (Selection sort):     " + score4 + " / 15");
        System.out.println("Exercise 5 (Min-heap add):       " + score5 + " / 15");
        System.out.println("---------------------------------");
        System.out.println("Total code score:                " + total + " / " + maxTotal);
        double percent = (maxTotal == 0) ? 0.0 : (100.0 * total / maxTotal);
        System.out.printf("Percentage (code part only):     %.2f%%\n", percent);
    }

    // ----------------------------------------
    // Exercise 1: LinkedList / DoublyLinkedList (14 pts)
    // ----------------------------------------
    private static int testExercise1() {
        int points = 0;

        try {
            // Singly-linked list tests (4 tests, 2 pts each = 8 pts)
            if (testSinglyList(new int[]{1}, 1)) points += 2;
            if (testSinglyList(new int[]{1, 2}, 2)) points += 2;          // even -> (n+1)-th
            if (testSinglyList(new int[]{1, 2, 3, 4}, 3)) points += 2;    // even -> (n+1)-th
            if (testSinglyList(new int[]{10, 20, 30, 40, 50, 60, 70}, 40)) points += 2;

            // Doubly-linked list tests (3 tests, 2 pts each = 6 pts)
            if (testDoublyList(new int[]{5}, 5)) points += 2;
            if (testDoublyList(new int[]{2, 4}, 4)) points += 2;
            if (testDoublyList(new int[]{2, 4, 6, 8}, 6)) points += 2;

        } catch (Throwable t) {
            System.out.println("Exercise 1 tests encountered an error: " + t);
            // keep whatever points accumulated so far
        }

        return points;
    }

    private static boolean testSinglyList(int[] values, int expectedMiddle) {
        FinalExam.LinkedList<Integer> list = new FinalExam.LinkedList<>();
        for (int v : values) {
            list.addLast(v);
        }
        FinalExam.SNode<Integer> mid = list.findMiddle();
        boolean ok = (mid.data == expectedMiddle);
        if (!ok) {
            System.out.println("  [E1 singly] Expected middle " + expectedMiddle +
                    " but got " + mid.data + " for " + Arrays.toString(values));
        }
        return ok;
    }

    private static boolean testDoublyList(int[] values, int expectedMiddle) {
        FinalExam.DoublyLinkedList<Integer> list = new FinalExam.DoublyLinkedList<>();
        for (int v : values) {
            list.addLast(v);
        }
        FinalExam.DNode<Integer> mid = list.findMiddle();
        boolean ok = (mid.data == expectedMiddle);
        if (!ok) {
            System.out.println("  [E1 doubly] Expected middle " + expectedMiddle +
                    " but got " + mid.data + " for " + Arrays.toString(values));
        }
        return ok;
    }

    // ----------------------------------------
    // Exercise 2: BrowserHistory (15 pts)
    // ----------------------------------------
    private static int testExercise2() {
        int points = 0;

        try {
            // Scenario:
            // start at "a.com"
            // visit b, c, d
            // back, back, forward, visit e
            // we check current page at each step
            FinalExam.BrowserHistory bh = new FinalExam.BrowserHistory();

            

            bh.visit("a.com");
            // First visit after construction
            if (checkCurrent(bh, "a.com")) points += 3;

            bh.visit("c.com");
            bh.visit("d.com");
            if (checkCurrent(bh, "d.com")) points += 3;

            bh.back();   // should go to c
            if (checkCurrent(bh, "c.com")) points += 3;

            bh.back();   // should go to b
            if (checkCurrent(bh, "a.com")) points += 3;

            bh.forward(); // should go to c
            if (checkCurrent(bh, "c.com")) points += 3;

            bh.visit("e.com"); // forward stack should be cleared now
            if (checkCurrent(bh, "e.com")) points += 0; // optional, no points, just sanity

        } catch (Throwable t) {
            System.out.println("Exercise 2 tests encountered an error: " + t);
        }

        return points;
    }

    private static boolean checkCurrent(FinalExam.BrowserHistory bh, String expected) {
        String cur = bh.current;
        boolean ok = expected.equals(cur);
        if (!ok) {
            System.out.println("  [E2] Expected current = " + expected + " but got " + cur);
        }
        return ok;
    }

    // ----------------------------------------
    // Exercise 3: canFinish using DFS (16 pts)
    // ----------------------------------------
    private static int testExercise3() {
        int points = 0;

        try {
            // Example 1: from statement
            int[][] pre1 = {{1, 0}};
            boolean r1 = FinalExam.ExamScheduler.canFinish(2, pre1);
            if (r1) points += 4;
            else System.out.println("  [E3] Example 1 should be true but was false.");

            // Example 2: from statement
            int[][] pre2 = {{1, 0}, {0, 1}};
            boolean r2 = FinalExam.ExamScheduler.canFinish(2, pre2);
            if (!r2) points += 4;
            else System.out.println("  [E3] Example 2 should be false but was true.");

            // Larger DAG: possible
            int[][] pre3 = {
                    {1, 0},
                    {2, 1},
                    {3, 2},
                    {4, 2}
            };
            boolean r3 = FinalExam.ExamScheduler.canFinish(5, pre3);
            if (r3) points += 4;
            else System.out.println("  [E3] DAG example should be true but was false.");

            // Larger cycle: impossible
            int[][] pre4 = {
                    {1, 0},
                    {2, 1},
                    {0, 2}
            };
            boolean r4 = FinalExam.ExamScheduler.canFinish(3, pre4);
            if (!r4) points += 4;
            else System.out.println("  [E3] Cycle example should be false but was true.");

        } catch (Throwable t) {
            System.out.println("Exercise 3 tests encountered an error: " + t);
        }

        return points;
    }

    // ----------------------------------------
    // Exercise 4: Selection sort (15 pts)
    // ----------------------------------------
    private static int testExercise4() {
        int points = 0;

        try {
            // Test 1: ascending, 1-arg version (5 pts)
            int[] a1 = {5, 1, 4, 2, 8};
            int[] expected1 = {1, 2, 4, 5, 8};
            FinalExam.SelectionSortUtils.selectionSort(a1);
            if (Arrays.equals(a1, expected1)) {
                points += 5;
            } else {
                System.out.println("  [E4] 1-arg ascending failed: " +
                        Arrays.toString(a1) + " vs " + Arrays.toString(expected1));
            }

            // Test 2: ascending with flag = false (5 pts)
            int[] a2 = {3, -1, 0, 7, 7};
            int[] expected2 = {-1, 0, 3, 7, 7};
            FinalExam.SelectionSortUtils.selectionSort(a2, false);
            if (Arrays.equals(a2, expected2)) {
                points += 5;
            } else {
                System.out.println("  [E4] ascending with flag failed: " +
                        Arrays.toString(a2) + " vs " + Arrays.toString(expected2));
            }

            // Test 3: descending with flag = true (5 pts)
            int[] a3 = {3, -1, 0, 7, 7};
            int[] expected3 = {7, 7, 3, 0, -1};
            FinalExam.SelectionSortUtils.selectionSort(a3, true);
            if (Arrays.equals(a3, expected3)) {
                points += 5;
            } else {
                System.out.println("  [E4] descending with flag failed: " +
                        Arrays.toString(a3) + " vs " + Arrays.toString(expected3));
            }

        } catch (Throwable t) {
            System.out.println("Exercise 4 tests encountered an error: " + t);
        }

        return points;
    }

    // ----------------------------------------
    // Exercise 5: LUCMinHeap.add (15 pts)
    // ----------------------------------------
    private static int testExercise5() {
    int points = 0;
    int maxPoints = 15;
    int tests = 3;
    int perTest = maxPoints / tests;

    System.out.println("Grading Exercise 5 (LUCMinHeap.add)...");
    try {
        // Test 1: small heap, simple values
        FinalExam.LUCMinHeap h1 = new FinalExam.LUCMinHeap(10);
        int[] vals1 = {10, 4, 15, 1};
        for (int v : vals1) {
            h1.add(v);
        }
        boolean t1Root = (h1.size == vals1.length && h1.heap[0] == 1);
        boolean t1Heap = isMinHeap(h1.heap, h1.size);
        boolean t1 = t1Root && t1Heap;
        System.out.println("  [E5-T1] values {10,4,15,1}; root should be 1; heap property must hold: "
                + (t1 ? "PASS" : "FAIL"));
        if (t1) points += perTest;

        // Test 2: more values, different insertion order
        FinalExam.LUCMinHeap h2 = new FinalExam.LUCMinHeap(10);
        int[] vals2 = {20, 5, 7, 3, 18};
        for (int v : vals2) {
            h2.add(v);
        }
        boolean t2Root = (h2.size == vals2.length && h2.heap[0] == 3);
        boolean t2Heap = isMinHeap(h2.heap, h2.size);
        boolean t2 = t2Root && t2Heap;
        System.out.println("  [E5-T2] values {20,5,7,3,18}; root should be 3; heap property must hold: "
                + (t2 ? "PASS" : "FAIL"));
        if (t2) points += perTest;

        // Test 3: include negative numbers and duplicates
        FinalExam.LUCMinHeap h3 = new FinalExam.LUCMinHeap(10);
        int[] vals3 = {0, -2, 5, -2, 3};
        for (int v : vals3) {
            h3.add(v);
        }
        boolean t3Root = (h3.size == vals3.length && h3.heap[0] == -2);
        boolean t3Heap = isMinHeap(h3.heap, h3.size);
        boolean t3 = t3Root && t3Heap;
        System.out.println("  [E5-T3] values {0,-2,5,-2,3}; root should be -2; heap property must hold: "
                + (t3 ? "PASS" : "FAIL"));
        if (t3) points += perTest;

    } catch (Throwable t) {
        System.out.println("Exercise 5 tests encountered an error: " + t);
    }

    System.out.printf("  -> Exercise 5 score: %d / %d\n\n", points, maxPoints);
    return points;
    }

    
    private static boolean isMinHeap(int[] heap, int size) {
    for (int i = 0; i < size; i++) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && heap[i] > heap[left]) return false;
        if (right < size && heap[i] > heap[right]) return false;
    }
    return true;
    }

}