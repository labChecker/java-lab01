package com.kpi.fict;

import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<Student> findStudentsWhoTakeEngExamWith11RatingOrMore() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<Student> findTwoStudentsWithMaxEngRating() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<String> calculateAvgRatingForEachStudent() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<Student> findAllWithRatingLessThan300() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
