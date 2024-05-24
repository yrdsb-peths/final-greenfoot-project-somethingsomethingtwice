import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player Actor. Deliver pizzas and avoid calamity!
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class DeliveryMan extends SmoothMover
{
    double vx = 0;
    double vy = 0;
    double friction = 0.1;
    public void act()
    {
        // ternary operator
        double accel = ((Greenfoot.isKeyDown("up") ? 1 : 0) - (Greenfoot.isKeyDown("down") ? 1 : 0)) * 0.2;
        turn(((Greenfoot.isKeyDown("right") ? 1 : 0) - (Greenfoot.isKeyDown("left") ? 1 : 0)) * 3);
        
        double radians = Math.toRadians(getExactRotation());
        System.out.println(getExactX());
        vx += Math.cos(radians) * accel;
        vy += Math.sin(radians) * accel;
        if (vx != 0)
            vx = vx > 0 ? Math.max(vx - friction, 0) : Math.min(vx + friction, 0);
        if (vy != 0)
            vy = vy > 0 ? Math.max(vy - friction, 0) : Math.min(vy + friction, 0);
        attemptExactMove();
    }
    
    // Taken from Hungry Animal, expanded for car physics
    public void attemptExactMove() {
        World world = getWorld();
        double newX = getExactX() + vx;
        if (newX <= 0) {
            vx = 0;
            setLocation(0, getExactY());
        } else if (newX >= world.getWidth() - 1) {
            vx = 0;
            setLocation(world.getWidth() - 1, getExactY());
        } else
            setLocation(newX, getExactY());
            
        double newY = getExactY() + vy;
        if (newY <= 0) {
            vy = 0;
            setLocation(getExactX(), 0);
        } else if (newY >= world.getHeight() - 1) {
            vy = 0;
            setLocation(getExactX(), world.getHeight() - 1);
        } else
            setLocation(getExactX(), newY);
    }
}
