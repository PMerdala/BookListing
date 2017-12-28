package com.example.pmerdala.booklisting;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by merdala on 2017-12-28.
 */

public class ComparisonComparableIterator<T extends Comparable<T>> implements Comparator<Iterable<T>> {

    @Override
    public int compare(Iterable<T> o1, Iterable<T> o2) {
        if (o1 == o2){
            return 0;
        }
        if (o1==null){
            if (o2==null){
                return 0;
            }else{
                return -1;
            }
        }else{
            if (o2==null){
                return 1;
            }
        }
        Iterator<T> strings2 = o2.iterator();
        for(Comparable<T> str1: o1){
            if (!strings2.hasNext()){
                return -1;
            }
            T str2 = strings2.next();
            int result = str1.compareTo(str2);
            if (result!=0){
                return result;
            }
        }
        if (strings2.hasNext()){
            return 1;
        }
        return 0;
    }

}
