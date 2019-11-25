package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findStudentsWithoutExams();

    Student findFirstWithoutMath();

    List<Student> findAllWithRatingLessThan300();

    Student findStudentWithMaxAvgExamRating();

    List<Student> findStudentsWithTwoExams();
}
