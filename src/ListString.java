import java.util.*;
import java.io.File;
import java.lang.Math;

public class ListString {
    private String[] string_content;
    private int nEff_string;
    private int size_string;
    private int prize_string;

    public ListString(int size) {
        this.string_content = new String[size];
        this.nEff_string = 0;
        this.size_string = size;
        this.prize_string = 0;
    }

    public int getnEff_String() {
        return this.nEff_string;
    }

    public int getsize_String() {
        return this.size_string;
    }

    public int getprize_String() {
        return this.prize_string;
    }

    public void setprize_String(int val) {
        this.prize_string = val;        
    }

    public void addprize_String(int val) {
        this.prize_string += val;
    }

    public String getElmt_String(int index) {
        return this.string_content[index];
    }

    public void setElmt_String (int index, String string) {
        this.string_content[index] = string;
    }

    public void InsertLast_String (String string) {
        if (this.nEff_string < this.size_string) {
            this.setElmt_String(this.nEff_string, string);
            this.nEff_string += 1;
        } else {
            ListString temp = new ListString(this.size_string);
            for (int i = 0; i < this.nEff_string; i++) {
                temp.string_content[i] = this.string_content[i];
            }
            temp.nEff_string = this.nEff_string;
            this.string_content = new String[temp.size_string*2];
            this.nEff_string = temp.nEff_string;
            this.size_string = temp.size_string*2;
            for (int i = 0; i < temp.nEff_string; i++) {
                this.string_content[i] = temp.string_content[i];
            }
            this.string_content[temp.nEff_string] = string;
            this.nEff_string += 1;
        }
    }

    public Boolean IsIn_String(String string) {
        Boolean bool = false;
        for (int i = 0; i < this.nEff_string; i++) {
            if (this.string_content[i] == string) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public void DeleteLast_String() {
        this.nEff_string -= 1;
    }

    public void DisplayList_String() {
        for (int i = 0; i < this.nEff_string; i++) {
            System.out.print(this.string_content[i]);
            System.out.print(" ");
        }
    }

    public void copyList_String(ListString list) {
        this.nEff_string = list.nEff_string;
        this.size_string = list.size_string;
        for (int i = 0; i < this.nEff_string; i++) {
            this.string_content[i] = list.string_content[i];
        }
    }

    public ListString slice_ListString (ListString list) {
        for (int i = 0; i < list.getnEff_String()-1; i++) {
            list.setElmt_String(i, list.getElmt_String(i+1));
        }
        list.DeleteLast_String();
        return list;
    }

    public Boolean isEqual_String_Beginning(ListString list1, ListString list2) {
        Boolean bool = true;
        if (list1.nEff_string >= list2.nEff_string) {
            for (int i = 0; i < list2.nEff_string; i++) {
                if (list1.getElmt_String(i).equals(list2.getElmt_String(i)) == false) {
                    bool = false;
                    break;
                }
            }
        } else {
            bool = false;
        }
        return bool;
    }

    public Boolean isSubset_String(ListString list1, ListString list2) {
        if (list1.getnEff_String() == 0) {
            return false;
        } else {
            if (isEqual_String_Beginning(list1, list2)) {
                return true;
            } else {
                return isSubset_String(slice_ListString(list1), list2);
            }
        }
    }

    public Boolean compare_ListString(ListString list2) {
        // System.out.print(this.prize_string);
        // System.out.print(" ");
        // System.out.print(list2.prize_string);
        // System.out.print(" ");
        if (this.prize_string > list2.prize_string) {
            // System.out.print("TRUE LENGTH");
            return true;
        } else if (this.prize_string == list2.prize_string) {
            if (this.nEff_string <= list2.nEff_string) {
                // System.out.print("TRUE LENGTH");
                return true;
            } else {
                // System.out.print("FALSE LENGTH");
                return false;
            }
        } else {
            // System.out.print("FALSE VALUE");
            return false;
        }
    }
}
