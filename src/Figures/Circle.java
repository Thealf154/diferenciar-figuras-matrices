package Figures;

import java.util.ArrayList;

public class Circle extends Figures {
    private int radius;

    public Circle(){
        this.coordinates = ExctractCoordinatesFromData();
        this.distances = GetDistances(coordinates);
        this.vectors = GetVectors(distances);
    }

    public Circle(int radius){
        this.radius = radius;
        this.coordinates = MakeCoordinates(radius);
        this.distances = GetDistances(coordinates);
        this.vectors = GetVectors(distances);
    }

    @Override
    public ArrayList<Integer> MakeCoordinates(int radius) {
        return null;
    }

}