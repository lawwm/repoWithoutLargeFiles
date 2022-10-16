public class Fenwick {
    long[] bit;
 
    public Fenwick(int arr[]) {
        bit = new long[arr.length + 1]; // index starts from 1 not 0.
 
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
        index = index + 1;
 
        while (index > 0) {
            totalSum += bit[index];
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

    public void update(int index, int val) {
        // index in BIT[] starts from 1
        index = index + 1;
 
        while (index < bit.length) {
            bit[index] += val;
           index += index & (-index);
        }
    }
}