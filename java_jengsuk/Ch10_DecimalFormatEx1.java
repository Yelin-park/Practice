package java_jengsuk;

import java.text.DecimalFormat;

public class Ch10_DecimalFormatEx1 {

	public static void main(String[] args) {
		double num = 1234567.89;
		String[] pattern = {
				"0",
				"#",
				"0.0",
				"#.#",
				"0000000000.0000",
				"##########.####",
				"#.#-",
				"-#.#",
				"0.0-",
				"#,###.##",
				"#,####.##",
				"#,###.##+;#,###.##-"
		};
		
		for (int i = 0; i < pattern.length; i++) {
			DecimalFormat df = new DecimalFormat(pattern[i]);
			System.out.printf("%19s : %s\n", pattern[i], df.format(num));
		}
	} // main

} // class
