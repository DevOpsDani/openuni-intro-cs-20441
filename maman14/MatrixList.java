/**
 * Matala 14 - Class to create a Date object
 * @author Daniel BenShushan
 */
public class MatrixList
{
    IntNodeMat _m00;
    

    public MatrixList()
    {
        _m00 = null;
    }

    /**
     * Constructor that creates a MatrixList from a 2D array.
     * The matrix is stored as a linked list where each element is a node 
     * with horizontal and vertical links to its neighbors.
     * 
     * @param mat 2D array representing the matrix data.
     *            If the array is null or empty, the matrix is initialized to null.
     */
    public MatrixList(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            _m00 = null;
            return;
        }
    
        int rows = mat.length;
        int cols = mat[0].length;
    
        // Array to store the first node of each row
        IntNodeMat[] rowHeads = new IntNodeMat[rows];
    
        // Step 1: Create nodes for each element in the matrix and link them horizontally (left-right)
        for (int i = 0; i < rows; i++) {
            IntNodeMat prev = null;
            for (int j = 0; j < cols; j++) {
                IntNodeMat newNode = new IntNodeMat(mat[i][j]);
    
                if (j == 0) {
                    // First node of the row
                    rowHeads[i] = newNode;
                } else {
                    // Link the previous node in the row to the current node (left-right link)
                    prev.setNextCol(newNode);
                    newNode.setPrevCol(prev);
                }
    
                // Set previous node for the next iteration
                prev = newNode;
            }
        }
    
        // Step 2: Link the nodes vertically (up-down)
        for (int i = 0; i < rows - 1; i++) {
            IntNodeMat upper = rowHeads[i];
            IntNodeMat lower = rowHeads[i + 1];
    
            while (upper != null && lower != null) {
                // Link the upper node to the lower node in the same column (up-down link)
                upper.setNextRow(lower);
                lower.setPrevRow(upper);
    
                // Move to the next column
                upper = upper.getNextCol();
                lower = lower.getNextCol();
            }
        }
    
        // Step 3: Set the first node (_m00) as the top-left node of the matrix
        _m00 = rowHeads[0];
    }

    /**
     * Gets the data at the specified row and column in the matrix.
     * 
     * @param i the row index (1-based).
     * @param j the column index (1-based).
     * @return the value at position (i, j), or Integer.MIN_VALUE if the indices are out of bounds.
     */
    public int getData_i_j (int i, int j) {

        if (_m00 == null) {
            return Integer.MIN_VALUE;
        }

        IntNodeMat pointer = _m00;
        int rows = 1;
        int column = 1;

        while (rows < i) {
            if (pointer.getNextRow() != null) {
                pointer = pointer.getNextRow();
                rows++;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        while (column < j) {
            if (pointer.getNextCol() != null) {
                pointer = pointer.getNextCol();
                column++;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        
        return pointer.getData();
    }


    /**
     * Sets the data at the specified row and column in the matrix.
     * 
     * @param data the value to set at position (i, j).
     * @param i the row index (1-based).
     * @param j the column index (1-based).
     */
    public void setData_i_j (int data, int i, int j)
    {
        if (_m00 == null) {
            return;
        }

        IntNodeMat pointer = _m00;
        int rows = 1;
        int column = 1;

        while (rows < i) {
            if (pointer.getNextRow() != null) {
                pointer = pointer.getNextRow();
                rows++;
            } else {
                return;
            }
        }

        while (column < j) {
            if (pointer.getNextCol() != null) {
                pointer = pointer.getNextCol();
                column++;
            } else {
                return;
            }
        }
        pointer.setData(data);

    }


    /**
     * Returns a string representation of the matrix where each row is printed
     * on a new line and each element is separated by a tab.
     * 
     * @return a string representation of the matrix.
     */
    public String toString() {
        if (_m00 == null) {
            return "";
        }

        String matrixString = "";
        IntNodeMat currentRow = _m00;

        while (currentRow != null) {
            IntNodeMat currentNode = currentRow;
            // Print all columns in the current row
            while (currentNode != null) {
                matrixString += currentNode.getData() + "\t"; // Concatenate the data with a tab space
                currentNode = currentNode.getNextCol();
            }
            // Move to the next row
            matrixString += "\n";
            currentRow = currentRow.getNextRow();
        }

        return matrixString;
    }

    /**
     * Finds and returns the maximum value in the matrix.
     * Uses a recursive approach to traverse all nodes and find the maximum value.
     * 
     * @return the maximum value in the matrix, or Integer.MIN_VALUE if the matrix is empty.
     */

    public int findMax() {
        // Start the recursive process from the first node in the matrix (top-left corner)
        if (_m00 == null) {
            return Integer.MIN_VALUE; 
        }
        return findMax(_m00, Integer.MIN_VALUE); 
    }

    /**
     * Recursive helper method to find the maximum value in the matrix.
     * 
     * @param currentNode the current node in the traversal.
     * @param currentMax the current maximum value.
     * @return the maximum value found in the matrix.
     */
    private int findMax(IntNodeMat currentNode, int currentMax) {
        if (currentNode == null) {
            return currentMax; // Return the current maximum if we reach the end of the row or column
        }
        
        // Compare the current node's value with the current maximum
        currentMax = Math.max(currentMax, currentNode.getData());
        
        // First, move to the next column in the same row
        int maxInRow = findMax(currentNode.getNextCol(), currentMax);
        
        // Then, move to the next row (same column)
        return findMax(currentNode.getNextRow(), maxInRow);
    }

    
    /**
     * Counts how many times a specific value 'x' appears in the matrix.
     * The matrix is traversed row by row, and within each row, the values are checked.
     * 
     * @param x the value to count in the matrix.
     * @return the count of occurrences of 'x' in the matrix.
     */
    public int howManyX(int x) {
        if (_m00 == null) {
            return 0; // Return 0 if the matrix is empty
        }
    
        // Initialize count
        int count = 0;
        IntNodeMat currentNode = _m00;  // Start at the top-left corner of the matrix
    
        // Traverse the matrix starting from the top-left corner
        while (currentNode != null) {
            IntNodeMat rowNode = currentNode;  // Start from the first element of each row
            // Traverse across the current row (left to right)
            while (rowNode != null) {
                if (rowNode.getData() == x) {
                    count++;  // Increment the count if the value matches x
                    break;    // No need to continue in the current row after finding x
                } else if (rowNode.getData() > x) {
                    break;    // No need to check further in this row (values are sorted)
                }
                rowNode = rowNode.getNextCol(); // Move to the next column
            }
    
            // Move to the next row (down)
            currentNode = currentNode.getNextRow();
        }
    
        return count;
    }
    

}
