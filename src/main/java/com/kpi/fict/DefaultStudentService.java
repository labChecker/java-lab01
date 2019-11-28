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
    public List<Student> findStudentsWithoutExams() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public double findAvgRatingOfMathExam() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    //Delimiter: ','
    @Override
    public List<String> calculateAvgRatingForEachStudent() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public List<Student> findStudentsWhoTakeMathAndOneMoreExam() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public Student findStudentWithMaxAvgExamRating() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
