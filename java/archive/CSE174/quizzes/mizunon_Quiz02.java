public class mizunon_Quiz02{
     public static void main(String[] args){
          //-------------------------------------- Q1
          String Q1 = "This is a quiz";
          System.out.println(evenLetters(Q1));

          //-------------------------------------- Q2
          int[] Q2 = {4,2,6,1,7,5,9,0};
          int[] Q2result = minMax(Q2);
          System.out.printf("Min: %d  Max: %d\n", Q2result[0], Q2result[1]);

          //-------------------------------------- Q3
          String[] Q3a = {"apple", "carrot"};
          String[] Q3b = {"cindy", "mike"};
          int Q3index = 1;
          System.out.println(concatenate(Q3a, Q3b, Q3index));


          //-------------------------------------- Q4
          String Q4s = "hello";
          char[] Q4 = breakDown(Q4s);
          for(int i = 0; i < Q4.length; i++)
               System.out.println(Q4[i]);

     }

     //#####################################################  Question 1
     public static String evenLetters(String str){
         String ret = "";

         for (int i = 0; i < str.length(); i += 2)
             ret += str.charAt(i);

         return ret;
     }
 
     //#####################################################  Question 2
     public static int[] minMax(int[] nums) {
  // HINT: make the first 2 elements of the array you are
  //      returning equal to the first value of nums[]
  
         int[] ret ={nums[0], nums[0]};
         for (int i = 1; i < nums.length; i++) {
             // When nums[i] is the smallest so far
             if (nums[i] < ret[0])
                 ret[0] = nums[i];
             // When nums[i] is the largest so far
             if (nums[i] > ret[1])
                 ret[1] = nums[i];
         }
         return ret;
     }

     //#####################################################  Question 3
     public static String concatenate(String[] a, String[] b, int index) {

         String ret = "";

         // Don't go further if the index is less than 0
         if (index < 0)
             return ret;

         if (index < a.length) {
             // When both arrays have the length smaller than index
             if (index < b.length)
                 return a[index] + b[index];

             // When the length of b is smaller than index
             else
                 return a[index];
         }
         // When the length of a is smaller than index
         // but we don't know about the length of b
         else {
             // If the length of b is or greater than index
             if (index < b.length)
                 return b[index];
             // When both arrays are smaller than index
             else
                 return ret;
         }
     }

     //#####################################################  Question 4
     public static char[] breakDown(String str){
 
         char[] ret = new char[str.length()];

         for (int i = 0; i < str.length(); i++)
             ret[i] = str.charAt(i);

         return ret;
     }
}
