import java.util.*;
import java.io.File;
import java.lang.Math;

public class Matrix {
    private String[][] content;
    private Point[][] coordinate = new Point[1][1];
    private int row;
    private int col;
    private int[] points;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = new String[row][col];
        this.coordinate = new Point[row][col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.coordinate[i][j] = new Point(i+1,j+1);
            }
        }
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

    public void displayMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.content[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void displayCoordinate() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.coordinate[i][j].displayPoint();
            }
            System.out.println();
        }
    }

    public void game_vertical(int col, ListDyn coor_list, ListListDyn token_list) {
        for (int i = 0; i < this.row; i++) {
            if (!coor_list.IsIn_Dyn(this.coordinate[i][col]) && coor_list.getnEff() < coor_list.getsize()) {
                ListDyn new_list = new ListDyn(coor_list.getsize());
                ListDyn new_list_copy = new ListDyn(new_list.getsize());
                new_list.copyList_Dyn(coor_list);
                new_list.InsertLast_Dyn(this.coordinate[i][col]);
                new_list_copy.copyList_Dyn(new_list);
                token_list.InsertLast_ListDyn(new_list_copy);
                game_horizontal(i, new_list, token_list);
                new_list.DeleteLast_Dyn();
            } else {
                try {
                    if (!coor_list.IsIn_Dyn(this.coordinate[row][i]) && coor_list.getnEff() == coor_list.getsize()) {
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Nothing
                }
            }
        }
    }

    public void game_horizontal(int row, ListDyn coor_list, ListListDyn token_list) {
        for (int i = 0; i < this.col; i++) {
            if (!coor_list.IsIn_Dyn(this.coordinate[row][i]) && coor_list.getnEff() < coor_list.getsize()) {
                ListDyn new_list = new ListDyn(coor_list.getsize());
                ListDyn new_list_copy = new ListDyn(new_list.getsize());
                new_list.copyList_Dyn(coor_list);
                new_list.InsertLast_Dyn(this.coordinate[row][i]);
                new_list_copy.copyList_Dyn(new_list);
                token_list.InsertLast_ListDyn(new_list_copy);
                game_vertical(i, new_list, token_list);
                new_list.DeleteLast_Dyn();
            } else {
                try {
                    if (!coor_list.IsIn_Dyn(this.coordinate[row][i]) && coor_list.getnEff() == coor_list.getsize()) {
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Nothing
                }
            }
        }
    }

    public String toString(char[] array) {
        String string = new String(array);
        return string;
    }

    public String generateToken() {
        Random r = new Random();
        char[] token = new char[2];
        String upper = "AZB0CYD9EXF1GWV8UHT2SIR7QJP3OKL6M4N5";
        for (int i = 0; i < token.length; i++) {
            token[i] = upper.charAt(r.nextInt(upper.length()));
        }
        return toString(token);
    }

    public void generateMatrix(int row, int col, String[] strings) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.content[i][j] = strings[(new Random()).nextInt(strings.length)];
            }
        }
    }
}