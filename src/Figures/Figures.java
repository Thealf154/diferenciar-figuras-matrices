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
    

    // Variables 
    ArrayList<Integer> coordinates;
    ArrayList<Integer> distances;
    ArrayList<Integer> vectors;

    //This variable corresponds to the coordinates of the equation
    //or the position's data from the JSONs
    ArrayList<Integer> raw_data;

    //Methods
    
    //Trough a mathematical equation, we can get the coordinates
    //For a example we can get a circle with: x² = y
    //The method below takes a value (circle = radious, square = length of a side)
    //and gives an array of coordinates from the equation
    public abstract ArrayList<Integer> MakeCoordinates(int Value);

    //This method take a json file with all the possible coordinates 
    //and makes an array 
    public ArrayList<Integer> ExctractCoordinatesFromData(){
    }

    public int ExtractData(){
        return 0;
    }

    public ArrayList<Integer> GetDistances(ArrayList<Integer> coordinates){
        return distances;
    }
    
    public ArrayList<Integer> GetVectors(ArrayList<Integer> distances){
        return vectors;
    }

    //Given a vector, this function returns 
    //if it's a Cirle or a Saqure
    public void resolve(){

    }

}