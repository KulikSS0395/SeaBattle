package ds;

import java.util.Scanner;

public class FieldGame {

    private String name;
    private String[][] field = new String[8][8];
    private String[][] fieldAttack = new String[8][8];
    private FieldGame enemy;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field.length; j++) {
                field[i][j] = "⬜";
            }
        }
    }

    {
        for (int i = 0; i < this.fieldAttack.length; i++) {
            for (int j = 0; j < this.fieldAttack.length; j++) {
                fieldAttack[i][j] = "⬜";
            }
        }
    }

    Ship treedeck1 = new Ship(3);

    Ship twodeck1 = new Ship(2);

    Ship onedeck1 = new Ship(1);

    {
        present();

        addShip(treedeck1);
        addShip(twodeck1);
        addShip(onedeck1);


    }

    public void showField() {
        System.out.println("Поле игрока " + name);
        for (int i = 1; i < field.length-1; i++) {
            for (int j = 1; j < field.length-1; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void showFieldAttack() {
        System.out.println("Поле атак игрока " + name);
        for (int i = 1; i < fieldAttack.length-1; i++) {
            for (int j = 1; j < fieldAttack.length-1; j++) {
                System.out.print(fieldAttack[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void present(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя игрока: ");
        name = scanner.nextLine();
        System.out.println("Начем выставлять корабли игрока: " + name);
    }

    public void addShip (Ship ship){
        boolean wrong = true;

        while (wrong) {

            ship.arrangement();

            wrong = false;

            {
                for (int i = 0; i < ship.getXsize(); i++) {
                    if (!field[ship.getingX(i)][ship.getingY(i)].equals("⬜")) {
                        System.out.println("Вы попали в зону другого корабля");
                        wrong = true;
                        ship.clearXY();
                        break;
                    }
                }
            }                                                           // проверка зоны корабля//

            if (wrong){
                continue;
            }
            {
                for (int i=0; i < ship.getXsize(); i++) {
                    field[ship.getingX(i)][ship.getingY(i)] = "\uD83D\uDEA2";
                }

                for (int i = 0; i < ship.getXsize(); i++) {
                    for(int k = ship.getingX(i)-1; k <= ship.getingX(i)+1; k++) {
                        for (int n = ship.getingY(i)-1; n <= ship.getingY(i)+1; n++) {
                            if(field[k][n].equals("⬜")){
                                field[k][n]="⚓";
                            }
                        }
                    }
                }
            }

            wrong = false;

        }
    }

    public boolean attack(FieldGame enemy){

        boolean check = true;

        while (check) {

            showFieldAttack();

            check = false;
            System.out.print("Смотреть свое поле команда f. Введите координаты удара (x,y): ");
            Scanner scanner = new Scanner(System.in);
            String coordinate = scanner.nextLine();

            switch (coordinate) {
                case "f":
                    showField();
                    check = true;
                    continue;

                default:

                    String[] coordinateAttack = coordinate.split(",");
                    if (coordinateAttack.length != 2) {
                        System.out.println("Не правильный ввод координат");
                        check = true;
                        continue;
                    }

                    int x = Integer.parseInt(coordinateAttack[0]);
                    int y = Integer.parseInt(coordinateAttack[1]);

                    if (x < 1 || x > 6 || y < 0 || y > 6) {
                        System.out.println("Введите числа в диапазоне от 1 до 6");
                        check = true;
                        continue;
                    }

                    fieldAttack[x][y] = "🔥";


                    if (enemy.field[x][y].equals("\uD83D\uDEA2")) {
                        enemy.field[x][y] = "🔥";
                        System.out.println("Попал");
                        check = true;

                    } else {
                        System.out.println("Мимо");
                        if (!((enemy.field[x][y].equals("🔥")))) {
                            fieldAttack[x][y] = "❌";
                        }
                        enemy.field[x][y] = "🔥";
                        break;
                    }

            }
        }
            return true;
        }


    public boolean checkField (FieldGame enemy){

        boolean chek = true;

        for (int i = 0; i < enemy.field.length-1; i++){
            for (int j = 0; j < enemy.field.length-1; j++) {
                if (enemy.field[i][j].equals("\uD83D\uDEA2")){
                    chek=false;
                    break;
            }
            }
        }
        return chek;
    }
}
