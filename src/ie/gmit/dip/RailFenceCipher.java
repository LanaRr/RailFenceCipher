package ie.gmit.dip;

public class RailFenceCipher {
	private static final int MIN_KEY_LENGTH = 1;
	private int key;
	private int offset; // instance variables to be used throughout this class
	private String plainText = "";
	private String cipherText = "";
	private char[][] matrix;

	public RailFenceCipher(int key, int offset, String plainText) {
		// Constructor that gets the input from menu and sets above instance variables
		// to be used throughout application
		this.key = key;
		this.offset = offset;
		this.plainText = plainText;
	}

	private boolean validateText(String text) throws Exception {
		if (text == null || text.length() < MIN_KEY_LENGTH) {
			throw new Exception(
					"[ERROR] Invalid Text length. Text must be at least [" + MIN_KEY_LENGTH + "] characters long.");
		}
		return false;
	}

	private char[][] createMatrix() {
		matrix = new char[key][plainText.length()];

		// creates a blank matrix with the parameters of text length and number of rows.

		for (int i = 0; i < key; i++) {
			for (int j = 0; j < plainText.length(); j++) {
				matrix[i][j] = '\n';
			}
		}
		return matrix;

	}

	private char[][] createRailFence() {
		createMatrix();
		int row = offset - 1;
		int check = 0;

		// uses blank matrix to and offset value to create the rail fence structure to
		// be used for encryption and display of rail fence.

		for (int i = 0; i < plainText.length(); i++) {
			matrix[row][i] = plainText.charAt(i);
			if (check == 0) {
				row++;
				if (row == key - 1) {
					check = 1;
				}

			} else if (check == 1) {
				row--;
				if (row == 0) {
					check = 0;
				}

			}
		}
		return matrix;
	}

	public String encrypt() throws Exception {
		validateText(plainText);
		createRailFence();

		// loops row by row through the matrix and adds each character to the cipher
		// text String

		for (int i = 0; i < key; i++) {
			for (int j = 0; j < plainText.length(); j++) {
				if (matrix[i][j] != '\n') {
					cipherText += matrix[i][j];
				}
			}

		}
		return cipherText;

	}

	public String decrypt() {
		createMatrix();
		int row = offset - 1; // offset is minus one to account for first index being 0, ensuring that
								// starting row is correct for the user
		int check = 0;
		String decryptText = "";

		// uses blank matrix, cipherText length and offset to recreate rail fence
		// structure with 'x'

		for (int i = 0; i < cipherText.length(); i++) {
			matrix[row][i] = 'x';

			if (check == 0) {
				row++;
				if (row == key - 1) {
					check = 1;
				}

			} else if (check == 1) {
				row--;
				if (row == 0) {
					check = 0;
				}

			}
		}
		// looping through row by row the letters of cipher text are added in place of
		// 'x'
		int k = 0;
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 'x') {
					matrix[i][j] = cipherText.charAt(k);
					k++;

				}

			}

		}
		// loops through the matrix in the zig zag format and appends to decrypttext
		// String
		int j = offset - 1;
		check = 0;
		for (int i = 0; i < cipherText.length(); i++) {

			if (check == 0) {

				decryptText += matrix[j][i];
				j++;

				if (j == key - 1) {
					check = 1;

				}

			} else if (check == 1) {
				decryptText += matrix[j][i];
				j--;

				if (j == 0) {
					check = 0;

				}
			}
		}

		return decryptText;
	}

	public String displayRailFence() {
		createRailFence();
		// adds '*' to make rail matrix more readable and prints the output

		for (int i = 0; i < key; i++) {
			System.out.println();
			for (int j = 0; j < plainText.length(); j++) {
				if (matrix[i][j] == '\n') {
					matrix[i][j] = '*';
				}

			}
			System.out.println(matrix[i]);
		}
		return matrix.toString();

	}

}
