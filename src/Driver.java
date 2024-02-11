import java.util.*;
import java.io.*;
import java.lang.Math;

public class Driver {
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";
    public static final String reset = "\u001B[0m";
    static int token_count = 0;
    static Matrix newMatrix = new Matrix(1,1);

    public static void first_method() {
        System.out.print("Masukkan nama file untuk diproses (Format adalah *.txt): ");
        Scanner file_name_inpute = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
        String file_name = file_name_inpute.nextLine();
        Matrix M = new Matrix(1000,1000);
        Matrix N = new Matrix(1000,1000);
        FileProcess.Read(file_name,M,N);
        ListDyn coor_liste = new ListDyn(N.Col());
        ListListDyn token_list = new ListListDyn(5);

        for (int i = 0; i < 1; i++) {
            M.game_horizontal(i, coor_liste, token_list);
        }

        ListString[] possible_tokens = new ListString[token_list.getnEff_list()];
        for (int i = 0; i < possible_tokens.length; i++) {
            possible_tokens[i] = new ListString(token_list.getElmt_ListDyn(i).getnEff());
            for (int j = 0; j < possible_tokens[i].getsize_String(); j++) {
                possible_tokens[i].InsertLast_String(M.getElmt(token_list.getElmt_ListDyn(i).getElmt_Dyn(j).getX()-1, token_list.getElmt_ListDyn(i).getElmt_Dyn(j).getY()-1));
            }
        }

        ListString[] possible_tokens_2 = new ListString[token_list.getnEff_list()];
        for (int i = 0; i < possible_tokens_2.length; i++) {
            possible_tokens_2[i] = new ListString(token_list.getElmt_ListDyn(i).getnEff());
            possible_tokens_2[i].copyList_String(possible_tokens[i]);
        }

        ListString[] prize_tokens = new ListString[N.Row()];
        for (int i = 0; i < prize_tokens.length; i++) {
            prize_tokens[i] = new ListString(N.Col());
            for (int j = 0; j < N.Col(); j++) {
                if (N.getElmt(i, j) != null) {
                    prize_tokens[i].InsertLast_String(N.getElmt(i, j));
                } else {
                    break;
                }
            }
            prize_tokens[i].setprize_String(N.getPoint(i));
        }

        for (int i = 0; i < possible_tokens.length; i++) {
            for (int j = 0; j < prize_tokens.length; j++) {
                if (possible_tokens_2[i].isSubset_String(possible_tokens_2[i],prize_tokens[j])) {
                    // if (j == 2) {
                    //     possible_tokens_2[i].DisplayList_String();
                    //     System.out.print(" ");
                    //     prize_tokens[j].DisplayList_String();
                    //     System.out.print(" ");
                    // }
                    possible_tokens[i].addprize_String(prize_tokens[j].getprize_String());
                    // if (possible_tokens[i].getprize_String() == 50) {
                    //     System.out.print(possible_tokens[i].getprize_String());
                    //     System.out.print(" ");
                    //     System.out.print(i);
                    //     System.out.println();
                    // }
                }
                possible_tokens_2[i].copyList_String(possible_tokens[i]);
            }
        }

        ListString max_ListString = new ListString(N.Col());
        int max_idx = 0;
        max_ListString.copyList_String(possible_tokens[0]);
        for (int i = 0; i < possible_tokens.length; i++) {
            // possible_tokens[i].DisplayList_String();
            // System.out.print(" ");
            // System.out.print(possible_tokens[i].getprize_String());
            // System.out.print(" ");
            // System.out.println(i);
            if(!max_ListString.compare_ListString(possible_tokens[i])) {
                max_ListString.copyList_String(possible_tokens[i]);
                max_ListString.setprize_String(possible_tokens[i].getprize_String());
                max_idx = i;
            }
        }

        possible_tokens[max_idx].DisplayList_String();
        System.out.println();
        System.out.print(possible_tokens[max_idx].getprize_String());
        System.out.println();
        System.out.println(max_idx);

        long endTime = System.currentTimeMillis();
        long duration = endTime-startTime;
        System.out.print(duration);
        System.out.println(" ms");
    }

    public static void second_method() {
        System.out.print("Masukkan jumlah token unik: ");
        int unique_token = (new Scanner(System.in)).nextInt();
        String[] token = new String[unique_token];

        System.out.print("Masukkan token-token buffer: ");
        String[] token_inpute = (new Scanner(System.in)).nextLine().trim().split(" ");
        for (int i = 0; i < unique_token; i++) {
            token[i] = token_inpute[i];
        }

        System.out.print("Masukkan ukuran buffer: ");
        int buffer_size = (new Scanner(System.in)).nextInt();

        System.out.print("Masukkan ukuran matriks: ");
        String[] matrix_row_col = (new Scanner(System.in)).nextLine().trim().split(" ");
        int matrix_row_size = Integer.parseInt(matrix_row_col[0]);
        int matrix_col_size = Integer.parseInt(matrix_row_col[1]);

        System.out.println("Masukkan jumlah sekuens: ");
        int jumlah_sekuens = (new Scanner(System.in)).nextInt();

        System.out.println("Masukkan ukuran maksimal sekuens: ");
        int ukuran_maksimal_sekuens = (new Scanner(System.in)).nextInt();
    }
    
    // Main
    public static void main(String[] args) {

        System.out.println("Masukkan metode yang diinginkan");
        System.out.println("1. File Processing");
        System.out.println("2. Command Line Interface");
        System.out.print("Input: ");
        int method = (new Scanner(System.in)).nextInt();

        if (method == 1) {
            first_method();
        } else if (method == 2) {
            second_method();
        }
    }
}
