public class FenwickComments {
        long[] bit;
 
    public FenwickComments(int arr[]) {
        bit = new long[arr.length + 1]; // index starts from 1 not 0.
 
        // store the actual values in BIT[] using update() alternative but sucks
        // for (int i = 0; i < arr.length; i++) {
        //     update(i, arr[i]);
        // }
        for (int i = 0; i < arr.length; i++) {
            int index = i+1;
            bit[index] += arr[i];
            int parent = index + (index & -index); // index of parent
            if (parent < bit.length) {
                bit[parent] += bit[index];
            }
        }
    }
 
    // return sum of arr[0..index].
    public long sum(int index) {
        long totalSum = 0;
 
        // index in BIT[] starts from 1
        index = index + 1;
 
        // traverse ancestors of BIT[index]
        while (index > 0) {
            // add current element of BIT to sum
            totalSum += bit[index];
 
            // move index to parent node in Sum View
            index -= index & (-index);
        }
        return totalSum;
    }
 
    // return sum of the given range
    public long rangeSum(int from, int to) {
        if (from >= 0 && to >= 0 && to >= from) {
            return sum(to) - sum(from - 1);
        } else {
            return -1;
        }
    }
 
    // update a node in Binary Index Tree at given index, the given value is the 'delta' value
    // compared with the original array arr[], not array BIT[]. This delta value is added to BIT[i] and
    // all of its ancestors.
    public void update(int index, int val) {
        // index in BIT[] starts from 1
        index = index + 1;
 
        // traverse all ancestors and add 'val'
        while (index < bit.length) {
           // add 'val' to current node of BI Tree
            bit[index] += val;
 
           // update index to that of parent in Update View
           index += index & (-index);
        }
    }
}
