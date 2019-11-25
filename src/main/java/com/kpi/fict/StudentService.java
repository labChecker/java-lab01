package com.kpi.fict;

import com.kpi.fict.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAllWithRatingLessThan300();

    List<Student> findStudentsWhoTakeMathAndOneMoreExam();

    List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam();

    List<Student> findStudentsWithoutExams();

    List<String> calculateAvgRatingForEachStudent();
}
