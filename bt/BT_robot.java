package bt;
import robocode.*;
import java.awt.Color;
import java.util.*;


/*
 * State based function use code found in sample robot for wall and crazy
 * my work consists of...
 * -ScanState
 * -MainRobot class
 * -abstract State class
 * -alterations were made to use crazy and wall states
 */

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * BT_robot - a robot by Ben Thompson
 **/

public class BT_robot extends AdvancedRobot
{
    int count=0;
	public List<State> states;
	public State currentState;//current state class
	public States current=States.Scan;//current state enum
	
	/**
	 * run: BT_robot's behavior
	 */
	public void run() {
		// Initialization of the robot should be put here
		//initializing symbolic view of the world
		states= new ArrayList<State>();
		states.add(new ScanState(this));
		states.add(new CrazyState(this));
		states.add(new WallState(this));
		currentState=states.get(current.getValue());
		System.out.println(current.getValue());
		currentState.Start();

		

		 setScanColor(Color.blue); 
		 setColors(Color.red,Color.red,Color.red); 

		// Robot main loop
		while(true) {
		   /* 
			* World Model
			* */
			//BT agent
			Model.pos=new Vector2D(getX(),getY());
			//Environment
			//etc...
			
			//current state update function
			currentState.Update();
			

			if(BT_robot.Model.enemiesScanned<3&&current==States.Scan)
			{
				//crazy
				current=States.Wall;
				currentState=states.get(current.getValue());
				currentState.Start();
			}else {
				//wall
				current=States.Crazy;
				currentState=states.get(current.getValue());
				currentState.Start();
			}
			
			count++;

			if(count>10)
			{
				//scan again
				current=States.Scan;
				currentState=states.get(current.getValue());
				currentState.Start();
				count=0;
			}
			
			//System.out.println(Model.robotScanned);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		currentState.OnRobotScanned(e);
		//	double dist=e.getDistance();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		currentState.OnHitByBullet(e);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		currentState.OnHitWall(e);
	}
	public void onHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		currentState.OnHitRobot(e);
	}	
	
	/*
	 * static nested class for storing symbolic representation of the world
	 * */
	public static class Model
	{
		public static Vector2D pos;
		public static int enemiesScanned=0;
	}
	/*
	 * states in order of 
	 */
	public enum States {
  	  Scan(0), Crazy(1), Wall(2);
	
	    private final int value;
	    private States(int value) {
	        this.value = value;
	    }
	
	    public int getValue() {
	        return value;
	    }
	}

}
	






