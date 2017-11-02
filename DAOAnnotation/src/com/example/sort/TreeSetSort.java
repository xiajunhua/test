package com.example.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TreeSetSort {

	public static void main(String[] args) {
		Person person1= new Person(1,23,1,"tom");
		Person person2= new Person(2,24,1,"basky");
		Person person3= new Person(3,25,1,"abc");
		Person person4= new Person(4,24,1,"dfdfdf");
		Person person5= new Person(5,24,1,"fffff");
		Person person6= new Person(6,24,1,"ggggg");
		Person person7= new Person(7,25,1,"def");

		List<Person> list=new ArrayList<>();
		list.add(person1);
		list.add(person2);
		list.add(person3);
		list.add(person4);
		list.add(person5);
		list.add(person6);
		list.add(person7);
		TreeSet<Person> pt = new TreeSet<>(new Comparator<Person>(){
			@Override
			public int compare(Person p1,Person p2) {
				if(p1.getAge()>p2.getAge())
				return 1;
				else if(p1.getAge()==p2.getAge()){
					if(p1.getId()>p2.getId()){
						return 1;
					}else if(p1.getId()==p2.getId())
						return 0;
					else return 1;
				}
				else return 1;
			}
		});
		for (Person person : list) {
			System.out.print(person.getId()+"---");
			System.out.print(person.getAge()+"---");
			System.out.println(person.getName());
			pt.add(person);
		}
		for (Person person : pt) {
			System.out.print(person.getId()+"---");
			System.out.print(person.getAge()+"---");
			System.out.println(person.getName());
		}
	}
	
}
