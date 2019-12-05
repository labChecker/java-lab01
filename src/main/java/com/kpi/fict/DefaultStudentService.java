package com.kpi.fict;

import com.kpi.fict.entities.Student;
import com.kpi.fict.entities.Exam;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findStudentsWithTwoExams() {
        return studentRepository.findAll().stream().filter(student -> student.getExams().size() == 2).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeMathAndOneMoreExam() {
        return studentRepository.findAll().stream().filter(student -> 
            student
                .getExams()
                .stream()
                .anyMatch(exam -> exam.getType() == Exam.Type.MATH)
            && 
            student
                .getExams()
                .size() == 2
        ).collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWithRatingMoreThanAvgAndTakeMathExam() {

        Double avg = studentRepository.findAll().stream().mapToDouble(Student::getRating).average().orElseThrow(NoSuchFieldError::new);
        
        return studentRepository.findAll().stream().filter(student -> 
            student
                .getExams()
                .stream()
                .anyMatch(exam -> exam.getType() == Exam.Type.MATH)
            && 
            student
                .getRating()
                > avg
        ).collect(Collectors.toList());
    }

    @Override
    public List<String> getExamSumAndRatingForEachStudent() {
        return studentRepository.findAll().stream().map(student -> 
            String.join(
                ",",
                student.getExams().stream().map(exam -> exam.getScore()).reduce(0.0, (a, v) -> a + v).toString(),
                Double.toString(student.getRating()),
                student.getName()
            )
        ).collect(Collectors.toList());
    }

    @Override
    public Student findFirstWithoutMath() {
        return studentRepository.findAll().stream().filter(student -> 
            student
                .getExams()
                .stream()
                .filter(exam -> exam.getType() == Exam.Type.MATH)
                .findAny()
                .isEmpty()
        ).findFirst().orElseThrow(NoSuchFieldError::new);
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
