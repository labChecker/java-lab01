package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    Student findFirstWithoutMath();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();

    List<String> getExamSumAndRatingForEachStudent();

    List<Student> findStudentsWithTwoExams();

    Student findStudentWithMaxEngRating();
}
