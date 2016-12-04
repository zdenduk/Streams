package com.company;

import com.company.domain.MockDataProvider;
import com.company.domain.School;
import com.company.domain.Student;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by semantier on 11/28/2016.
 */
public class MainTest {

    School school;

    @org.junit.Before
    public void setUp() throws Exception {
        school = MockDataProvider.getNewSchool();
    }

    @org.junit.Test
    public void printNameAndAgeOfEveryStudent() throws Exception {
        Main.printNameAndAgeOfEveryStudent(school.getClasses().get(0).getStudents());
        assertTrue(true);
    }

    @org.junit.Test
    public void getStudentAges() throws Exception {
        Student student = school.getClasses().get(0).getStudents().get(0);
        int actual = Main.getStudentAges(school.getClasses().get(0).getStudents()).get(0);
        assertEquals(student.getAge(), actual);
    }

    @org.junit.Test
    public void getMultiplyStudentAge() throws Exception {
        Student student = school.getClasses().get(0).getStudents().get(0);
        int doubledAge = Main.getMultiplyStudentAge(school.getClasses().get(0).getStudents()).get(0).getAge();
        assertEquals(student.getAge() * 2, doubledAge);
    }

    @org.junit.Test
    public void getStudentsWithMinimumAge() throws Exception {
        int size = Main.getStudentsWithMinimumAge(school.getClasses().get(0).getStudents(), 14).size();
        assertEquals(size, 2);
    }

    @org.junit.Test
    public void getPrimeAges() throws Exception {
        for (int num : Main.getPrimeAges(school.getClasses().get(0).getStudents())) {
            assertTrue(Main.isPrime(num));
        }
    }

    @org.junit.Test
    public void getSortedAges() throws Exception {
        List<Integer> sortedAges = Main.getSortedAges(school.getClasses().get(0).getStudents());
        assertEquals(13, (int) sortedAges.get(0));
        assertEquals(15, (int) sortedAges.get(sortedAges.size() - 1));
    }

    @org.junit.Test
    public void countMaleStudents() throws Exception {
        long studentsCount = Main.countMaleStudents(school.getClasses().get(0).getStudents());
        assertEquals(2, studentsCount);
    }

    @org.junit.Test
    public void avgAgeOfFemaleStudent() throws Exception {
        assertTrue(15==Main.avgAgeOfFemaleStudent(school.getClasses().get(0).getStudents()));
        assertTrue(10.5==Main.avgAgeOfFemaleStudent(school.getClasses().get(1).getStudents()));
    }

    @org.junit.Test
    public void productOfStudentGrades() throws Exception {
        System.out.println(Main.productOfStudentGrades(school.getClasses().get(0).getStudents().get(0)));
        assertTrue(0==Main.productOfStudentGrades(school.getClasses().get(0).getStudents().get(0)));
    }

    @org.junit.Test
    public void getBestMathGradeFromStudent() throws Exception {
        assertTrue(Main.getBestMathGradeFromStudent(school.getClasses().get(0).getStudents().get(0)).get()
                == Optional.of(school.getClasses().get(0).getStudents().get(0).getGrades().get(0)).get());
    }

    @org.junit.Test
    public void atLeastOneGradeA() throws Exception {
        assertTrue(Main.atLeastOneGradeA(school.getClasses().get(0).getStudents().get(0)));
        assertFalse(Main.atLeastOneGradeA(school.getClasses().get(1).getStudents().get(0)));
    }

    @org.junit.Test
    public void getFirstPrimes() throws Exception {
        Main.getFirstPrimes(10).forEach(Integer -> assertTrue(Main.isPrime(Integer.byteValue())));
    }

}