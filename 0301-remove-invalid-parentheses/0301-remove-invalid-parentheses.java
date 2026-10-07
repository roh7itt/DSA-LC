class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one level
            for (int k = 0; k < size; k++) {

                String current = queue.poll();

                // If valid, add to answer
                if (isValid(current)) {
                    ans.add(current);
                    found = true;
                }

                // If we already found valid strings,
                // don't generate next level
                if (found) {
                    continue;
                }

                // Remove one character
                for (int i = 0; i < current.length(); i++) {

                    char ch = current.charAt(i);

                    // Only remove parentheses
                    if (ch != '(' && ch != ')') {
                        continue;
                    }

                    String next =
                        current.substring(0, i)
                        + current.substring(i + 1);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            // Minimum removals achieved
            if (found) {
                break;
            }
        }

        return ans;
    }

    private boolean isValid(String s) {

        int count = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }

        return count == 0;
    }
}