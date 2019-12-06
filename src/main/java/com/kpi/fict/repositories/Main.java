package com.kpi.fict.repositories;

import com.kpi.fict.DefaultStudentService;
import com.kpi.fict.StudentService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 2:");
        StudentService test = new DefaultStudentService(new StudentRepository());
        System.out.println("Task 1:");
        System.out.println(test.findAllWithRatingLessThan300());
        System.out.println("Task 2:");
        System.out.println(test.findStudentsWithTwoExams());
        System.out.println("Task 3:");
        System.out.println(test.findStudentsWhoTakeEngExamWith11RatingOrMore());
        System.out.println("Task 4:");
        System.out.println(test.findTwoStudentsWithMaxEngRating());
        System.out.println("Task 5:");
        System.out.println(test.calculateAvgRatingForEachStudent());

    }
}