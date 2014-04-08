import java.util.*;

public class mizunon_smithct5_Lab09 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Number of students: ");
        int number = input.nextInt();
        input.nextLine();

        String students[] = new String[number];
        int test1[] = new int[number];
        int test2[] = new int[number];
        int test3[] = new int[number];
        int test4[] = new int[number];

        for (int i = 0; i < number; i++) {
            System.out.print("Student's name: ");
            students[i] = input.nextLine().toLowerCase();

            System.out.print("Score for Test 1: ");
            test1[i] = input.nextInt();
            input.nextLine();
            System.out.print("Score for Test 2: ");
            test2[i] = input.nextInt();
            input.nextLine();
            System.out.print("Score for Test 3: ");
            test3[i] = input.nextInt();
            input.nextLine();
            System.out.print("Score for Test 4: ");
            test4[i] = input.nextInt();
            input.nextLine();
        }

        while (true) {
            System.out.println("| ============================== |");
            System.out.println("| 1. Get Student Grades and GPA  |");
            System.out.println("| 2. Quit                        |");
            System.out.println("| ============================== |");
            
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 2)
                break;
            else {
                System.out.println("Student to look up: ");
                String student = input.nextLine().toLowerCase();
                for (int i = 0; i < number; i++) {
                    if (student.equals(students[i])) {
                        int[] score_for_student = {test1[i], test2[i], test3[i], test4[i]};
                        
                        // Change the value of array and get the GPA
                        double gpa_score = gpa(score_for_student);
                        
                        System.out.print("Student: " + students[i]);
                        for (int j = 0; j < 4; j++) {
                            String grade = "";
                            switch (score_for_student[j]) {
                                case 1:
                                    grade = "D"; break;
                                case 2:
                                    grade = "C"; break;
                                case 3:
                                    grade = "B"; break;
                                case 4:
                                    grade = "A"; break;
                                default:
                                    grade = "F";
                            }
                            // Print grade each loop
                            System.out.format("\n\tTest%d: %s", j + 1, grade);
                        }
                        System.out.println();
                        
                        System.out.println("GPA: " + gpa_score);
                        
                        break;
                    }
                }
            }
        }
    }
    
    public static double gpa(int[] score_for_student) {
        for(int i = 0; i < 4; i++){
            if(score_for_student[i] >= 90)
                score_for_student[i] = 4;
            else if(score_for_student[i] >= 80)
                score_for_student[i] = 3;
            else if(score_for_student[i] >= 70)
                score_for_student[i] = 2;
            else if(score_for_student[i] >= 60)
                score_for_student[i] = 1;
            else
                score_for_student[i] = 0;
        }
        double ret = ((double)score_for_student[0] + (double)score_for_student[1] + (double)score_for_student[2] + (double)score_for_student[3]) / 4;
        return ret;
    }
}