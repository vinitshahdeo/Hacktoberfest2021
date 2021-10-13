package contributors;

import java.util.Scanner;


public class MyProgramAboutArray {
    public static void main(String[] args) {
        
    
    Scanner in = new Scanner(System.in);
    int n;
    System.out.print("Input Amount Student :");
    n=in.nextInt();
    String [][] dataArray = new  String [n][3];
    //Create data array
    for(int i =0 ; i < n; i++){
        System.out.println("");
        System.out.println("data student to :"+(i+1));
      for(int j=0 ; j < 3 ; j++){
          if(j==0){
              System.out.print("SIN\t:");
          }else if(j==1){
              System.out.print("NAME\t:");
          }else{
              System.out.print("MAJOR  \t:");
          }
          System.out.print("");
          dataArray[i][j]=in.next();
      }
    }
     System.out.println("list of data Student");
     System.out.println("--------------------------------");
     
     
     //Show data student with for
     System.out.println("Data Student");
     System.out.println("SIN\t\tNAME\t\tMAJOR\t");
     int k=0;
     for(int i =0 ; i < n ; i++){
         
         System.out.println();
         k++;
         System.out.print(k+" " );
         
         for(int j =0; j < 3; j++){
            
             System.out.print(dataArray[i][j]+"\t\t");
             
         }
     }
    
    
    } 
}
