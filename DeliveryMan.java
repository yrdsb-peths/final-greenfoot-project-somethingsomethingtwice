import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player Actor. Deliver pizzas and avoid calamity!
 * Physics are based on:
 * https://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class DeliveryMan extends SmoothMover
{
    Vector velocity = new Vector();
    
    double engineForce = 1;
    double brakingForce = 0.8;
    double dragForce = 0.4257;
    double frictionForce = 12.8;
    
    int scaleDown = 2;
    double turnSpd = 3;
    GreenfootImage idleSpr;
    public DeliveryMan() {
        idleSpr = getImage();
        idleSpr.scale(idleSpr.getWidth() / scaleDown, idleSpr.getHeight() / scaleDown);
    }
    
    public void act()
    {
        // ternary operator
        int pedal = (Greenfoot.isKeyDown("up") ? 1 : 0) - (Greenfoot.isKeyDown("down") ? 1 : 0);
        
        System.out.println(velocity.getLength());
        Vector traction = new Vector();
        if (pedal == 1)
            traction.set(engineForce, 0);
        else if (pedal == -1 && velocity.getLength() != 0)
            traction.set(-brakingForce, 0);
        traction.rotate(Math.toRadians(getExactRotation()));
        
        Vector drag = new Vector(velocity.getX(), velocity.getY());
        drag.multiply(-dragForce);
        drag.multiply(velocity.getLength());
        
        Vector rollingResistance = new Vector(velocity.getX(), velocity.getY());
        drag.multiply(-frictionForce);
        
        velocity.set(0, 0);
        velocity.add(traction);
        velocity.add(drag);
        velocity.add(rollingResistance);
        velocity.divide(scaleDown);
 
        attemptExactMove();
    }
    
    // Taken from Hungry Animal, expanded for car physics
    public void attemptExactMove() {
        World world = getWorld();
        double newX = getExactX() + velocity.getX();
        if (newX <= 0) {
            velocity.set(0, velocity.getY());
            setLocation(0, getExactY());
        } else if (newX >= world.getWidth() - 1) {
            velocity.set(0, velocity.getY());
            setLocation(world.getWidth() - 1, getExactY());
        } else
            setLocation(newX, getExactY());
            
        double newY = getExactY() + velocity.getY();
        if (newY <= 0) {
            velocity.set(velocity.getX(), 0);
            setLocation(getExactX(), 0);
        } else if (newY >= world.getHeight() - 1) {
            velocity.set(velocity.getX(), 0);
            setLocation(getExactX(), world.getHeight() - 1);
        } else
            setLocation(getExactX(), newY);
    }
}
