package assessment.question1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assessment.question3.TimingTool;

/**
 * Question 1:
 * Validate a text file of IP Address(es), and filter out any invalid IP address(es).
 * 
 * Criteria:
 * Format A.B.C.D - Value A, B, C, D may range from 0 - 255
 * Leading zeros are allowed
 * Length of A, B, C, D cannot be greater than 3
 * @author Ginny
 *
 */
public class IPValidationRegex {
	
	private static final String INPUT_FILENAME = "src/assessment/data/ipaddress.txt";
	private static final String OUTPUT_FILENAME_VALIDATED_IP = "src/assessment/data/validated_ipaddress.txt";
	private static final String OUTPUT_FILENAME_INVALID_IP = "src/assessment/data/invalid_ipaddress.txt";

	public static void main(String[] args) {
		
		System.out.println("IPValidationRegex start...");
		TimingTool.start();
		
		IPValidator validator = new IPValidator();
		BufferedReader bufferReader = null;
		FileReader fileReader = null;
		FileWriter validatedIpFileWriter = null;
		BufferedWriter validatedIpBufferWriter = null;
		FileWriter invalidIpFileWriter = null;
		BufferedWriter invalidIpBufferWriter = null;
		
		try {
			File file = new File(INPUT_FILENAME);
			fileReader = new FileReader(file);
			bufferReader = new BufferedReader(fileReader);
			String readLine = "";
			validatedIpFileWriter = new FileWriter(OUTPUT_FILENAME_VALIDATED_IP);
			validatedIpBufferWriter = new BufferedWriter(validatedIpFileWriter);
			invalidIpFileWriter = new FileWriter(OUTPUT_FILENAME_INVALID_IP);
			invalidIpBufferWriter = new BufferedWriter(invalidIpFileWriter);
			
			System.out.println("Reading text file=" + INPUT_FILENAME);
			
			// Read from text file
			int total = 0;
			int validCount = 0;
			int invalidCount = 0;
			while ((readLine = bufferReader.readLine()) != null) {
				++total;
				// Call method to validate
				boolean validIP = validator.validate(readLine);
				
				System.out.println("IP= " + readLine + "; validIP= " + validIP);
				
				// Write into text file
				if (validIP) {
					++validCount;
					validatedIpBufferWriter.write(readLine + "\n");
				} else {
					++invalidCount;
					// invalid IP address
					invalidIpBufferWriter.write(readLine + "\n");
				}
			}
				
			System.out.println("Total Count=" + total + ". Validated IP Address=" + validCount + ". Invalid IP Address=" + invalidCount);
			System.out.println("Validated IP Address file path=" + OUTPUT_FILENAME_VALIDATED_IP);
			System.out.println("Invalid IP Address file path=" + OUTPUT_FILENAME_INVALID_IP);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			// Close file writer & file reader
			try {
				if (bufferReader != null) bufferReader.close();
				if (fileReader != null) fileReader.close();
				if (validatedIpBufferWriter != null) validatedIpBufferWriter.close();
				if (validatedIpFileWriter != null) validatedIpFileWriter.close();
				if (invalidIpBufferWriter != null) invalidIpBufferWriter.close();
				if (invalidIpFileWriter != null) invalidIpFileWriter.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			TimingTool.stop();
			System.out.println("IPValidationRegex completed...");
		}
	}
}

class IPValidator {
	private static final String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private Pattern pattern;
	private Matcher matcher;
	
	public IPValidator() {
		pattern = Pattern.compile(IP_ADDRESS_PATTERN);
	}
	
	public boolean validate(final String ip) {
		String ipString = (ip == null) ? "" : ip.trim();
		matcher = pattern.matcher(ipString);
		return matcher.matches();
	}
}