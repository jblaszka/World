package simulation;

public class World {

    private static final int DAYS_NUMBER = 10;

    public static void main(String[] args) {
        System.out.println("Start");
        for(int i = 0; i < DAYS_NUMBER; i++){
            Simulation.simulateDay();
        }
        System.out.println("Stop");
    }
}
