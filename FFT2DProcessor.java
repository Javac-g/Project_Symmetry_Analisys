package com.max.hexopt.core.spectral;

import org.jtransforms.fft.DoubleFFT_2D;

public final class FFT2DProcessor {

    private final DoubleFFT_2D fft;

    public FFT2DProcessor(int N) {
        this.fft = new DoubleFFT_2D(N, N);
    }

    public double[][] computeMagnitude(double[][] grid) {

        int N = grid.length;
        double[][] complex = new double[N][2 * N]; // real+imag packed

        // Copy real data
        for (int i = 0; i < N; i++)
            System.arraycopy(grid[i], 0, complex[i], 0, N);

        fft.realForwardFull(complex);

        double[][] magnitude = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double re = complex[i][2 * j];
                double im = complex[i][2 * j + 1];
                magnitude[i][j] = Math.hypot(re, im);
            }
        }

        return magnitude;
    }
}
