import java.util.*;
import java.io.File;
import java.lang.Math;

public class Matrix {
    private String[][] content;
    private int row;
    private int col;
    private int[] points;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = new String[row][col];
    }

    public int Row() {
        return this.row;
    }

    public int Col() {
        return this.col;
    }

    public void setRow(int newRow) {
        this.row = newRow;
    }

    public void setCol(int newCol) {
        this.col = newCol;
    }

    public String getElmt(int row, int col) {
        return this.content[row][col];
    }

    public void setElmt(int row, int col, String val) {
        this.content[row][col] = val;
    }

    public int getPoint(int row) {
        return this.points[row];
    }

    public void setPoint(int row, int rep) {
        this.points[row] = rep;
    }

    public void setPointSize(int size) {
        this.points = new int[size];
    }

    public void fillEmpty() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.content[i][j] = "--";
            }
        }
    }
}