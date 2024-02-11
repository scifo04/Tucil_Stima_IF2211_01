import java.util.*;
import java.io.File;
import java.lang.Math;

public class ListListDyn {
    private ListDyn[] buffer;
    private int nEff_list;
    private int size_list;

    public ListListDyn(int size) {
        this.buffer = new ListDyn[size];
        this.nEff_list = 0;
        this.size_list = size;
    }

    public int getnEff_list() {
        return this.nEff_list;
    }

    public int getsize_list() {
        return this.size_list;
    }

    public void setElmt_ListDyn(int index, ListDyn liste) {
        this.buffer[index] = new ListDyn(liste.getnEff());
        // liste.DisplayList_Dyn();
        // System.out.println();
        // for (int i = 0; i < this.buffer[index].getnEff(); i++) {
        //     this.buffer[index].list[i] = liste.list[i];
        // }
        this.buffer[index] = liste;
    }

    public void InsertLast_ListDyn(ListDyn list) {
        if (this.size_list > this.nEff_list) {
            this.setElmt_ListDyn(this.nEff_list, list);
            this.nEff_list += 1;
        } else {
            ListListDyn temp = new ListListDyn(this.buffer.length);
            for (int i = 0; i < this.buffer.length; i++) {
                temp.buffer[i] = this.buffer[i];
            }
            temp.nEff_list = this.nEff_list;
            this.buffer = new ListDyn[temp.nEff_list*2];
            this.nEff_list = temp.nEff_list;
            this.size_list = temp.nEff_list*2;
            for (int i = 0; i < temp.nEff_list; i++) {
                this.buffer[i] = temp.buffer[i];
            }
            this.setElmt_ListDyn(this.nEff_list, list);
            this.nEff_list += 1;
        }
    }

    public Boolean IsIn_ListDyn(ListDyn list) {
        Boolean bool = false;
        for (int i = 0; i < this.nEff_list; i++) {
            if (this.buffer[i] == list) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void DeleteLast_ListDyn() {
        this.nEff_list -= 1;
    }

    public void DisplayList_ListDyn() {
        for (int i = 0; i < this.nEff_list; i++) {
            this.buffer[i].DisplayList_Dyn();
            if (i != this.nEff_list-1) {
                System.out.print(",");
            }
            System.out.println();
        }
    }

    public ListDyn getElmt_ListDyn(int n) {
        return this.buffer[n];
    }
}
