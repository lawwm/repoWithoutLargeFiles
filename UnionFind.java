public class UnionFind {
    /**
     *  dp[i] represents the parent of node i
     *  rank[i] represents the rank of node i
     */
    int[] dp;
    int[] rank;
    
    public UnionFind(int n) {
        dp = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Path compression, set parent of i to root parent
     * @param i
     * @return
     */
    public int find(int i) {
        if (dp[i] == i) {
            return i;
        }
        dp[i] = find(dp[i]);
        return dp[i];
    }

    public boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * Rank, reduce height of tree.
     * @param x
     * @param y
     */
    public void merge(int x, int y) {
        if (isSameSet(x, y)) {
            return;
        }
        int xp = find(x);
        int yp = find(y);

        if (rank[xp] < rank[yp]) {
            dp[xp] = yp;
        } else {
            dp[yp] = dp[xp];
            if (rank[xp] == rank[yp]) {
                rank[xp]++;
            }
        }
    }
}
