import java.util.*;
import java.io.File;
import java.lang.Math;

public class ListDyn {
    public Point[] list;
    public int nEff;
    public int size;

    public ListDyn(int size) {
        this.list = new Point[size];
        this.nEff = 0;
        this.size = size;
    }

    public int getnEff() {
        return this.nEff;
    }

    public int getsize() {
        return this.size;
    }

    public Point getElmt_Dyn (int index) {
        return this.list[index];
    }

    public void setElmt_Dyn (int index, Point point) {
        this.list[index] = point;
    }

    public void InsertLast_Dyn(Point point) {
        if (this.size > this.nEff) {
            this.list[this.nEff] = point;
            this.nEff += 1;
        } else {
            ListDyn temp = new ListDyn(this.list.length);
            for (int i = 0; i < this.list.length; i++) {
                temp.list[i] = this.list[i];
            }
            temp.nEff = this.nEff;
            this.list = new Point[temp.nEff*2];
            this.nEff = temp.nEff;
            this.size = temp.nEff*2;
            for (int i = 0; i < temp.nEff; i++) {
                this.list[i] = temp.list[i];
            }
            this.list[temp.nEff] = point;
            this.nEff += 1;
        }
    }

    public Boolean IsIn_Dyn(Point point) {
        Boolean bool = false;
        for (int i = 0; i < this.nEff; i++) {
            if (this.list[i] == point) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void DeleteLast_Dyn() {
        this.nEff -= 1;
    }

    public void DisplayList_Dyn() {
        for (int i = 0; i < this.nEff; i++) {
            System.out.print("(");
            System.out.print(this.list[i].getX());
            System.out.print(",");
            System.out.print(this.list[i].getY());
            System.out.print(")");
            if (i != this.nEff-1) {
                System.out.print(",");
            }
        }
    }

    public void copyList_Dyn(ListDyn list) {
        this.nEff = list.nEff;
        this.size = list.size;
        for (int i = 0; i < this.nEff; i++) {
            this.list[i] = list.list[i];
        }
    }
}
