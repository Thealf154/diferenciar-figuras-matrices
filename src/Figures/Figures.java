/*
* How this code even works:
* The non abstract methods are treated like setters for the variables:
*   - coordinates
*   - distances 
*   - vectors 
*   
* These varibles are treated like the properties of the instance.
*
* The only method that is not a setter, is the resolve() method  and it
* takes the instance's properties to work.
* 
*/

package Figures;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
//This is to read the Json
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Iterator;


abstract class Figures {

    // ======================== Variables ==========================
    protected ArrayList<float[]> coordinates;
    protected ArrayList<Float> distances;
    protected ArrayList<float[]> vectors;

    protected float centerX;
    protected float centerY;
    // =============================================================

    // ======================== Constructors =======================
    Figures(int value) {
        this.coordinates = setCoordinates(value, 0, 0);
        this.distances = setDistances(coordinates, 0, 0);
        this.vectors = setVectors(distances);
    }

    Figures(int value, float center_x, float center_y) {
        this.coordinates = setCoordinates(value, center_x, center_y);
        this.distances = setDistances(coordinates, center_x, center_y);
        this.vectors = setVectors(distances);
    }

    Figures(String fileName) {
        this.coordinates = setCoordinatesFromData(fileName);
        this.distances = setDistances(coordinates, centerX, centerY);
        this.vectors = setVectors(distances);
    }

    // =============================================================

    // ======================== Methods ============================
    protected abstract ArrayList<float[]> setCoordinates(int Value, float center_x, float center_y);

    // This method take a json file with all the possible coordinates
    // and makes an array
    protected ArrayList<float[]> setCoordinatesFromData(String fileName) {
        ArrayList<float[]> finalCoordinates = new ArrayList<>();
        int count;
        JSONArray posx;
        JSONArray posy;
        JSONArray centerCoordinates;
        String type;
        Iterator<?> itr1;
        Iterator<?> itr2;

        try {
            JSONParser jsonParser = new JSONParser();
            // Create JSON object
            Object jsonObj = jsonParser.parse(new FileReader(fileName));

            // Typecasting obj to JSONObject
            JSONObject json = (JSONObject) jsonObj;

            // Get POSX adn POSY list
            posx = (JSONArray) json.get("posX");
            posy = (JSONArray) json.get("posY");

            // Get the center
            centerCoordinates = ((JSONArray) json.get("center"));
            type = centerCoordinates.get(0).getClass().getName();
            if(type == "long"){
                this.centerX = (float) (double) centerCoordinates.get(0);
                this.centerY = (float) (double) centerCoordinates.get(1);
            }
            else if(type == "double"){
                this.centerX = (float) (long) centerCoordinates.get(0);
                this.centerY = (float) (long) centerCoordinates.get(1);
            }

            // Loop through all the values
            itr1 = posx.iterator();
            itr2 = posy.iterator();
            count = 0;
            while (itr1.hasNext() && itr2.hasNext() && (count < 20)) {
                float[] localCoordinates = { (float) (Long) itr1.next(), (float) (Long) itr2.next() };
                finalCoordinates.add(localCoordinates);
                count++;
            }
            return finalCoordinates;

        } catch (FileNotFoundException f) {
            System.out.println("Error: " + f);
        } catch (IOException i) {
            System.out.println("Error: " + i);
        } catch (ParseException p) {
            System.out.println("Error: " + p);
        }

        return null;
    }

    protected ArrayList<Float> setDistances(ArrayList<float[]> coordinates, float centerX, float centerY) {
        ArrayList<Float> local_distances = new ArrayList<>();
        float[] current_coordinates;
        float calculated_distance;
        float y;
        float x;
        for (int i = 0; i < coordinates.size(); i++) {
            current_coordinates = coordinates.get(i);
            x = current_coordinates[0];
            y = current_coordinates[1];
            // We always calculate the distance between (0,0) and the other coordinates
            // so, x1=0, y1=0. The final equation is: sqrt()
            calculated_distance = (float) Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
            local_distances.add(calculated_distance);
        }
        return local_distances;
    }

    protected ArrayList<float[]> setVectors(ArrayList<Float> distances) {
        ArrayList<float[]> vectors = new ArrayList<>();
        float firstDistance;
        float secondDistance;
        int originalDistancesSize;

        originalDistancesSize = distances.size() - 1;

        for (int i = 0; i <= originalDistancesSize; i++) {
            firstDistance = distances.get(i);
            float[] row = new float[originalDistancesSize + 1];

            for (int j = 0; j <= originalDistancesSize; j++) {
                secondDistance = distances.get(j);
                float product = firstDistance * secondDistance;
                row[j] = product;
            }
            vectors.add(row);
        }
        return vectors;
    }

    protected void makeCsv(ArrayList<float[]> valuesList, String fileName) {
        int valuesListSize;
        int currentVectorListSize;
        float[] currentVectorList;
        File csv;

        valuesListSize = valuesList.size() - 1;

        try {
            // Preapring to Write the file
            //This gets the current location of the project using a Canonical Path
            String currentPath = new File(".").getCanonicalPath() + "/data/" + fileName + ".csv";
            csv = new File(currentPath);
            if (!csv.exists())
                csv.createNewFile();
            FileWriter fw = new FileWriter(csv, true);
            // This is better for consecutive writings of small size
            BufferedWriter bw = new BufferedWriter(fw);

            // Write all the data in the csv file
            // Headers
            for (int i = 0; i < valuesListSize; i++) 
            {
                currentVectorList = valuesList.get(i);
                currentVectorListSize = currentVectorList.length - 1;
                for (int j = 0; j < currentVectorListSize; j++) 
                {
                    float value = currentVectorList[j];
                    if(currentVectorListSize == (j+1))
                        bw.write(String.valueOf(value) + "\n");
                    else{
                        bw.write(String.valueOf(value) + ",");
                    }
                }
            }
          bw.close();
            System.out.println("Vectores resultantes escritos en formato CSV"); 
        } catch (IOException e) {
            System.out.println("Un error pasó: " + e);
        }

    }

    // Given a vector, this function returns
    // if it's a Cirle or a Saqure
    public void resolve() {
        float firstDiference;
        float secondDiference;
        float percentageDiference;

        firstDiference = this.vectors.get(0)[1] - this.vectors.get(0)[0];
        secondDiference = this.vectors.get(0)[2] - this.vectors.get(0)[1];

        makeCsv(this.vectors, "vectors");

        percentageDiference = (secondDiference * 100) / firstDiference;

        if(percentageDiference > 101)
            System.out.println("Es un cuadrado");
        else
            System.out.println("Es un circulo");

    }

}