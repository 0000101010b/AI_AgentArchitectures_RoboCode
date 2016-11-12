package bt;
import robocode.*;
/**
 * Abstract Behaviour class -a class by Ben Thompson
 * Behaviours for reactive architecture
 */
abstract public class State
{
	abstract public void Start();
	abstract public void Update();
	abstract public void Exit();
	abstract public void OnRobotScanned(ScannedRobotEvent e);
	abstract public void OnHitWall(HitWallEvent e);
	abstract public void OnHitByBullet(HitByBulletEvent e);
	abstract public void OnHitRobot(HitRobotEvent e);
}
