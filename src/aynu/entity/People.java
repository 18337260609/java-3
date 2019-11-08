package aynu.entity;

import java.util.Random;

public class People {

    private Double weight;

    public People() {
        //随机给人赋值重量，范围：30~150
        Random random = new Random();
        double w = random.nextInt(120)+30d;
        this.weight = w;
    }

    public People(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "People{" +
                "weight=" + weight +
                '}';
    }
}
