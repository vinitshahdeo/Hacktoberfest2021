class Sub

{

public static void main(String[] arg)

{

int a,b,c;

Scanner s=new Scanner(System.in);

System.out.println("Enter first number");

a=s.nextInt();

System.out.println("Enter second number");

b=s.nextInt();

if(a>b)

  c=a-b;

else

  c=b-a;

System.out.println("Subtraction of two numbers is : "+c);

}

}
