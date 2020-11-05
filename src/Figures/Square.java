package Figures;

import java.util.ArrayList;

public class Square extends Figures{

    public Square (int side){
        super(side);
    }

    public Square (String fileName){
        super(fileName);
    }

    @Override
    public ArrayList<float []> setCoordinates (int side, float center_x, float center_y) {
        float y;
        ArrayList<float []> coordinates = new ArrayList<>();

        for (int x = 1; x < 5; x++) {
            // This is: y = sqrt(r² - x²)
            y = 0;
            float [] local_coordinates = { x, y };

            // coordinates = [{x, y}, {x, y}]
            coordinates.add(local_coordinates);
        }
        return coordinates;
    }   


}
