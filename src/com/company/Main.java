package com.company;


import com.company.domain.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    // refactoring java 7 => 8

    public static void printNameAndAgeOfEveryStudent(List<Student> students) {
        /*for (Student student : students) {
            System.out.println(student.getAge() + student.getName());
        }*/
        students.forEach(student -> System.out.println(student.getName() + student.getAge()));
    }


    // map - method reference
    public static List<Integer> getStudentAges(List<Student> students) {
        /*List<Integer> ages = new ArrayList<Integer>();
        for (Student student : students) {
            ages.add(student.getAge());
        }
        return ages;*/
        List<Integer> newStudents = students.stream()
                .map(student -> student.getAge())
                .collect(Collectors.toList());
        return newStudents;
    }

    // map
    public static List<Student> getMultiplyStudentAge(List<Student> students) {
        /*List<Student> doubledAges = new ArrayList<Student>();
        for (Student student : students) {
            Student studentTmp = new Student(student.getName(), student.getAge() * 2, student.getGender(), student.getGrades(), student.getFavouriteSubject());
            doubledAges.add(studentTmp);
        }
        return doubledAges;*/
        List<Student> newStudents = students.stream()
                .map(student -> new Student(student.getName(), student.getAge() * 2, student.getGender(), student.getGrades(), student.getFavouriteSubject()))
                .collect(Collectors.toList());
        return newStudents;
    }

    // filter
    public static List<Student> getStudentsWithMinimumAge(List<Student> students, int minAge) {
        /*List<Student> verifiedStudents = new ArrayList<Student>();
        for (Student student : students) {
            if (student.getAge() >= minAge) {
                verifiedStudents.add(student);
            }
        }
        return verifiedStudents;*/
        List<Student> newStudents = students.stream()
                .filter(student -> student.getAge() >= minAge)
                .collect(Collectors.toList());
        return newStudents;
    }

    // filter and possible double method references
    public static List<Integer> getPrimeAges(List<Student> students) {
        /*List<Integer> primeAges = new ArrayList<Integer>();
        for (Student student : students) {
            if (isPrime(student.getAge())) {
                primeAges.add(student.getAge());
            }
        }
        return primeAges;*/
        List<Integer> newStudents = students.stream()
                .map(Student::getAge)
                .filter(Main::isPrime)
                .collect(Collectors.toList());
        return newStudents;
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    // comparing (sorted)
    public static List<Integer> getSortedAges(List<Student> students) {
        /*List<Integer> sortedAges = new ArrayList<Integer>();
        for (Student student : students) {
            sortedAges.add(student.getAge());
        }
        Collections.sort(sortedAges);
        return sortedAges;*/
        List<Integer> ages = Main.getStudentAges(students);
        List<Integer> sortedAges = ages.stream()
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        return sortedAges;
    }

    // count
    public static long countMaleStudents(List<Student> students) {
        /*long counter = 0;
        for (Student student : students) {
            if (student.getGender().equals(Gender.MALE)) {
                counter++;
            }
        }
        return counter;*/
        return students.stream().filter(gender -> Gender.MALE.equals(gender.getGender())).count();
    }


    // average, orElse
    public static double avgAgeOfFemaleStudent(List<Student> students) {
        return students.stream()
                .filter(Student -> Student.getGender().equals(Gender.FEMALE))
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }


    // reduce
    public static double productOfStudentGrades(Student student) {
        return student.getGrades().stream()
                .mapToDouble(grade -> grade.getType().getValue())
                .reduce((a, b) -> a * b)
                .getAsDouble();
    }


    // sorted(default), findFirst
    public static Optional<Grade> getBestMathGradeFromStudent(Student student) {
        return student.getGrades().stream()
                .filter(grade -> grade.getSubject().equals(Subject.MATH))
                .sorted(Comparator.reverseOrder())
                .findFirst();
    }

    // anyMatch
    public static boolean atLeastOneGradeA(Student student) {
        return student.getGrades().stream()
                .anyMatch(s -> s.getType().equals(GradeType.A));
    }


    // limit, IntStream.iterate, boxed
    public static List<Integer> getFirstPrimes(int howMany) {
        return IntStream
                .iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(howMany)
                .boxed()
                .collect(Collectors.toList());
    }


}
