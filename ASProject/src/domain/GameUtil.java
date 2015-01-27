package domain;

import java.util.Random;

public class GameUtil {
	
	public static Strategy getRandomStrategy(){
		Random random = new Random(); 
    	int r = random.nextInt(1);
    	Strategy randomStrategy;
    	if (r==0){
    		randomStrategy = new TimeStrategy(100); //100 secs
    		
    	}
    	else{
    		randomStrategy = new TriesStrategy(20); // 20 tirades max
    		
    	}
    	return randomStrategy;
		
	}
	public static Strategy getRandomStrategy(Game game){
		Random random = new Random(); 
    	int r = random.nextInt(2);

    	System.out.println("Estrategia: " + r);
    	Strategy randomStrategy;
    	if (r==0){
    		randomStrategy = new TimeStrategy(game, 100);
    	}
    	else{
    		randomStrategy = new TriesStrategy(game, 20);
    	}
    	return randomStrategy;
		
	}

}
