public class NumberToWord {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter The number");
		int n = scanner.nextInt();
		String wordString = convert(n);
		System.out.println(wordString);
		scanner.close();
	}

	public static String convert(int n) {
		final String[] unit = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		final String[] tens = { "", // 0
				" ", // 1
				"Twenty", // 2
				"Thirty", // 3
				"Forty", // 4
				"Fifty", // 5
				"Sixty", // 6
				"Seventy", // 7
				"Eighty", // 8
				"Ninety" // 9
		};
		if (n < 10) {
			return unit[n];
		}
		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? tens[1] : tens[0]) + unit[n % 10];
		}
		if (n < 1000) {
			return unit[n / 100] + " " + "hundred" + (n % 100 != 0 ? tens[1] : tens[0]) + convert(n % 100);
		}
		if (n < 100000) {
			return convert(n / 1000) + " " + "thousand" + (n % 10000 != 0 ? tens[1] : tens[0]) + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " " + "lakh" + (n % 10000000!= 0 ? tens[1] : tens[0]) + convert(n % 100000);
		}

		return "The number is not possible to convert";
	}
}
