import java.util.Scanner;

public class Poly {
    private int[] coefficient;   // coefficient p(x) = sum { coefficient[i] * x^i }
    private int degree;   // degree of Poly (-1 for the zero Poly)

    
    public Poly(int coef, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + exp);
        }
        coefficient = new int[exp+1];
        coefficient[exp] = coef;
        reduce();
    }


    private void reduce() {
        degree = -1;
        for (int i = coefficient.length - 1; i >= 0; i--) {
            if (coefficient[i] != 0) {
                degree = i;
                return;
            }
        }
    }

    
    public int degree() {
        return degree;
    }

    
    public Poly addPoly(Poly that) {
        Poly poly = new Poly(0, Math.max(this.degree, that.degree));
        for (int i = 0; i <= this.degree; i++) poly.coefficient[i] += this.coefficient[i];
        for (int i = 0; i <= that.degree; i++) poly.coefficient[i] += that.coefficient[i];
        poly.reduce();
        return poly;
    }

    
    public Poly multiplyPoly(Poly that) {
        Poly poly = new Poly(0, this.degree + that.degree);
        for (int i = 0; i <= this.degree; i++)
            for (int j = 0; j <= that.degree; j++)
                poly.coefficient[i+j] += (this.coefficient[i] * that.coefficient[j]);
        poly.reduce();
        return poly;
    }

    
    public int evaluate(int x) {
        int p = 0;
        for (int i = degree; i >= 0; i--)
            p = coefficient[i] + (x * p);
        return p;
    }


    
    public String toString() {
        if      (degree == -1) return "0";
        else if (degree ==  0) return "" + coefficient[0];
        else if (degree ==  1) return coefficient[1] + "x + " + coefficient[0];
        String s = coefficient[degree] + "x^" + degree;
        for (int i = degree - 1; i >= 0; i--) {
            if      (coefficient[i] == 0) continue;
            else if (coefficient[i]  > 0) s = s + " + " + (coefficient[i]);
            else if (coefficient[i]  < 0) s = s + " - " + (-coefficient[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

    
    public static void main(String[] args) { 
        Poly p1   = new Poly(7, 3);
        Poly p2   = new Poly(4, 2);
        Poly p3   = new Poly(2, 0);
        Poly p4   = new Poly(3, 1);
        Poly p    = p1.addPoly(p2).addPoly(p3).addPoly(p4);   
        
        Poly q1   = new Poly(5, 2);
        Poly q2   = new Poly(3, 0);
        Poly q    = q1.addPoly(q2);                     

        int choice;
        String more;
        Scanner sc= new Scanner(System.in);
        do{
        System.out.println("1.Evaluting polynomial");
		System.out.println("2.Finding degree");
		System.out.println("3.Add 2 polynomials");
		System.out.println("4.Multiply 2 polynomials");
		choice =sc.nextInt();
        switch(choice){
		case 1:
			System.out.println("The polynomial is "+p.toString());
			System.out.println("Enter a value to evaluate the polynomial with");
			int value=sc.nextInt();
			System.out.println(p.evaluate(value));
			break;
				
		case 2:
			System.out.println("The polynomial is "+p.toString());
			System.out.println("Degree of polynomial is:  "+ p.degree());
			break;
			
		case 3:
			System.out.println("We will add " + p.toString() +" and "+ q.toString()+":");
			System.out.println("Result:  "+p.addPoly(q));
			break;
		
		case 4:
			
			System.out.println("We will multiply " + p.toString() +" and "+ q.toString()+":");
			System.out.println("Result  "+p.multiplyPoly(q));
			break;
			
		default:
			System.out.println("Invalid input number");
		}
        System.out.println("Want to enter More ?(Y/N) : ");
		more = sc.next();
        
        }while(more.equals("Y")|| more.equals("y"));
        sc.close();
    }
}
