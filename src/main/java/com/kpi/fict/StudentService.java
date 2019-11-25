package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();

    List<Student> findTwoStudentsWithMaxEngRating();

    Student findStudentWithMaxEngRating();

    List<String> calculateAvgRatingForEachStudent();

    List<String> getExamSumAndRatingForEachStudent();
}
