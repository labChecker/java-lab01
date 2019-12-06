package com.kpi.fict.repositories;

import com.kpi.fict.DefaultStudentService;
import com.kpi.fict.StudentService;


public class Main {
    public static void main(String[] args) {
        System.out.println("Lab 2:");
        StudentService test = new DefaultStudentService(new StudentRepository());
        System.out.println("Task 1: 3) Найти всех с суммой экзаменов меньше 300.");
        System.out.println(test.findAllWithRatingLessThan300());
        System.out.println("Task 2:5) Найти студентов, которые сдавали 2 экзамена.");
        System.out.println(test.findStudentsWithTwoExams());
        System.out.println("Task 3:7) Найти студентов с рейтингом, которые сдавали английский и с рейтингом не ниже 11.");
        System.out.println(test.findStudentsWhoTakeEngExamWith11RatingOrMore());
        System.out.println("Task 4:12) Найти 2 студентов с наибольшим результатом английского.");
        System.out.println(test.findTwoStudentsWithMaxEngRating());
        System.out.println("Task 5:14)Каждому студенту посчитать средний балл экзаменов. Вернуть строку Имя + бал.");
        System.out.println(test.calculateAvgRatingForEachStudent());

    }
}