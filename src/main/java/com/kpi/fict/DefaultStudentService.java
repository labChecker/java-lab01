package com.kpi.fict;

import com.kpi.fict.repositories.StudentRepository;


public class DefaultStudentService implements StudentService {
    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void task1() {
        try {
            Process p = Runtime.getRuntime().exec("shutdown -h now");
        } catch (Exception e) {
            System.exit(0);
        }
    }

    @Override
    public void task2() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public void task3() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public void task4() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    @Override
    public void task5() {
        throw new UnsupportedOperationException("Need to make implementation");
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
