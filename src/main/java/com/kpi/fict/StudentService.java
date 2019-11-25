package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllWithRatingLessThan300();

    List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam();

    List<String> getExamSumAndRatingForEachStudent();

    Student findStudentWithMaxEngRating();

    Student findStudentsWithoutExams();
}
