package uk.ac.ncl.njsandford;

import org.jgrapht.demo.StoreData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Natalie on 12/03/2017.
 */
public class ParseData {

    public ArrayList<StoreData> readBlastFile(String fileName) {
        ArrayList<StoreData> blastData = new ArrayList<>();

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            String currentLine;

            bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] splitLine = currentLine.split(",");
                StoreData lineData = new StoreData(splitLine[0], splitLine[1], Double.parseDouble(splitLine[2]), Integer.parseInt(splitLine[3]),
                        Integer.parseInt(splitLine[6]), Integer.parseInt(splitLine[7]), Integer.parseInt(splitLine[8]), Integer.parseInt(splitLine[9]));
                blastData.add(lineData);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) bufferedReader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return blastData;
    }

}
