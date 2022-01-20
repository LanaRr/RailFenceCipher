package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.net.URL;

public class FileHandler {
	RailFenceCipher rfc;

	public String parseUrl(String urlName) throws Exception {
		String line = "";
		String plainText = "";
		URL url = new URL(urlName);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = br.readLine()) != null) {
				plainText += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return plainText;

	}

	public String parse(String fileName) {
		String line = "";
		String plainText = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			while ((line = br.readLine()) != null) {
				plainText += line;
			}

			br.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return plainText;
	}

}
