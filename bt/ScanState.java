package bt;
import robocode.*;
import java.util.*;

/**
 * MyClass - a class by (your name here)
 */
//shooting nearby enemy if detected
public class ScanState extends State
{
    AdvancedRobot robot;
	List<String> scannedRobots;

    public ScanState (AdvancedRobot robot) {
        this.robot = robot;
    }
	public void Start(){
		
	}
	public void Update(){
		BT_robot.Model.enemiesScanned=0;
		scannedRobots=new ArrayList<String>();
		robot.turnGunRight(360);
		
		
	}
	public void OnHitRobot(HitRobotEvent e){
	
	}
	public void OnRobotScanned(ScannedRobotEvent e){
		//double dist=e.getDistance();
		//System.out.println(dist);
		

		String s=e.getName();
		if(scannedRobots.contains(s)){
    	//true
		}else{
    		// false
			scannedRobots.add(s);
		}
		robot.fire(1);
		
		BT_robot.Model.enemiesScanned=scannedRobots.size();
		//System.out.println(BT_robot.Model.enemyNum);
		
	}
	public void Exit(){
	}
	
	public void OnHitWall(HitWallEvent e)
	{
			
	}
	public void OnHitByBullet(HitByBulletEvent e){
		
	}
}
