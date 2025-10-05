/*
 * *** DO NOT MODIFY THIS FILE ***
 *
 * This Main runs small automated checks for each part of the assignment.
 * Read the console output carefully. If a test fails, use the message
 * to guide your debugging.
 */

import java.util.*;

public class Main {

    // -------------------------
    // Part 1: AVL Insertion/Rotations
    // -------------------------
    public static int testPart1_AVL() {
        int points = 0;

        // Helper to compare preorder strings
        java.util.function.BiFunction<LUC_AVLTree,String,Boolean> expectPre =
            (t, s) -> t.preorderTraversal().equals(s);

        // ---- RR rotation: insert 10, 20, 30 -> root 20
        {
            LUC_AVLTree t = new LUC_AVLTree();
            t.insert(10); t.insert(20); t.insert(30);
            boolean ok = expectPre.apply(t, "20 10 30 ");
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "AVL RR: (10,20,30) -> '20 10 30 '");
            if (ok) points += 10;
        }

        // ---- LL rotation: insert 30, 20, 10 -> root 20
        {
            LUC_AVLTree t = new LUC_AVLTree();
            t.insert(30); t.insert(20); t.insert(10);
            boolean ok = expectPre.apply(t, "20 10 30 ");
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "AVL LL: (30,20,10) -> '20 10 30 '");
            if (ok) points += 10;
        }

        // ---- RL rotation: insert 10, 30, 20 -> root 20
        {
            LUC_AVLTree t = new LUC_AVLTree();
            t.insert(10); t.insert(30); t.insert(20);
            boolean ok = expectPre.apply(t, "20 10 30 ");
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "AVL RL: (10,30,20) -> '20 10 30 '");
            if (ok) points += 10;
        }

        // ---- LR rotation: insert 30, 10, 20 -> root 20
        {
            LUC_AVLTree t = new LUC_AVLTree();
            t.insert(30); t.insert(10); t.insert(20);
            boolean ok = expectPre.apply(t, "20 10 30 ");
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "AVL LR: (30,10,20) -> '20 10 30 '");
            if (ok) points += 10;
        }

        return points; // /40
    }

    // -------------------------
    // Part 2: TreeProblems (different/removeEven)
    // -------------------------
    public static int testPart2_TreeProblems() {
        int points = 0;

        // removeEven()
        {
            Map<Integer,String> ages = new TreeMap<>();
            ages.put(10, "James");
            ages.put(13, "Tony");
            ages.put(15, "Britany");
            ages.put(16, "Maria");
            ages.put(17, "Geoffrey");

            Map<Integer,String> expected = new TreeMap<>();
            expected.put(13, "Tony");
            expected.put(15, "Britany");
            expected.put(17, "Geoffrey");

            TreeProblems.removeEven(ages);

            boolean ok = ages.equals(expected);
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "TreeMap removeEven");
            if (ok) points += 15;
        }

        // different()
        {
            Set<Integer> s0 = new TreeSet<>(Arrays.asList(9, 7, 6, 3));
            Set<Integer> s1 = new TreeSet<>(Arrays.asList(4, 7, 6, 2));

            Set<Integer> expected = new TreeSet<>(Arrays.asList(2, 3, 4, 9));
            Set<Integer> ans = TreeProblems.different(s0, s1);

            boolean ok = ans.equals(expected);
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "TreeSet different (symmetric difference)");
            if (ok) points += 15;
        }

        return points; // /30
    }

    // -------------------------
    // Part 3: BinaryTree (replaceValueHelper, averageHelper)
    // -------------------------
    public static int testPart3_BinaryTree() {
        int points = 0;

        // Build a small BST so structure is predictable
        BinaryTree bt = new BinaryTree();
        for (int v : new int[]{10, 5, 15, 2, 7, 12, 20}) bt.insert(v);

        // replaceValueHelper test: replace 7 -> 70 (preorder should reflect change)
        {
            bt.replaceValue(7, 70);
            String expectedPre = "10 5 2 70 15 12 20 ";
            boolean ok = bt.preOrder().equals(expectedPre);
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "BinaryTree replaceValueHelper");
            if (ok) points += 15;
        }

        // averageHelper test: sum/count = (10+5+2+70+15+12+20)/7 = 134/7
        {
            double avg = bt.average();
            double expected = 134.0 / 7.0;
            boolean ok = Math.abs(avg - expected) < 1e-9;
            System.out.println((ok ? "[PASS] " : "[FAIL] ") + "BinaryTree averageHelper");
            if (ok) points += 15;
        }

        return points; // /30
    }

    // -------------------------
    // Overall summary
    // -------------------------
    public static void main(String[] args) {
        System.out.println("=== COMP 272 Assignment Autograder ===");

        int total = 0;
        int p1 = testPart1_AVL();        // /40
        int p2 = testPart2_TreeProblems(); // /30
        int p3 = testPart3_BinaryTree(); // /30

        total = p1 + p2 + p3;

        System.out.println("--------------------------------------");
        System.out.println("Part 1 (AVL):         " + p1 + " / 40");
        System.out.println("Part 2 (TreeProblems):" + p2 + " / 30");
        System.out.println("Part 3 (BinaryTree):  " + p3 + " / 30");
        System.out.println("--------------------------------------");
        System.out.println("TOTAL: " + total + " / 100");
    }
}