//AlienDictionary.java
import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	System.out.println("Input: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }

        String order = alienOrder(words);
	System.out.println("Output: ");
        if (order.isEmpty()) {
            System.out.println("No valid character order found");
        } else {
            System.out.println(order);
        }

        sc.close();
    }

    // Function to find the alien dictionary order
    public static String alienOrder(String[] words) {
        // Step 1: Build graph and indegree map
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Initialize graph nodes for all unique chars
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Step 2: Build edges based on word differences
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1];
            String curr = words[i];
            int minLen = Math.min(prev.length(), curr.length());

            boolean edgeFound = false;
            for (int j = 0; j < minLen; j++) {
                char c1 = prev.charAt(j);
                char c2 = curr.charAt(j);
                if (c1 != c2) {
                    // Add edge c1 -> c2 if not already added
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    edgeFound = true;
                    break;
                }
            }
            // Edge case: Check prefix condition, e.g., "abc" then "ab"
            if (!edgeFound && prev.length() > curr.length()) {
                // Invalid case
                return "";
            }
        }

        // Step 3: Topological sort (BFS)
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);

            for (char nei : graph.get(c)) {
                indegree.put(nei, indegree.get(nei) - 1);
                if (indegree.get(nei) == 0) {
                    queue.offer(nei);
                }
            }
        }

        // If all chars included in order, return it
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
