//
//  Mark Anthony Start          180140208       C02220 CW2 
//

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.io.*;

public class ReceiptStorageEngine {
    private static final String filesLocation = "." + File.separator + "invoices";

    public ReceiptStorageEngine() {
        //create dir that holds all the files if it doesn't already exist
        Path storageDir = Paths.get(filesLocation);
        if(!Files.exists(storageDir)) {
            try {
                Files.createDirectory(storageDir);
            } catch (IOException e) {
                System.err.println("Error: storage directory for receipts does not exist and cannot be created.");
            }
        }
    }

    public String[] fetch(String filename) {
        if (fileExists(filename)) {
            return readFile(filename);
        } else {
            return null;
        }
    }

    private String[] readFile(String filename) {

	String [] contents = null;
	File fileToRead = new File ( getFilePath(filename).toString() );

        try {
		FileInputStream fileStream = new FileInputStream(fileToRead);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
                contents = (String[]) objectInputStream.readObject();

        } catch (FileNotFoundException e) {
                System.err.println("Error: file not found " + e);
        } catch (IOException e) {
                System.err.println("Error: " + e);
        } catch (ClassNotFoundException e) {
                System.err.println("Error: " + e);
        }

        return contents;

    }

    public boolean save(String[] receipt, String filename) {

	File fileToSave = new File( getFilePath(filename).toString() );

        try {
                FileOutputStream fileStream = new FileOutputStream(fileToSave);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);
                objectOutputStream.writeObject(receipt);
                objectOutputStream.close();
                return true;

	} catch (FileNotFoundException e) {
		System.err.println("Error: file not found " + e);
	} catch (IOException e) {
		System.err.println("Error: " + e);
	}

	return false;	

    }

    public String[] listFiles() {
        File invoiceDir = new File(filesLocation);
	String[] filesList = invoiceDir.list();
        Arrays.sort(filesList);
        return filesList;
    }

    private boolean fileExists(String filename) {
        File file = new File( getFilePath(filename).toString() );
        if (file.exists()) return true;
	return false;
    }

    private Path getFilePath(String filename) {
        return Paths.get(filesLocation + File.separator + filename);
    }
}
