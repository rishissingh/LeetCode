class Solution {
    public double separateSquares(int[][] squares) {
         double total = 0;
        double maxTop = 0;
        for (int[] sq : squares) {
            long l = sq[2];
            total += (double) l * l;
            maxTop = Math.max(maxTop, sq[1] + l);
        }
        
        double half = total / 2.0;
        double lo = 0;
        double hi = maxTop;
        double eps = 1e-5;
        
        while (hi - lo > eps) {
            double mid = (lo + hi) / 2.0;
            double belowArea = 0;
            // compute area below horizontal line y = mid
            for (int[] sq : squares) {
                double yStart = sq[1];
                double l = sq[2];
                if (yStart < mid) {
                    // part of the square is below mid
                    belowArea += l * Math.min(mid - yStart, l);
                }
            }
            if (belowArea >= half) {
                hi = mid;  // try smaller y
            } else {
                lo = mid;  // need a bigger y
            }
        }
        return hi;
    }
}