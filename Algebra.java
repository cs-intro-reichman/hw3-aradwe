// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println("---------------------");
		System.out.println("Test plus func:");
		System.out.println(plus(-4,-3));   // -4 + -3
		System.out.println(plus(-2,3));   // -2 + 3
	    System.out.println(plus(2,-3));   // 2 + -3
	    System.out.println(plus(2,3));   // 2 + 3
		System.out.println("---------------------");
		System.out.println("Test minus func:");
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
		System.out.println(minus(-7,-2));  // -7 - -2
		System.out.println(minus(-7,2));  // -7 - 2
		System.out.println(minus(7,-2));  // 7 - -2
		System.out.println("---------------------");
		System.out.println("Test times func:");
		System.out.println(times(3,4));  // 3 * 4
		System.out.println(times(-3,4));  // -3 * 4
		System.out.println(times(3,-4));  // 3 * -4
		System.out.println(times(-3,-4));  // -3 * -4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println("---------------------");
		System.out.println("Test pow func:");
		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println("---------------------");
		System.out.println("Test div func:");
		System.out.println(div(12,3));   // 12 / 3    
		System.out.println(div(-12,3));   // -12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println("---------------------");
		System.out.println("Test mod func:");
		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
		System.out.println(mod(17,3));  // 17 % 3    
		System.out.println("---------------------");
		System.out.println("Test sqrt func:");
		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		boolean isX1Negative = x1 < 0;
		boolean isX2Negative = x2 < 0;

		// Handle two negative numbers
		if (isX1Negative && isX2Negative) {
			x1 = -x1;
			x2 = -x2;
			for(int i = 0; i < x2; i++){
				x1++;
			}
			return -x1;	// Return negative result for both negative inputs		
		}
		// Handle x1 is negative and x2 positive
		else if(isX1Negative){
			x1 = -x1;
			return minus(x2, x1);
		}
		// Handle x1 is positive and x2 negative
		else if(isX2Negative){
			x2 = -x2;
			return minus(x1, x2);
		}
		// Both are positive, add normally
		else{
			for(int i = 0; i < x2; i++)
			x1++;
			return x1;
		}
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		boolean isX1Negative = x1 < 0;
		boolean isX2Negative = x2 < 0;

		// Handle two negative numbers
		if (isX1Negative && isX2Negative) {
			x1 = -x1;
			x2 = -x2;
			for(int i = 0; i < x2; i++){
				x1--;
			}
			return -x1;
		}
		// Handle x1 is negative and x2 positive
		else if(isX1Negative){
			x1 = -x1;
			return -plus(x1, x2);
		}
		// Handle x1 is positive and x2 negative
		else if(isX2Negative){
			x2 = -x2;
			return plus(x1, x2);
		}
		// Both are positive, add normally
		else{
			for(int i = 0; i < x2; i++){
				x1--;
			}
			return x1;
		}
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		boolean isX1Negative = x1 < 0;
		boolean isX2Negative = x2 < 0;
		int x3 = 0;

		// Handle two negative numbers
		if (isX1Negative && isX2Negative) {
			x1 = -x1;
			x2 = -x2;
			for(int i = 0; i < x2; i++){
				x3 = plus(x3, x1);
			}
			return x3;
		}

		// Handle x1 is negative and x2 positive
		else if (isX1Negative){
			x1 = -x1;
			for(int i = 0; i < x2; i++){
				x3 = plus(x3, x1);
			}
			return -x3;
		}
		// Handle x1 is positive and x2 negative
		else if (isX2Negative){
			x2 = -x2;
			for(int i = 0; i < x2; i++){
				x3 = plus(x3, x1);
			}
			return -x3;
		}
		// Handle two positive numbers
		else{
			for(int i = 0; i < x2; i++){
				x3 = plus(x3, x1);
			}
			return x3;
		}
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		int x3 = x;
		// Handle power of zero case
		if(n==0){
			return 1;
		}
		for(int i = 1; i < n; i++)
		x3 = times(x3, x);
		return x3;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		int quotient = 0;
		boolean negativeResult = false;
		
		// Prevent edge case of zero division
		if (x2 == 0){
			System.out.println("Devision by zero is not allowed!");
			return 0;
		}
		
		// Check negativity of the result and handle the numbers as positive for comfort
		if(x1 < 0){
			x1 = -x1;
			if(x2 < 0){
				x2 = -x2;
			}
			else{
				negativeResult = true;
			}
		if(x2 < 0){
			x2 = -x2;
			negativeResult = true;
		}
		
		// If x1 is greater than equal to x2 subtract x2 from x1 an increase the quotient by one.
		} 
		while (x1 >= x2) {
			x1 = minus(x1, x2);
			quotient++;
		}
		
		// If negative result is true - make the quotient negative 
		if (negativeResult) {
			quotient = - quotient;
		}
		return quotient;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		int divResult = div(x1, x2);
		return minus(x1, times(x2, divResult));
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		// make sure the sqrt of the number is defined
		if(x < 0){
			System.out.println("Square root of a negative number is undefined");
			return 0;
		}
		
		//find the largest number whose square is less than or equal to x
		int sqrtResult = 0;
		while (times(sqrtResult, sqrtResult) <= x) {
			sqrtResult++;
		}
		return minus(sqrtResult, 1);
	}	  	  
}