import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objects created specifically for the Game world, which allows for zoom and
 * faux camera effects
 * 
 * @author Ethan Yan 
 * @version May 2024
 */
public class GameObject extends SmoothMover
{
    private GreenfootImage originalImage;
    private GreenfootImage newImage;
    private int originalWidth;
    private int originalHeight;
    private double originalX;
    private double originalY;
    private Game gameWorld;
    public GameObject() {
        originalImage = getImage();
        originalWidth = originalImage.getWidth();
        originalHeight = originalImage.getHeight();
    }  
    public void addedToWorld(World world) {
        gameWorld = (Game) world;
        originalX = getExactX();
        originalY = getExactY();
    }
    public void resetCamera()
    {
        newImage = new GreenfootImage(originalImage);
        setImage(newImage);
        setLocation(originalX, originalY);
    }
    public void camera() {
        originalX = getExactX();
        originalY = getExactY();
        newImage.scale((int) (originalWidth / gameWorld.getZoom() + 0.5), (int) (originalHeight / gameWorld.getZoom() + 0.5));
        setLocation((originalX - gameWorld.getCameraX()) / gameWorld.getZoom(), (originalY - gameWorld.getCameraY()) / gameWorld.getZoom());
    }
}
