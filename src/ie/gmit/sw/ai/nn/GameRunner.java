package ie.gmit.sw.ai.nn;

import ie.gmit.sw.ai.nn.activator.*;

public class GameRunner{


    private double[][] data = { //Health, Anger, Strength, Defence

        { 2, 2, 2, 0 }, { 1, 2, 2, 0 }, { 0, 2, 2, 0 }, // red
        { 2, 0, 0, 2 }, { 1, 0, 0, 2 }, { 1, 0, 0, 2 }, // green
        { 2, 2, 1, 0 }, { 1, 2, 1, 0 }, { 0, 2, 1, 0 }, // orange
        { 2, 2, 0, 2 }, { 1, 2, 0, 2 }, { 0, 2, 0, 2 }, // Black
        { 2, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 1 }, // Brown
        { 2, 0, 1, 1 }, { 1, 0, 1, 1 }, { 0, 0, 1, 1 }, // Yellow
        { 2, 1, 0, 2 }, { 1, 1, 0, 2 }, { 0, 1, 0, 2 }, // Blue
        { 2, 0, 2, 0 }, { 1, 0, 2, 0 }, { 0, 0, 2, 0 }  // Grey
    };

    private double[][] expected = { //Walk Away, Attack, Build Hedge, Run Away
        {0.0, 1.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0},
        {1.0, 0.0, 0.0, 0.0}, {1.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 1.0},
        {0.0, 1.0, 0.0, 0.0}, {0.0, 1.0, 0.0, 0.0}, {1.0, 0.0, 0.0, 0.0},
        {0.0, 1.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}, {0.0, 0.0, 1.0, 0.0},
        {0.0, 1.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}, {1.0, 0.0, 0.0, 0.0},
        {1.0, 0.0, 0.0, 0.0}, {1.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0},
        {0.0, 1.0, 0.0, 0.0}, {0.0, 0.0, 1.0, 0.0}, {1.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 1.0, 0.0}, {1.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 1.0}
    };


    public void walkAway(){
        System.out.println("Walk Away");
    }

    public void attack(){
        System.out.println("Attack");
    }

    public void buildHedge(){
        System.out.println("Build Hedge");
    }

    public void runAway(){
        System.out.println("Run Away");
    }

    public void action(double health, double anger, double strength, double defence) throws Exception{

        double[] params = {health, anger, strength, defence};

        NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
        Trainator trainer = new BackpropagationTrainer(nn);
        trainer.train(data, expected, 0.01, 100000);

        double[] result = nn.process(params);

        for (double val : result){
            System.out.println(val);
        }

        System.out.println("==>" + (Utils.getMaxIndex(result) + 1));

        int output = (Utils.getMaxIndex(result) + 1);

        switch(output){
            case 1:
                walkAway();
                break;
            case 2:
                attack();
                break;
            case 3:
                buildHedge();
                break;
            case 4:
                runAway();
        }
    }

    public static void main(String[] args) throws Exception{
        double health = Double.parseDouble(args[0]);
        double anger = Double.parseDouble(args[1]);
        double strength = Double.parseDouble(args[2]);
        double defence = Double.parseDouble(args[3]);

        new GameRunner().action(health, anger, strength, defence);
    }
        
}