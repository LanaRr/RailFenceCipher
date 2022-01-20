package ie.gmit.dip;

import java.util.Scanner;

public class Menu {
	private Scanner s;
	private boolean keepRunning = true;
	private FileHandler fh = new FileHandler();
	private RailFenceCipher rfc;

	public Menu() {
		s = new Scanner(System.in);

	}

	@SuppressWarnings("preview")
	public void start() throws Exception {
		while (keepRunning) { // Switch statement to continue showing options throughout program duration
			menuOptions();
			int choice = Integer.parseInt(s.next());

			switch (choice) {
			case 1 -> getParameters();
			case 2 -> encrypt();
			case 3 -> decrypt();
			case 4 -> displayRailFence();
			case 5 -> {
				System.out.println("[INFO] Shutting down now....");
				keepRunning = false;

			}
			default -> System.out.println("[ERROR] Invalid input.");

			}
		}
	}

	private void menuOptions() {
		System.out.println("(1) Enter Programme requirements");
		System.out.println("(2) Encrypt");
		System.out.println("(3) Decrypt");
		System.out.println("(4) Display Rail Fence");
		System.out.println("(5) Quit");
		System.out.println("Select an option [1-5]>");
	}

	private void getParameters() {
		String fileOrUrlName = null;
		String plainText = null;
		int key = 0;// local variables initialized to be used to set instance variables in rail
					// fence class.
		int offset = 0;

		System.out.println("(1) To Enter File>");
		System.out.println("(2) To Enter Url>");

		int choice = Integer.parseInt(s.next());

		if (choice == 1) {
			System.out.println("Enter File Name>");
			fileOrUrlName = s.next(); // gets file name for parse method
			plainText = fh.parse(fileOrUrlName);

		} else if (choice == 2) {
			System.out.println("Enter URL>");
			fileOrUrlName = s.next(); // gets URL for URL parse method
			try {
				plainText = fh.parseUrl(fileOrUrlName);
			} catch (Exception e) {
				System.out.println("[ERROR] Invalid Url");
				e.printStackTrace();
			}
		}

		System.out.println("Enter desired number of Rails>");
		key = s.nextInt(); // sets key value to format number of rows

		System.out.println("Enter starting row>");
		offset = s.nextInt();
		// sets offset value in order to change starting row

		RailFenceCipher rfc = new RailFenceCipher(key, offset, plainText); // uses local variables to set instance
																			// variables in railFenceCipher class
		this.rfc = rfc;

	}

	public void encrypt() throws Exception {
		System.out.println("[INFO] File Encrypted...");
		System.out.println(rfc.encrypt());

	}

	public void decrypt() throws Exception {
		System.out.println("[INFO] File Decrypted ... ");
		System.out.println(rfc.decrypt());

	}

	private void displayRailFence() throws Exception {
		rfc.displayRailFence();

	}
}
