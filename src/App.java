import java.util.Scanner;
import Figures.Square;
import Figures.Circle;

public class App {

    public static void main(String[] args) throws Exception {
        // This code block is to create a Circle or Square
        Scanner entries;
        int desicion;
        entries = new Scanner(System.in);
        System.out.println("Escoge una opción");
        System.out.println("1=Cuadrado, 2=Circulo");
        desicion = entries.nextInt();
        entries.close();

        switch (desicion) {
            case 1:
                Square squareFile = new Square("./data/square.json");
                squareFile.resolve();
                break;
            case 2:
                Circle circleFile = new Circle("./data/circle.json");
                circleFile.resolve();
                break;
        }
    }
}