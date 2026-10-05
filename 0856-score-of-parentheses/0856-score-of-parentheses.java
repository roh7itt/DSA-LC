class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // New level
                stack.push(0);
            } 
            else {
                // Get inside score
                int inside = stack.pop();

                int score;

                if (inside == 0) {
                    // ()
                    score = 1;
                } else {
                    // (A)
                    score = 2 * inside;
                }

                // Add score to outer level
                int outer = stack.pop();
                stack.push(outer + score);
            }
        }

        return stack.peek();
    }
}