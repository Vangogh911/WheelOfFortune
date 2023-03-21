import java.util.ArrayList;
import java.util.Random;

class Toy {
    public static int number;
    private int id;         //id игрушки
    private String name;    //текстовое название
    private int count;      //количество
    private int weight;     //частота выпадения игрушки
    int maxLimit = 0;
    int minLimit = 0;

    Toy(String name, int count, int weight){
        this.id = number;
        this.name = name;
        this.count = count;
        this.weight = weight;
        number++;
    }

    public int getId(){
        return this.id;
    }

    public int getCount(){
        return this.count;
    }

    public void decCount(){
        this.count--;
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int newWeight){ //метод для установки веса
        if (newWeight > 0) {
            this.weight = newWeight;
        }
        else {
            System.out.println("Неверный вес");
        }
    }

    public String getName(){
        return this.name;
    }

}

class WheelOfFortune {
    ArrayList<Toy> toys = new ArrayList<>();

    public void addToy(Toy newToy){ //Метод для добавления новых предметов в розыгрыш
        toys.add(newToy);
    }

    public void runTheWheel(){
        //Не стал делать вероятность выпадения приза в процентном соотношении.
        //Сделал расчет вероятности выпадения относительно суммарного веса предметов в розыгрыше.
        int sumWeight = 0;
        for(int i = 0; i < toys.size(); i++){
            toys.get(i).minLimit = sumWeight;
            sumWeight += toys.get(i).getWeight();
            toys.get(i).maxLimit = sumWeight;
            sumWeight++;
        }

        Random rnd = new Random();
        int luckyNumber = rnd.nextInt(sumWeight);

        for(int i = 0; i < toys.size(); i++){
            if(toys.get(i).minLimit < luckyNumber && luckyNumber < toys.get(i).maxLimit){
                System.out.println("Random number is " + luckyNumber + ". Prize is " + toys.get(i).getName());
                toys.get(i).decCount();
                if(toys.get(i).getCount() <= 0){ //Если предметы кончились, то удаляем их из списка розыгрыша.
                    toys.remove(i);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Toy toy1 = new Toy("RC plane", 1, 20);
        System.out.println("id = " + toy1.getId());
        Toy toy2 = new Toy("RC car", 4, 30);
        System.out.println("id = " + toy2.getId());
        Toy toy3 = new Toy("car", 10, 50);
        System.out.println("id = " + toy3.getId());

        WheelOfFortune wheel = new WheelOfFortune();
        wheel.addToy(toy1);
        wheel.addToy(toy2);
        wheel.addToy(toy3);

        wheel.runTheWheel();
        wheel.runTheWheel();
        wheel.runTheWheel();
        wheel.runTheWheel();
        wheel.runTheWheel();
        wheel.runTheWheel();
        wheel.runTheWheel();
    }
}
