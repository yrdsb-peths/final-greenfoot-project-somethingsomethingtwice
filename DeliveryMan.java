import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player Actor. Deliver pizzas and avoid calamity!
 * Physics are based on:
 * https://www.asawicki.info/Mirror/Car%20Physics%20for%20Games/Car%20Physics%20for%20Games.html
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class DeliveryMan extends GameObject
{
    Vector velocity = new Vector();
    
    double engineForce = 64;
    double brakingForce = 48;
    double dragForce = 0.4257;
    double frictionForce = 12.8;
    double mass = 1900;
    
    double turnForce = 3;
    double orientation = 0;
    public void act()
    {
        resetCamera();
        // ternary operator
        int pedal = (Greenfoot.isKeyDown("up") ? 1 : 0) - (Greenfoot.isKeyDown("down") ? 1 : 0);
        double steeringAngle = (Greenfoot.isKeyDown("right") ? 1 : 0) - (Greenfoot.isKeyDown("left") ? 1 : 0);
        steeringAngle *= Math.toRadians(40);
        turn(Math.toDegrees(velocity.getLength() / (1.0 / Math.sin(steeringAngle))));
        
        Vector force = new Vector();
        Vector traction = new Vector();
        if (pedal == 1)
            traction.set(engineForce, 0);
        else if (pedal == -1)
            traction.set(-brakingForce, 0);
        traction.rotate(Math.toRadians(getExactRotation()));
        
        Vector drag = new Vector(velocity.getX(), velocity.getY());
        drag.multiply(-dragForce);
        drag.multiply(velocity.getLength());
        
        Vector rollingResistance = new Vector(velocity.getX(), velocity.getY());
        rollingResistance.multiply(-frictionForce);
        
        force.add(traction);
        force.add(drag);
        force.add(rollingResistance);
        force.divide(mass);
        
        if (pedal == -1 && force.getLength() > velocity.getLength())
            velocity.set(0, 0);
        else
            velocity.add(force);
 
        attemptExactMove();
        Game game = (Game) getWorld();
        game.setCamera(getExactX() - (game.getWidth() * game.getZoom()) / 2, getExactY() - (game.getHeight() * game.getZoom()) / 2);
        game.setDebugLabel("Velocity: " + velocity + "\n" + "Position: " + getExactX() + ", " + getExactY());
        camera();
    }
    
    // Taken from Hungry Animal, expanded for car physics
    public void attemptExactMove() {
        setLocation(getExactX() + velocity.getX(), getExactY() + velocity.getY());
        //World world = getWorld();
        //double newX = getExactX() + velocity.getX();
        //if (newX <= 0) {
        //    velocity.set(0, velocity.getY());
        //    setLocation(0, getExactY());
        //} else if (newX >= world.getWidth() - 1) {
        //    velocity.set(0, velocity.getY());
        //    setLocation(world.getWidth() - 1, getExactY());
        //} else
        //    setLocation(newX, getExactY());
            
        //double newY = getExactY() + velocity.getY();
        //if (newY <= 0) {
        //    velocity.set(velocity.getX(), 0);
        //    setLocation(getExactX(), 0);
        //} else if (newY >= world.getHeight() - 1) {
        //    velocity.set(velocity.getX(), 0);
        //    setLocation(getExactX(), world.getHeight() - 1);
        //} else
         //   setLocation(getExactX(), newY);
    }
}
