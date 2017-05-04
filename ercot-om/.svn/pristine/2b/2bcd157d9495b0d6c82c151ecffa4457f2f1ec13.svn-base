package com.softwarelikeyou.pojo;

import java.util.Comparator;

public class ERCOTFileDateComparator implements Comparator<ERCOTFile> {
    public int compare(ERCOTFile o1, ERCOTFile o2) {
        if (o1.getDate().before(o2.getDate()))
            return -1; 
        else if (o1.getDate().after(o2.getDate()))
            return 1;
        else
            return 0;        
    }
}