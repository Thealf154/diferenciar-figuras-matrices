package Figures;

public class Square extends Figures{
    private int side;

    public Square(){
        this.coordinates = ExctractCoordinatesFromData();
        this.distances = GetDistances(coordinates);
        this.vectors = GetVectors(distances);
    }

    public Square(int side){
        this.side = side;
        this.coordinates = MakeCoordinates(side);
        this.distances = GetDistances(coordinates);
        this.vectors = GetVectors(distances);
    }

    @Override
    public ArrayList<Integer> MakeCoordinates(int side) {
        return null;
    }   
}
