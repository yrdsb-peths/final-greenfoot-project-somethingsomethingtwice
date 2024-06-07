import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main game world. Hosts DeliveryMan, tasks, and obstacles.
 * Will also act as a sort of camera for the player.
 * 
 * @author Ethan Yan
 * @version May 2024
 */
public class Game extends World
{
    private double cX = 0;
    private double cY = 0;
    private double zoom = 2;
    private DeliveryMan deliveryMan = new DeliveryMan();
    private Label debugLabel = new Label("", 16);
    private GreenfootImage originalBackground;
    private int originalWidth;
    private int originalHeight;
    private GreenfootImage newBackground;
    public Game()
    {    
        super(512, 288, 1, false);
        originalBackground = new GreenfootImage("grass.png");
        originalWidth = originalBackground.getWidth();
        originalHeight = originalBackground.getHeight();
        addObject(deliveryMan, 0, 0);
        addObject(debugLabel, 512 / 2, 24);
    }
    
    public double getCameraX() {return cX;}
    public double getCameraY() {return cY;}
    public double getZoom() {return zoom;}
    
    public void setCamera(double cX, double cY) {
        this.cX = cX;
        this.cY = cY;
        newBackground = new GreenfootImage(100, 100);
        newBackground.setColor(Color.CYAN);
        newBackground.fill();
        int rightX = Math.floorMod((int) (-cX + 0.5), originalWidth);
        int leftX = rightX - originalWidth;
        int bottomY = Math.floorMod((int) (-cY + 0.5), originalHeight);
        int topY = bottomY - originalHeight;
        newBackground.drawImage(originalBackground, leftX, topY);
        newBackground.drawImage(originalBackground, rightX, topY);
        newBackground.drawImage(originalBackground, leftX, bottomY);
        newBackground.drawImage(originalBackground, rightX, bottomY);
        newBackground.scale((int) (originalWidth / zoom + 0.5), (int) (originalHeight / zoom + 0.5));
        setBackground(newBackground);
    }
    
    public void setDebugLabel(String value) {
        debugLabel.setValue(value);
    }
    
    // Utility function from 
    // https://stackoverflow.com/questions/55205437/whats-a-method-that-works-exactly-like-math-floormod-but-with-floats-instead
    private double floatMod(double x, double y){
        // x mod y behaving the same way as Math.floorMod but with doubles
        return (x - Math.floor(x/y) * y);
    }
}
