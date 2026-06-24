class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) {
            return m;
        }

        int size = 2 * m;
        long[][] T = new long[size][size];

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < y; x++) {
                T[y][m + x] = 1;
            }
            for (int x = y + 1; x < m; x++) {
                T[m + y][x] = 1;
            }
        }

        long[][] Tn = matrixPower(T, n - 1);

        long total = 0;
        for (int i = 0; i < size; i++) {
            long rowSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum = (rowSum + Tn[i][j]) % MOD;
            }
            total = (total + rowSum) % MOD;
        }

        return (int) total;
    }

    private long[][] matrixPower(long[][] base, int exp) {
        int size = base.length;
        long[][] result = new long[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        long[][] current = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, current);
            }
            current = multiply(current, current);
            exp >>= 1;
        }
        return result;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int size = A.length;
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}