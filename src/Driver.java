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
        System.out.println("WARNING: File yang diambil adalah dari folder test (test.txt, maka test/test.txt akan diproses)!");
        System.out.print("Masukkan nama file untuk diproses (Format adalah *.txt): ");
        Scanner file_name_inpute = new Scanner(System.in);
        String file_name = file_name_inpute.nextLine();
        Matrix M = new Matrix(1000,1000);
        Matrix N = new Matrix(1000,1000);
        FileProcess.Read(file_name,M,N);
        ListDyn coor_liste = new ListDyn(N.Col());
        ListListDyn token_list = new ListListDyn(5);
        long startTime = System.currentTimeMillis();
        
        try {
            for (int i = 0; i < 1; i++) {
                M.game_horizontal(i, coor_liste, token_list);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("");
            return;
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
                    possible_tokens[i].addprize_String(prize_tokens[j].getprize_String());
                }
                possible_tokens_2[i].copyList_String(possible_tokens[i]);
            }
        }

        long endTime = System.currentTimeMillis();

        ListString max_ListString = new ListString(N.Col());
        int max_idx = 0;
        max_ListString.copyList_String(possible_tokens[0]);
        for (int i = 0; i < possible_tokens.length; i++) {
            if(!max_ListString.compare_ListString(possible_tokens[i])) {
                max_ListString.copyList_String(possible_tokens[i]);
                max_ListString.setprize_String(possible_tokens[i].getprize_String());
                max_idx = i;
            }
        }

        if (possible_tokens[max_idx].getprize_String() == 0) {
            System.out.print("Tidak ada poin paling optimal. Maka hasilnya adalah 0: ");
            System.out.print(possible_tokens[max_idx].getprize_String());
            System.out.println();
        } else {
            System.out.print("Sekuens Optimal: ");
            possible_tokens[max_idx].DisplayList_String();
            System.out.println();
            System.out.print("Poin Optimal: ");
            System.out.print(possible_tokens[max_idx].getprize_String());
            System.out.println();
            System.out.println("Koordinat Optimal: ");
            token_list.getElmt_ListDyn(max_idx).DisplayList_Dyn_Resultado();
            System.out.println();
        }

        long duration = endTime-startTime;
        System.out.print(duration);
        System.out.println(" ms");

        FileProcess.Write(max_ListString, possible_tokens[max_idx].getprize_String(), token_list.getElmt_ListDyn(max_idx), duration);
    }

    public static void second_method() {
        Random r = new Random();

        System.out.print("Masukkan jumlah token unik: ");
        int unique_token = (new Scanner(System.in)).nextInt();
        String[] token = new String[unique_token];

        String[] token_inpute;
        while (true) {
            System.out.print("Masukkan token-token buffer: ");
            token_inpute = (new Scanner(System.in)).nextLine().trim().split(" ");
            int count = 0;
            for (int i = 0; i < token_inpute.length; i++) {
                if (token_inpute[i].length() == 2) {
                    count += 1;
                }
            }
            if (count == token_inpute.length) {
                break;
            } else {
                System.out.println("Jumlah digit setiap token harus 2!");
            }
        }

        for (int i = 0; i < unique_token; i++) {
            token[i] = token_inpute[i];
        }

        System.out.print("Masukkan ukuran buffer: ");
        int buffer_size = (new Scanner(System.in)).nextInt();

        System.out.print("Masukkan ukuran matriks: ");
        String[] matrix_row_col = (new Scanner(System.in)).nextLine().trim().split(" ");
        int matrix_row_size = Integer.parseInt(matrix_row_col[1]);
        int matrix_col_size = Integer.parseInt(matrix_row_col[0]);

        System.out.print("Masukkan jumlah sekuens: ");
        int jumlah_sekuens = (new Scanner(System.in)).nextInt();

        System.out.print("Masukkan ukuran maksimal sekuens: ");
        int ukuran_maksimal_sekuens = (new Scanner(System.in)).nextInt();

        Matrix matrix_token = new Matrix(matrix_row_size, matrix_col_size);
        matrix_token.generateMatrix(matrix_row_size, matrix_col_size, token);
        matrix_token.displayMatrix();
        System.out.println();

        ListString[] prize_token = new ListString[jumlah_sekuens];
        int rand;
        for (int i = 0; i < jumlah_sekuens; i++) {
            rand = r.nextInt(ukuran_maksimal_sekuens-1)+2;
            prize_token[i] = new ListString(rand);
            while (true) {
                for (int j = 0; j < rand; j++) {
                    prize_token[i].InsertLast_String(token[r.nextInt(token.length)]);
                }
                int count = 0;
                for (int k = 0; k < prize_token.length; k++) {
                    if (prize_token[k] != null) {
                        if (prize_token[i].isEqual_String_Beginning(prize_token[i], prize_token[k]) && i != k) {
                            count -= 1;
                        }
                    }
                }
                if (count == 0) {
                    break;
                } else {
                    prize_token[i] = new ListString(rand);
                }
            }
            prize_token[i].setprize_String(r.nextInt(1001));
            int rando = r.nextInt(3);
            if (rando == 2) {
                prize_token[i].setprize_String(-1*prize_token[i].getprize_String());
            }
            prize_token[i].DisplayList_String();
            System.out.println();
            System.out.print(prize_token[i].getprize_String());
            System.out.println();
        }

        ListDyn coor_liste = new ListDyn(buffer_size);
        ListListDyn token_list = new ListListDyn(5);

        matrix_token.setCol(matrix_col_size);
        matrix_token.setRow(matrix_row_size);
        
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            matrix_token.game_horizontal(i, coor_liste, token_list);
        }

        ListString[] possible_tokens = new ListString[token_list.getnEff_list()];
        for (int i = 0; i < possible_tokens.length; i++) {
            possible_tokens[i] = new ListString(token_list.getElmt_ListDyn(i).getnEff());
            for (int j = 0; j < possible_tokens[i].getsize_String(); j++) {
                possible_tokens[i].InsertLast_String(matrix_token.getElmt(token_list.getElmt_ListDyn(i).getElmt_Dyn(j).getX()-1, token_list.getElmt_ListDyn(i).getElmt_Dyn(j).getY()-1));
            }
        }

        ListString[] possible_tokens_2 = new ListString[token_list.getnEff_list()];
        for (int i = 0; i < possible_tokens_2.length; i++) {
            possible_tokens_2[i] = new ListString(token_list.getElmt_ListDyn(i).getnEff());
            possible_tokens_2[i].copyList_String(possible_tokens[i]);
        }

        for (int i = 0; i < possible_tokens.length; i++) {
            for (int j = 0; j < prize_token.length; j++) {
                if (possible_tokens_2[i].isSubset_String(possible_tokens_2[i],prize_token[j])) {
                    possible_tokens[i].addprize_String(prize_token[j].getprize_String());
                }
                possible_tokens_2[i].copyList_String(possible_tokens[i]);
            }
        }

        long endTime = System.currentTimeMillis();

        ListString max_ListString = new ListString(buffer_size);
        int max_idx = 0;
        max_ListString.copyList_String(possible_tokens[0]);
        for (int i = 0; i < possible_tokens.length; i++) {
            if(!max_ListString.compare_ListString(possible_tokens[i])) {
                max_ListString.copyList_String(possible_tokens[i]);
                max_ListString.setprize_String(possible_tokens[i].getprize_String());
                max_idx = i;
            }
        }

        if (possible_tokens[max_idx].getprize_String() == 0) {
            System.out.print("Tidak ada poin paling optimal. Maka hasilnya adalah 0: ");
            System.out.print(possible_tokens[max_idx].getprize_String());
            System.out.println();
        } else {
            System.out.print("Sekuens Optimal: ");
            possible_tokens[max_idx].DisplayList_String();
            System.out.println();
            System.out.print("Poin Optimal: ");
            System.out.print(possible_tokens[max_idx].getprize_String());
            System.out.println();
            System.out.println("Koordinat Optimal: ");
            token_list.getElmt_ListDyn(max_idx).DisplayList_Dyn_Resultado();
            System.out.println();
        }

        long duration = endTime-startTime;
        System.out.print(duration);
        System.out.println(" ms");

        FileProcess.Write(max_ListString, possible_tokens[max_idx].getprize_String(), token_list.getElmt_ListDyn(max_idx), duration);
    }
    
    // Main
    public static void main(String[] args) {
        while (true) {
            System. out. print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=====================================");
            System.out.println("|| Masukkan metode yang diinginkan ||");
            System.out.println("=====================================");
            System.out.println("|| 1. Matrix From File             ||");
            System.out.println("|| 2. Random Generated Matrix      ||");
            System.out.println("|| Other: Exit                     ||");
            System.out.println("=====================================");
            System.out.println();
            System.out.print("Input: ");

            int method;
            try {
                method = (new Scanner(System.in)).nextInt();
            } catch (InputMismatchException e) {
                System. out. print("\033[H\033[2J");
                System.out.flush();
                System.out.println("=====================================");
                System.out.println("||          Terima kasih!!         ||");
                System.out.println("=====================================");
                break;
            }

            if (method == 1) {
                first_method();
            } else if (method == 2) {
                second_method();
            } else {
                System. out. print("\033[H\033[2J");
                System.out.flush();
                System.out.println("=====================================");
                System.out.println("||          Terima kasih!!         ||");
                System.out.println("=====================================");
                break;
            }
        }
    }
}
