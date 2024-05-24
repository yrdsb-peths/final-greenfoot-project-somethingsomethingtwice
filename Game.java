import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main game world. Hosts DeliveryMan, tasks, and obstacles.
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class Game extends World
{
    DeliveryMan deliveryMan;
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 576, 1);
        deliveryMan = new DeliveryMan();
        addObject(deliveryMan, 300, 200);
    }
}
