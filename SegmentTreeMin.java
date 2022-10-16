
class SegmentTreeMin {
	private final int[] tree;
	private final int n; // input array length
	
	public SegmentTreeMin(int[] ar) {
		n = ar.length;		
		tree = new int[n * 2];
		System.arraycopy(ar, 0, tree, n, n);
		
		for (int i = n - 1; i > 0; i--) {			
			tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
		}
	}
	
	/**
	 * @param i - index to original array, inclusive
	 * @param value - new value to be saved off
	 */
	public void update(int i, int value) {
        i += n;
        tree[i] = value;
        int newValue;
        
        while (i > 1) {
            i >>= 1; // shift right is the same as divide by 2
            newValue = Math.min(tree[2 * i],tree[2 * i + 1]);
            
            if (tree[i] != newValue) {
            	tree[i] = newValue;	
            } else {
            	return; // since no update is made 
            }
        }
    }

	/**
	 * @param from - inclusive
	 * @param to - exclusive
	 * @return
	 */
    public int max(int from, int to) {
        from += n; // go to second half of the array
        to += n;
        int max = Integer.MIN_VALUE;

        while (from < to) {
            if ((from & 1) == 1) { // 'from' is odd, so it is the right child of its parent, then interval includes node 'from' but doesn't include its parent
                max = Math.min(max, tree[from]);
                from++;
            }
            if ((to & 1) == 1) { // 'to' is odd, so it's the right child of its parent, then might as well use the parent
                to--;
                max = Math.min(max, tree[to]);
            }
            from >>= 1; // shift right is the same as divide by 2 but a little faster
            to >>= 1;            
        }
        
        return max;
    }
}