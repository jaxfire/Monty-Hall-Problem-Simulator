package uk.co.jaxfire;

import java.util.Random;

public class MontyHallSimulation {

    Random random;
    boolean[] doors;

    public MontyHallSimulation(int numberOfGames){

        random = new Random();

        int stickVictories = 0;
        int swapVictories = 0;

        for (int i = 0; i < numberOfGames; i++){
            if (playGame(false)){
                stickVictories++;
            }
            if (playGame(true)){
                swapVictories++;
            }
        }

        //Show results
        String stickVictoriesFormatted = String.format("%,d", stickVictories);
        String swapVictoriesFormatted = String.format("%,d", swapVictories);
        String stickVictoriesPercent = String.format("%.2f", (((float)stickVictories / numberOfGames) * 100));
        String swapVictoriesPercent = String.format("%.2f", (((float)swapVictories / numberOfGames) * 100));
        System.out.println("Stick victories: " + stickVictoriesFormatted + " (" + stickVictoriesPercent + "%)");
        System.out.println("Swap victories: " + swapVictoriesFormatted + " (" + swapVictoriesPercent + "%)");
    }

    private boolean playGame(boolean playerSwaps){

        doors = new boolean[3];

        //Select a random door to be the winning door
        doors[random.nextInt(3)] = true;

        //The player picks a door - a computed random rather than a human guess
        int playerPick = random.nextInt(3);

        //Monty picks a door - Any door that is not either the players chosen door or the winning door
        //NOTE: If the player has picked the winning door then Monty will pick the first losing door that he encounters in the loop...
        //I don't think this has an effect on the outcome but I'm not 100% sure.
        int montyPick = -99;
        for(int i = 0; i < 3; i++){
            if(i != playerPick && !doors[i]){
                montyPick =  i;
            }
        }

        //Return the player's fate if he...
        //swaps
        if (playerSwaps){
            for (int i = 0; i < 3; i++){
                if (i == playerPick || i == montyPick){
                    continue;
                } else {
                    playerPick = i;
                    break;
                }
            }
        }
        // or if he sticks
        return doors[playerPick];
    }

    private int mPick(int pPick){

        int mPick = -99;
        for(int i = 0; i < 3; i++){
            if(i != pPick && !doors[i]){
                mPick =  i;
            }
        }

        return mPick;
    }

    public static void main(String[] args) {

        //The arg is the number of games to simulate
        MontyHallSimulation mhp = new MontyHallSimulation(1000000);

    }

}
