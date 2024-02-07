import java.io.*;
import java.util.*;

public class FileProcess {
    static Scanner scInt = new Scanner(System.in);
    static Scanner scFlt = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);

    public static void Read(String path, Matrix M, Matrix N){
        int m,n;
            try{
                File f = new File(path);
                Scanner reader = new Scanner(f);
                if (reader.hasNextLine()) {
                    String buffer_size_string = reader.nextLine();
                    int buffer_size = Integer.parseInt(buffer_size_string);
                    N.setCol(buffer_size);
                }
                if (reader.hasNextLine()) {
                    String[] matrix_size_string = reader.nextLine().trim().split(" ");
                    int matrix_row = Integer.parseInt(matrix_size_string[0]);
                    int matrix_col = Integer.parseInt(matrix_size_string[1]);
                    M.setRow(matrix_row);
                    M.setCol(matrix_col);
                }
                for (int i = 0; i < M.Row(); i++) {
                    if (reader.hasNextLine()) {
                        String[] alpha_numeric_string = reader.nextLine().trim().split(" ");
                        for(int j = 0; j < M.Col(); j++) {
                            M.setElmt(i,j,alpha_numeric_string[j]);
                        }
                    }
                }
                if (reader.hasNextLine()) {
                    String point_size_string = reader.nextLine();
                    int point_size = Integer.parseInt(point_size_string);
                    N.setRow(point_size);
                    N.setPointSize(point_size);
                }
                for (int i = 0; i < N.Row(); i++) {
                    if (reader.hasNextLine()) {
                        String[] buffer_token_string = reader.nextLine().trim().split(" ");
                        for (int j = 0; j < buffer_token_string.length; j++) {
                            N.setElmt(i,j,buffer_token_string[j]);
                        }
                        if (reader.hasNextLine()) {
                            N.setPoint(i, Integer.parseInt(reader.nextLine()));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nama file tidak ditemukan!");
            }
        }
        
    public static void Write(String output) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("||    Simpan output dalam file?   ||");
        System.out.println("||--------------------------------||");
        System.out.println("|| 1. Iya                         ||");
        System.out.println("|| Anything Else : Tidak          ||");
        System.out.println("====================================");
        System.out.println();
        try {
            int input = scInt.nextInt();
            if (input == 1) {
                System.out.print("Masukkan sebuah path: ");
                String path = scStr.nextLine();
                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(output);
                    writer.close();
                    System.out.println("Berhasil menulis ke file " + path);
                } catch (IOException e) {
                    System.out.println("Nama file tidak ditemukan!");
                }
            }
        } catch (InputMismatchException e) {
            scInt.next();
            scInt.nextLine();
        }



    }
}

