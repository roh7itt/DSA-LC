class Solution {
    String s;
    int index;
    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parse();

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse() {

        // Final result of current expression
        Set<String> result = new HashSet<>();

        // Current concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            // Comma means UNION
            if (ch == ',') {

                result.addAll(current);

                current.clear();
                current.add("");

                index++;

            } else {

                // Get next expression
                Set<String> next;

                if (ch == '{') {

                    index++; // skip '{'

                    next = parse();

                    index++; // skip '}'

                } else {

                    // Single character
                    next = new HashSet<>();
                    next.add(String.valueOf(ch));

                    index++;
                }

                // Concatenate current × next
                current = concatenate(current, next);
            }
        }

        // Add last part
        result.addAll(current);

        return result;
    }

    private Set<String> concatenate(
        Set<String> a,
        Set<String> b
    ) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}