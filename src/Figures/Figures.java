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

abstract class Figures {

    //======================== Variables ==========================    
    ArrayList<float []> coordinates;
    ArrayList<float []> distances;
    ArrayList<float []> vectors;
    //This variable corresponds to the coordinates of the equation
    //or the position's data from the JSONs
    ArrayList<float []> raw_data;
    //=============================================================    

    //======================== Constructors =======================    
    Figures(int value)
    {
        this.coordinates = setCoordinates(value);
        this.distances = setDistances(coordinates);
        this.vectors = setVectors(distances);
    }

    Figures()
    {
        this.coordinates = setCoordinatesFromData();
        this.distances = setDistances(coordinates);
        this.vectors = setVectors(distances);
    }
    //=============================================================    

    
    //======================== Methods ============================
    protected abstract ArrayList<float []> setCoordinates(int Value);

    //This method take a json file with all the possible coordinates 
    //and makes an array 
    protected ArrayList<float []> setCoordinatesFromData(){
        return null;
    }

    protected int ExtractData(){
        return 0;
    }

    protected ArrayList<float []> setDistances (ArrayList<float []> coordinates){
        return distances;
    }
    
    protected ArrayList<float []> setVectors (ArrayList<float []> distances){
        return vectors;
    }

    //Given a vector, this function returns 
    //if it's a Cirle or a Saqure
    public void resolve(){
       System.out.println(coordinates); 
    }

}