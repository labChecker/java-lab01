package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithoutExams();

    double findAvgRatingOfMathExam();

    List<String> calculateAvgRatingForEachStudent();

    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    Student findStudentWithMaxAvgExamRating();
}
