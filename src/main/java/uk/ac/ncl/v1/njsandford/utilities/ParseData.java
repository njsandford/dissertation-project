package uk.ac.ncl.v1.njsandford.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Natalie on 12/03/2017.
 */
public class ParseData {

    public ArrayList<BlastData> readBlastFile(String fileName) {
        ArrayList<BlastData> blastData = new ArrayList<>();

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            String currentLine;

            bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] splitLine = currentLine.split(",");
                BlastData lineData = new BlastData(splitLine[0], splitLine[1], Double.parseDouble(splitLine[2]), Integer.parseInt(splitLine[3]), Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5]),
                        Integer.parseInt(splitLine[6]), Integer.parseInt(splitLine[7]), Integer.parseInt(splitLine[8]), Integer.parseInt(splitLine[9]), Double.parseDouble(splitLine[10]), Double.parseDouble(splitLine[11]));
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
