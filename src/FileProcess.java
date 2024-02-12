import java.io.*;
import java.util.*;

public class FileProcess {
    static Scanner scInt = new Scanner(System.in);
    static Scanner scFlt = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);

    public static void Read(String path, Matrix M, Matrix N){
            try{
                String test = "../test/";
                File f = new File(test.concat(path));
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
                    M.setCol(matrix_col);
                    M.setRow(matrix_row);
                }

                for (int i = 0; i < M.Row(); i++) {
                    if (reader.hasNextLine()) {
                        String[] alpha_numeric_string = reader.nextLine().trim().split(" ");
                        for(int j = 0; j < M.Col(); j++) {
                            M.setElmt(i,j,alpha_numeric_string[j]);
                        }
                        for (int k = 0; k < M.Row(); k++) {
                            for (int l = 0; l < M.Col(); l++) {
                                if (M.getElmt(k, l) != null) {
                                    if (M.getElmt(k, l).length() != 2) {
                                        System.out.println("Terdapat elemen berdigit tidak sama dengan 2! Menutup program...");
                                        System.exit(0);
                                    }
                                }
                            }
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
                        for (int k = 0; k < N.Row(); k++) {
                            for (int l = 0; l < N.Col(); l++) {
                                if (N.getElmt(k, l) != null) {
                                    if (N.getElmt(k, l).length() != 2) {
                                        System.out.println("Terdapat elemen berdigit tidak sama dengan 2! Menutup program...");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nama file tidak ditemukan!");
            }
        }
        
    public static void Write(ListString liststring, int prize, ListDyn listcoor) {
        System.out.println();
        System.out.println("====================================");
        System.out.println("||    Simpan output dalam file?   ||");
        System.out.println("||--------------------------------||");
        System.out.println("|| (Y/y) : YES                    ||");
        System.out.println("|| Other Response : NO            ||");
        System.out.println("====================================");
        System.out.println();
        try {
            char input = (new Scanner(System.in)).next().charAt(0);
            if (input == 'Y' || input == 'y') {
                System.out.print("Masukkan sebuah path: ");
                String path = scStr.nextLine();
                try {
                    String test = "../test/";
                    FileWriter writer = new FileWriter(test.concat(path));
                    for (int i = 0; i < liststring.getnEff_String(); i++) {
                        writer.write(liststring.getElmt_String(i));
                        if (i != liststring.getnEff_String()-1) {
                            writer.write(" ");
                        }
                    }
                    writer.write("\n");
                    writer.write(Integer.toString(prize));
                    writer.write("\n");
                    for (int i = 0; i < listcoor.getnEff(); i++) {
                        writer.write(Integer.toString(listcoor.getElmt_Dyn(i).getY()));
                        writer.write(",");
                        writer.write(Integer.toString(listcoor.getElmt_Dyn(i).getX()));
                        if (i != liststring.getnEff_String()-1) {
                            writer.write("\n");
                        }
                    }
                    writer.close();
                    System.out.println("Berhasil menulis ke file " + path);
                } catch (IOException e) {
                    System.out.println("Nama file tidak ditemukan!");
                }
            }
        } catch (InputMismatchException e) {
            char input = (new Scanner(System.in)).next().charAt(0);
        }



    }
}

