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
//This is to read the Json
import java.io.FileReader;
import java.io.IOException;

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
        JSONArray posx; 
        JSONArray posy;
        JSONArray centerCoordinates;
        Iterator<?> itr1;
        Iterator<?> itr2;
        Iterator<?> itr3;

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
            this.centerX = (float) (double) centerCoordinates.get(0);
            this.centerY = (float) (double) centerCoordinates.get(1);

            //Loop through all the values
            itr1 = posx.iterator();
            itr2 = posy.iterator();

            while(itr1.hasNext() && itr2.hasNext())
            {
               float [] localCoordinates = {(float) (Long) itr1.next(), (float) (Long) itr2.next()};
               finalCoordinates.add(localCoordinates);
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

    protected ArrayList<Float> setDistances(ArrayList<float[]> coordinates, float centerY, float centerX) {
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
            calculated_distance = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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

    // Given a vector, this function returns
    // if it's a Cirle or a Saqure
    public void resolve() {
        System.out.println(vectors);
    }

}