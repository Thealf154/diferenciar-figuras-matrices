import java.util.Scanner;
import Figures.Square;
import Figures.Circle;

public class App {

    public static void main(String[] args) throws Exception {
        //This code block is to create a Circle or Square
        Scanner entries;
        int desicion;
            entries = new Scanner (System.in);
            System.out.println("Escoge una opción");
            System.out.println("1=Cuadrado, 2=Circulo");
            desicion = entries.nextInt();
            entries.close();

        switch(desicion)
        {
            case 1:
                Square square = new Square(5);
                square.resolve();
                break;
            case 2:
                Circle circle = new Circle(5);
                circle.resolve();
                break;
        }
    }
}