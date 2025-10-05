/*
 * *** Johnny McKinnon Comp272 001 ***
 *
 * This file contains two short practice problems using TreeSet/TreeMap.
 * Keep your code simple and readable. No extra methods are needed here.
 */

import java.util.*;

public class TreeProblems {

  /**
   * different()
   *
   * Given two sets of integers, return the set of elements that are in
   * exactly one of them (the symmetric difference).
   *
   * Examples:
   *  A = {3,6,7,9}, B = {2,4,6,7}  =>  {2,3,4,9}
   *
   * Return a TreeSet so the result is sorted ascending.
   */
  public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {

    // TODO: implement using unions/intersections, or direct iteration.
    // Keep it short. Aim for a few lines only.
    return setA; // placeholder so code compiles
  }

  /**
   * removeEven()
   *
   * Given a TreeMap<Integer,String>, remove all pairs where the key is even.
   * Modify the map in place. Keep this neat and safe (no concurrent mod errors).
   */
  public static void removeEven(Map<Integer, String> treeMap) {

    // TODO: implement (one simple approach uses keySet().removeIf(...))
    return; // placeholder so code compiles
  }
}