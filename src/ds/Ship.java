package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ship {

    private int deck;
    private List<Integer> x = new ArrayList<>();
    private List<Integer> y = new ArrayList<>();

    public Ship(int deck) {
        this.deck = deck;
    }

    public int getDeck() {
        return deck;
    }

    public void setDeck(int deck) {
        this.deck = deck;
    }

    public List<Integer> getX() {
        return x;
    }

    public void setX(List<Integer> x) {
        this.x = x;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> y) {
        this.y = y;
    }

    public int getXsize() {
        return x.size();
    }

    public int getingX(int a) {
        return x.get(a);
    }

    public int getingY(int a) {
        return y.get(a);
    }

    public void clearXY() {
        x.clear();
        y.clear();
    }

    public void arrangement() {

        boolean wrong = true;

        Scanner scanner = new Scanner(System.in);

        while (wrong) {
            wrong = false;
            switch (deck) {
                case 3:
                    System.out.println("Введите координаты трехпалубного корабля");
                    break;
                case 2:
                    System.out.println("Введите координаты двухпалубного корабля");
                    break;
                case 1:
                    System.out.println("Введите координаты однопалубного корабля");
                    break;
            }

            String coordinates = scanner.nextLine();
            String[] line = coordinates.split(";");

            {
                if (line.length != deck) {
                    System.out.println("Некорректный ввод, неправильное кол-во координат");
                    wrong = true;
                    continue;
                }
            }                                                                        // проверка кол-ва координат
            {
                for (int i = 0; i < line.length; i++) {
                    String[] coordinate = line[i].split(",");
                    x.add(Integer.parseInt(coordinate[0]));
                    y.add(Integer.parseInt(coordinate[1]));
                }
            }                                                                       // координаты в Лист

            {
                for (int i = 0; i < x.size(); i++) {
                    if (x.get(i) < 1 || x.get(i) > 6 || y.get(i) < 1 || y.get(i) > 6) {
                        System.out.println("Введите числа в диапазоне от 1 до 6");
                        wrong = true;
                        x.clear();
                        y.clear();
                        break;
                    }
                }

                if (wrong)
                    continue;
            }                                                                       // проверка диапазона чисел

            {
                if (!(Collections.max(x).equals(Collections.min(x)) | Collections.max(y).equals(Collections.min(y)))) {
                    System.out.println("Корабыль должен быть горизонтальным или вертикальным");
                    wrong = true;
                    x.clear();
                    y.clear();
                    continue;
                }                                                                           // проверка гор и верт
            }

            {
                for (int i = 0; i < x.size() - 1; i++) {
                    if (!((Math.abs(x.get(i) - x.get(i + 1)) == 1) | (Math.abs(y.get(i) - y.get(i + 1)) == 1))) {
                        System.out.println("Корабль должен быть целым");
                        wrong = true;
                        x.clear();
                        y.clear();
                        continue;
                    }
                }
            }                                                                              // проверка целостности


        }

    }


    public void shipShow() {
        System.out.println(deck + " " + x.size() + " " + y.size() + " " + x + y);
        System.out.println();
    }
}
