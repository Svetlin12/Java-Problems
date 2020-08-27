import java.lang.*;
import java.util.*;

class MedianFinder {

    // left will store numbers below the median, right - numbers above the median (left is max heap, right is min heap)
    private PriorityQueue<Integer> left, right;
    double median;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
        median = 0;
    }

    public void addNum(int num) {
        if (left.size() > right.size()) {
            if (num < median) {
                right.add(left.remove());
                left.add(num);
            }
            else
                right.add(num);
            median = (left.peek() + right.peek()) / 2.0;
        }
        else if (left.size() == right.size()) {
            if (num < median) {
                left.add(num);
                median = left.peek();
            }
            else {
                right.add(num);
                median = right.peek();
            }
        }
        else {
            if (num > median) {
                left.add(right.remove());
                right.add(num);
            }
            else
                left.add(num);
            median = (left.peek() + right.peek()) / 2.0;
        }
    }

    public double findMedian() {
        return median;
    }
}

class Solution {
    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
