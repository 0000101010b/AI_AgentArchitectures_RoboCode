package bt;
import robocode.*;

/**
 * MyClass - a class by (your name here)
 */
//shooting nearby enemy if detected
public class CrazyState extends State
{
    AdvancedRobot robot;
	boolean movingForward=false;

    public CrazyState (AdvancedRobot robot) {
        this.robot = robot;
    }
	public void Start(){
		
	}
	public void Update(){
			// Tell the game we will want to move ahead 40000 -- some large number
			robot.setAhead(40000);
			movingForward = true;
			// Tell the game we will want to turn right 90
			robot.setTurnRight(90);
			// At this point, we have indicated to the game that *when we do something*,
			// we will want to move ahead and turn right.  That's what "set" means.
			// It is important to realize we have not done anything yet!
			// In order to actually move, we'll want to call a method that
			// takes real time, such as waitFor.
			// waitFor actually starts the action -- we start moving and turning.
			// It will not return until we have finished turning.
			robot.waitFor(new TurnCompleteCondition(robot));
			// Note:  We are still moving ahead now, but the turn is complete.
			// Now we'll turn the other way...
			robot.setTurnLeft(180);
			// ... and wait for the turn to finish ...
			robot.waitFor(new TurnCompleteCondition(robot));
			// ... then the other way ...
			robot.setTurnRight(180);
			// .. and wait for that turn to finish.
			robot.waitFor(new TurnCompleteCondition(robot));
		
		
	}
	/**
	 * reverseDirection:  Switch from ahead to back & vice versa
	 */
	public void reverseDirection() {
		if (movingForward) {
			robot.setBack(40000);
			movingForward = false;
		} else {
			robot.setAhead(40000);
			movingForward = true;
		}
	}
	public void OnRobotScanned(ScannedRobotEvent e){
			robot.fire(1);
	}
	public void Exit(){
	}
	
	public void OnHitWall(HitWallEvent e)
	{
			
	}
	public void OnHitByBullet(HitByBulletEvent e){
		
	}
	/**
	 * onHitRobot:  Back up!
	 */
	public void OnHitRobot(HitRobotEvent e) {
		// If we're moving the other robot, reverse!
		if (e.isMyFault()) {
			reverseDirection();
		}
	}
}
