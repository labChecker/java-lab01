package com.kpi.fict;

import com.kpi.fict.entities.Exam;
import com.kpi.fict.entities.Student;
import com.kpi.fict.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findStudentWithMaxAvgExamRating() {
        return studentRepository
                .findAll()
                .stream()
                .min((a, b) -> {
                    double r1 = a.getRating();
                    double r2 = b.getRating();
                    return Double.compare(r1, r2);
                })
                .get();
    }

    @Override
    public List<Student> findStudentsWithMathRatingMoreThanAvgAndTakeEngExam() {
        double avg = studentRepository
                .findAll()
                .stream()
                .map(Student::getRating)
                .reduce(Double::sum)
                .get() / studentRepository.findAll().size();
        return studentRepository
                .findAll()
                .stream()
                .filter(s ->
                        s.getRating() > avg &&
                        s.getExams().stream().anyMatch(e -> e.getType() == Exam.Type.ENGLISH))
                .collect(Collectors.toList());
    }

    //Delimiter: ','
    @Override
    public List<String> getExamSumAndRatingForEachStudent() {
        return studentRepository
                .findAll()
                .stream()
                .map(s -> {
                    double sum = s
                            .getExams()
                            .stream()
                            .map(e -> e.getScore())
                            .reduce(Double::sum)
                            .get();
                    return sum + "," + s.getRating() + "," + s.getName();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findStudentsWhoTakeMathEngExamWith180RatingOrMore() {
        return studentRepository
                .findAll()
                .stream()
                .filter(s -> s
                        .getExams()
                        .stream()
                        .filter(e -> e.getType() == Exam.Type.MATH || e.getType() == Exam.Type.ENGLISH)
                        .allMatch(e -> e.getScore() > 180)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findTwoStudentsWithMaxEngRating() {
        return studentRepository
                .findAll()
                .stream()
                .sorted((a, b) -> {
                    double aa = a
                            .getExams()
                            .stream()
                            .filter(e -> e.getType() == Exam.Type.ENGLISH)
                            .findFirst()
                            .get()
                            .getScore();
                    double bb = b
                            .getExams()
                            .stream()
                            .filter(e -> e.getType() == Exam.Type.ENGLISH)
                            .findFirst()
                            .get()
                            .getScore();
                    return Double.compare(aa, bb);
                })
                .collect(Collectors.toList());
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
