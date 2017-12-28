package com.example.pmerdala.booklisting;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by merdala on 2017-12-28.
 */

public class ComparisonStringIteratorTest {

    @Test
    public void testCompareWithNull(){
        ComparisonComparableIterator<String> comparison = new ComparisonComparableIterator<>();
        ArrayList<String> list1 = new ArrayList<>();
        assertEquals("Expect 0 if 2 arguments is null",0,comparison.compare(null,null));
        assertEquals("Expect -1 if first argument is null and second not",-1,comparison.compare(null,list1));
        assertEquals("Expect 1 if first argument is not null and second is null",1,comparison.compare(list1,null));
        list1.add("Test");
        assertEquals("Expect -1 if first argument is null and second not",-1,comparison.compare(null,list1));
        assertEquals("Expect 1 if first argument is not null and second is null",1,comparison.compare(list1,null));
    }
    @Test
    public void testCompareEmptyList(){
        ComparisonComparableIterator<String> comparison = new ComparisonComparableIterator<>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        assertEquals("Expect 0 if 2 arguments is the same list",0,comparison.compare(list1,list1));
        assertEquals("Expect 0 if 2 arguments empty list",0,comparison.compare(list1,list2));
        list1.add("Test");
        assertEquals("Expect -1 if first argument is list with item and second is empty list",-1,comparison.compare(list1,list2));
        assertEquals("Expect 1 if first argument is empty list and second is list with item",1,comparison.compare(list2,list1));
    }

    @Test
    public void testCompareList(){
        ComparisonComparableIterator<String> comparison = new ComparisonComparableIterator<>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list1.add("Test");
        list2.add("Test");
        assertEquals("Expect 0 if 2 arguments is the same list",0,comparison.compare(list1,list1));
        assertEquals("Expect 0 if 2 arguments list with the same item",0,comparison.compare(list1,list2));
        list1.add("Test1");
        assertEquals("Expect -1 if first argument have 2 item and second one have 1 item",-1,comparison.compare(list1,list2));
        assertEquals("Expect 1 if first argument have 1 item and second one have 2 item",1,comparison.compare(list2,list1));
        list2.add("Test1");
        assertEquals("Expect 0 if 2 arguments is the same list",0,comparison.compare(list1,list1));
        assertEquals("Expect 0 if 2 arguments list with the same item",0,comparison.compare(list1,list2));
        list1.add("Test3");
        list2.add("Test2");
        assertEquals("Expect -1 if first argument have 3 item and second one have 3 item but 3'nd item is diffrent Test3>Test2",1,comparison.compare(list1,list2));
        assertEquals("Expect -1 if first argument have 3 item and second one have 3 item but 3'nd item is diffrent Test2<Test3",-1,comparison.compare(list2,list1));
        list1.add("Test4");
        list2.add("Test4");
        assertEquals("Expect -1 if first argument have 3 item and second one have 3 item but 3'nd item is diffrent Test3>Test2",1,comparison.compare(list1,list2));
        assertEquals("Expect -1 if first argument have 3 item and second one have 3 item but 3'nd item is diffrent Test2<Test3",-1,comparison.compare(list2,list1));
    }
}
