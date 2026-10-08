class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // Both states earn zero after the final day.
        int aheadCanBuy = 0;
        int aheadMustSell = 0;
 
        // Reverse order keeps the next-day pair available.
        for (int day = n - 1; day >= 0; day--) {
            // The current buy state compares both choices.
            int currentCanBuy = Math.max(
                -prices[day] + aheadMustSell,
                aheadCanBuy
            );
 
            // The current sell state compares both choices.
            int currentMustSell = Math.max(
                prices[day] + aheadCanBuy,
                aheadMustSell
            );
 
            // Shift both current values into the ahead pair.
            aheadCanBuy = currentCanBuy;
            aheadMustSell = currentMustSell;
        }
 
        // The first day starts with permission to buy.
        return aheadCanBuy;
    }
}