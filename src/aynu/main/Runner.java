package aynu.main;

import aynu.entity.Building;
import aynu.entity.Elevator;
import aynu.entity.People;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        run();
    }

    private static void run() throws InterruptedException {
        Elevator      elevator   = new Elevator();
        List<People>  peoples    = new ArrayList<>();
        List<Integer> upFloors   = new ArrayList<>();
        List<Integer> downFloors = new ArrayList<>();
        elevator.setPeoples(peoples);
        while (true){
            setPeople(peoples);
            System.out.println("该楼最高"+Building.TOP_FLOOR+"层，最低为"+ Building.BOTTOM_FLOOR+"层");
            setFloors(upFloors, downFloors);
            System.out.println("upFloors:"+upFloors+"\tdownFloors:"+downFloors);
            elevatorRun(upFloors, downFloors);
        }
    }
    private static void elevatorRun(List<Integer> upFloors, List<Integer> downFloors) throws InterruptedException {
        while(!upFloors.isEmpty()){
            Elevator.floor++;
            arrive(upFloors);
            if (upFloors.isEmpty()){
                setFloors(upFloors, downFloors);
            }
        }
        while(!downFloors.isEmpty()){
            Elevator.floor--;
            arrive(downFloors);
            if (downFloors.isEmpty()){
                setFloors(upFloors, downFloors);
            }
        }
    }
    private static void arrive(List<Integer> Floors) throws InterruptedException {
        if (Floors.contains(Elevator.floor)){
            System.err.println("---到达"+Elevator.floor+"楼层---");
            Floors.removeAll(Arrays.asList(Elevator.floor));
        }else {
            System.out.println("-------"+Elevator.floor+"-------");
        }
        TimeUnit.SECONDS.sleep(1);
    }
    private static void setFloors(List<Integer> upFloors, List<Integer> downFloors) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("当前楼层："+ Elevator.floor +"，请输入您要去的楼层：");
        while (scan.hasNext()){
            String next = scan.next();
            try {
                Integer floor = Integer.valueOf(next);
                if (floor >= Building.BOTTOM_FLOOR && floor <= Building.TOP_FLOOR){
                    if (floor > Elevator.floor){
                        upFloors.add(floor);
                    }else if (floor < Elevator.floor){
                        downFloors.add(floor);
                    }
                }else {
                    Collections.sort(upFloors);
                    Collections.sort(downFloors);
                    break;
                }
            }catch (RuntimeException e){
                System.err.println("请输入正确格式的数字!");
                setFloors(upFloors, downFloors);
                break;
            }
        }
        elevatorRun(upFloors, downFloors);
    }
    private static void setPeople(List<People> peoples){
        Random random = new Random();
        for (int i=0; i<random.nextInt(12); i++){
            People p = new People();
            peoples.add(p);
            Elevator.allWeight += p.getWeight();
        }
        while(Elevator.allWeight > Elevator.CAPACITY){
            People p = peoples.get(peoples.size()-1);
            peoples.remove(p);
            Elevator.allWeight -= p.getWeight();
            System.out.println("因人数过多，下了一个人");
        }
        System.out.println("人进入电梯完毕，总重量为："+Elevator.allWeight+",可以运行");
    }
}