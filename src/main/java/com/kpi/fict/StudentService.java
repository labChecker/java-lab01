package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findFirstWithoutMath();

    double findAvgRatingOfMathExam();

    List<String> calculateAvgRatingForEachStudent();

    List<Student> findStudentsWithTwoExams();

    List<Student> findTwoStudentsWithMaxEngRating();
}
