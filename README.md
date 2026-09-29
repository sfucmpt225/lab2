# lab2
CMPT 225's Lab2

## Intro
In this lab, you will identify and fix five deliberately introduced compilation errors in a student record management program. Your task is to locate each error, determine the cause, correct it, and successfully compile and run the program. Additionally, you are required to answer the written questions below and submit your answers along with your source code.

## Objectives
* Understand the role of static methods and static fields in Java
* Apply exception handling in Java
* File reading and writing in Java
* Understand the difference between checked exceptions and unchecked exceptions in Java
* Use Arrays in Java



#### Compilation Error 1
<details>
    <summary>There is something wrong with the FileManager class's writeLines() method</summary>


</details>

#### Compilation Error 2
<details>
    <summary>There is something wrong with the StatsCalculator class's average() method</summary>
    

</details>

#### Compilation Error 3
<details>
    <summary>There is something wrong with the FileManager class's readFirstLine() method</summary>


</details>

#### Compilation Error 4
<details>
    <summary>There is something wrong with the RecordParser class's RecordParser() method</summary>


</details>

#### Compilation Error 5
<details>
    <summary>There is something wrong with the StudentRecord class's StudentRecord() method</summary>


</details>


#### Written Questions
##### Question 1
In one or two sentences, explain why the main method should be static.
##### Question 2
In one or two sentences, explain the difference between an absolute path and a relative path.
##### Question 3
What is the difference between FileWriter(fileName, true) and FileWriter(fileName)? Explain in one or two sentences. 
##### Question 4
Explain the difference between checked and unchecked exceptions in one or two sentences. 
##### Question 5
Given two consecutive catch statements, should the catch statement for the more specific exception appear before the catch statement for the less specific exception? Yes or no?

## Program Output
Once you have successfully fixed all the compilation errors and the program runs correctly, make sure your output looks similar to the following:

```
== Writing grades.csv ==
Wrote 6 lines.

== Appending to grades.csv ==
Total lines written so far: 8

== Reading and parsing ==
  line 1 OK: Alice [90, 85, 77]
  line 2 OK: Bob [72, 88, 95]
  line 3 skipped (bad number): For input string: "abc"
  line 4 skipped (invalid): expected a name and at least one score but got 1 field(s): "Dave"
  line 5 skipped (invalid): name must not be empty
  line 6 skipped (invalid): score 101 is outside 0-100
  line 7 OK: Eve [88, 91, 79]
  line 8 OK: Frank [65, 70, 99]
Parsing finished. Valid records: 4 (StudentRecord objects created: 4)

== Report ==
Alice scores=[90, 85, 77] highest=90 average=84
Bob scores=[72, 88, 95] highest=95 average=85
Eve scores=[88, 91, 79] highest=91 average=86
Frank scores=[65, 70, 99] highest=99 average=78

== Unchecked exceptions ==
ArithmeticException: / by zero
ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 3 (array is [70, 80, 90])
NullPointerException: values must not be null
NullPointerException: called length() on a null String
NumberFormatException: For input string: "12x"
IllegalArgumentException: cannot find the max of an empty array
(finally: unchecked exception demo complete)

== Checked exceptions ==
FileNotFoundException: does_not_exist.csv (No such file or directory)

== try/finally without try-with-resources ==
  (finally: reader closed manually)
First line: "Alice, 90, 85, 77" (length 17)
```

 

## Submission
Zip the project directory along with your answers in a PDF document, and submit the ZIP file to Canvas.

## Rubric
| Criterion | ✓ Yes (1 pt) | ✗ No (0 pts) |
|-----------|-------------|------------|
| **Compilation Error 1 Fixed** | Meets requirement | Does not meet requirement |
| **Compilation Error 2 Fixed** | Meets requirement | Does not meet requirement |
| **Compilation Error 3 Fixed** | Meets requirement | Does not meet requirement |
| **Compilation Error 4 Fixed** | Meets requirement | Does not meet requirement |
| **Compilation Error 5 Fixed** | Meets requirement | Does not meet requirement |
| **Question 1** | Meets requirement | Does not meet requirement |
| **Question 2** | Meets requirement | Does not meet requirement |
| **Question 3** | Meets requirement | Does not meet requirement |
| **Question 4** | Meets requirement | Does not meet requirement |
| **Question 5** | Meets requirement | Does not meet requirement |
| **Total** | **x/10** | |
---

## Deadline
Sunday, October 4, 2026, at 11:59 PM PDT