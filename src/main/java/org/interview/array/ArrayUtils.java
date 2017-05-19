package org.interview.array;

public class ArrayUtils {

    public static int[][] rotate2DMatrix(int[][] inputMatrix) {
        int size = inputMatrix.length;
        int layerCount = size/2;

        for(int layer = 0; layer < layerCount; layer++) {
            int first = layer;
            int last = size - first - 1;

            for(int element = first; element < last; element++) {
                int offset = element - first;

                // store off current values
                int top = inputMatrix[first][element];
                int right_side = inputMatrix[element][last];
                int bottom = inputMatrix[last][last-offset];
                int left_side = inputMatrix[last-offset][first];

                inputMatrix[first][element] = left_side;
                inputMatrix[element][last] = top;
                inputMatrix[last][last-offset] = right_side;
                inputMatrix[last-offset][first] = bottom;
            }
        }
        return inputMatrix;
    }
}
