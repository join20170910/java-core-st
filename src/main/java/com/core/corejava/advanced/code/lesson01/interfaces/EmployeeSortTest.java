package com.core.corejava.advanced.code.lesson01.interfaces;

import java.util.Arrays;
import java.util.Comparator;

public class EmployeeSortTest {
	public static void main(String[] args) {
		com.core.corejava.Employee[] staff = new com.core.corejava.Employee[3];

		staff[0] = new com.core.corejava.Employee("Harry Hacker", 75000);
		staff[1] = new Employee("Carl Cracker", 75000);
		staff[2] = new Employee("Tony Tester", 38000);
		
		Arrays.sort(staff);

		System.out.println(Arrays.toString(staff));
	}
}