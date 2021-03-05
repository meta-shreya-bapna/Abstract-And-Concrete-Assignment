package intSet;
import java.util.*;

public class intSet {
	private int size;
	private int[] set;
	Scanner sc = new Scanner(System.in);
	
	//default constructor to get array values
	private intSet(){
	 	
		System.out.println("Enter the size of the array that is to be created::");
	    int size = sc.nextInt();
	    int[] myArray = new int[size];
	    System.out.println("Enter the elements of the array ::");

	    for(int i=0; i<size; i++) {
	       myArray[i] = sc.nextInt();
	     }
		
		arrToSetConvert(myArray, size);
	}
	
	//parameterized constructor to array assign values
	private intSet(int[] array){
		
		int n = array.length;
		arrToSetConvert(array, n);
		
	}
	
	//converts array into set
	public void arrToSetConvert(int[] array, int n){
		Arrays.sort(array);
		int j=0;
		set=new int[n];
		for (int i=0; i<n-1; i++){
			if(array[i]!= array[i+1]){
				set[j++] = array[i];
			}
		}
		set[j++]=array[n-1];
		size=j;
	}
	
	//Checks if no. is member of set
	public  boolean isMember()
	{int x;
	
	 System.out.println("enter an element to check its presence");
	 x=sc.nextInt();
	 for(int i=0; i<set.length; i++)
	 {
		 if(set[i]==x)
		 {
			 return true;
		 }
	 }
	 return false;
		
	}
	
	//checks if x is member of set
	public boolean isMember (int x){
		boolean Member = false;
		for(int i=0; i<size; i++){
			if(x == set[i]){
				Member= true;
				break;
			}
		}
		return Member;
	}
	
	//Calculates length of array
	public int size(){
		return size;
	}
	
	//Checks for subset
	public boolean isSubset(int[] s){
		int m=set.length,  n=s.length;
	    int i = 0;
	    int j = 0;
	    if(s.length > set.length){
			return false;
		}
	    for (i=0; i<n; i++){
	        for (j = 0; j<m; j++){
	           if(s[i] == set[j])
	              break;
	        }
	        if (j == m)
	           return false;
	    }
	    return true;
	}
	
// to get complement of a set within range 1-1000
	public intSet getComplement(){
		final int upperLimit = 1000;
		int[] compSet= new int[upperLimit-set.length+1];
		
		int compSetIndex=0, setIndexCount=0;
		for(int i=1; i<=upperLimit; i++){
			if(setIndexCount < set.length){
				if(isMember(i)){
					setIndexCount++;
				}
				else{
					compSet[compSetIndex]=i;
					compSetIndex++;
				}
			}
			else{
				compSet[compSetIndex]=i;
				compSetIndex++;
			}
		}
		
		intSet complSet = new intSet(compSet);
		return complSet;
	}
	
// calculates union of 2 sets 	
	public intSet union(int[] set1 ,int[] set2){
		int[] set3 = new int[set1.length + set2.length];
		int i=0;
		for(i=0; i<set1.length;i++){
			set3[i] = set1[i];
		}
		for(int j=0; j<set2.length; j++)
			set3[j+i] = set2[j];
		intSet unionSet = new intSet(set3);
		return unionSet;
	}

	
	public static void main(String[] args)
	{
		boolean flag=true;
		int choice;
		Scanner sc = new Scanner(System.in);
		while(flag==true){
			System.out.println("1.IsMember\n"+
							   "2.Calculate Size\n"+
							   "3.Subset or not\n"+
							   "4.Complement\n"+
							   "5.Union\n"+
							   "6.Exit\n");
		
			choice= sc.nextInt();
			intSet s1=new intSet();
				
				switch(choice){
				case 1:
					System.out.println("Member of set :"+ s1.isMember());
					break;
				
				
				case 2:
					System.out.println("Size is :"+ s1.size() );
					break;
					
				case 3:
					
					System.out.println("Enter set to check if it is Subset or not ->");
					intSet s2 = new intSet();
					System.out.println("Is subset : " + s1.isSubset(s2.set));
					break;
				
				case 4:
					intSet s4 = s1.getComplement();
					System.out.print("Complement values are : ");
					for(int i=0; i<s4.size; i++){
						System.out.print(s4.set[i]+ " ");
					}
					System.out.println("\n");
					break;
				
				case 5:
					System.out.println("Enter a set for union ->");
					intSet s3 = new intSet();
					intSet union = s1.union(s1.set, s3.set);
					System.out.print("Union Set is :");
					for(int i=0; i<union.size; i++){
						System.out.print(union.set[i]+ " ");
					}
					System.out.println("\n");
					break;
				
				case 6:
					flag=false;
					break;
					
				default:
					System.out.println("Invalid input number");
				}
			}
			
		sc.close();
	}
	
}
