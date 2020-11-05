package Figures;

import java.util.ArrayList;

public class Circle extends Figures {

    public Circle(int value) {
        super(value);
    }

    public Circle(String fileName){
        super(fileName);
    }
    

    //Trough a mathematical equation, we can get the coordinates
    //For a example we can get a circle with: x² = y
    //The method below takes a value (circle = radious, 
    // square = length of a side)
    //and gives an array of coordinates from the equation
    @Override
    public ArrayList<float []> setCoordinates(int radius, float center_x, float center_y) {
        float y;
        ArrayList<float []> coordinates = new ArrayList<>();

        for (int x = 1; x < 5; x++) {
            // This is: y = sqrt(r² - x²)
            y = (float) Math.sqrt(Math.pow(radius, 2) - Math.pow(x, 2));
            float [] local_coordinates = { x, y };

            // coordinates = [{x, y}, {x, y}]
            coordinates.add(local_coordinates);
        }
        return coordinates;
    }
}