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
        originalBackground = getBackground();
        originalWidth = originalBackground.getWidth();
        System.out.println(originalWidth);
        originalHeight = originalBackground.getHeight();
        addObject(deliveryMan, 0, 0);
        addObject(debugLabel, 512 / 2, 16);
    }
    
    public double getCameraX() {return cX;}
    public double getCameraY() {return cY;}
    public double getZoom() {return zoom;}
    
    public void setCamera(double cX, double cY) {
        this.cX = cX;
        this.cY = cY;
        //newBackground = new GreenfootImage(originalBackground.getWidth(), originalBackground.getHeight());
        newBackground = new GreenfootImage(512, 288);
        newBackground.setColor(Color.CYAN);
        newBackground.fill();
        //newBackground.scale((int) (originalWidth / zoom + 0.5), (int) (originalHeight / zoom + 0.5));
        newBackground.drawImage(originalBackground, (int) (((-cX % originalWidth) / zoom) / zoom + 0.5), (int) (-cY / zoom + 0.5));
        //newBackground.drawImage(originalBackground, (int) ((cX % originalWidth) / zoom + 0.5), (int) ((-cY % originalHeight) / zoom + 0.5));
        //newBackground.drawImage(originalBackground, (int) ((-cX % originalWidth) / zoom + 0.5), (int) ((cY % originalHeight) / zoom + 0.5));
        //newBackground.drawImage(originalBackground, (int) ((cX % originalWidth) / zoom + 0.5), (int) ((cY % originalHeight) / zoom + 0.5));
        setBackground(newBackground);
    }
    
    public void setDebugLabel(String value) {
        debugLabel.setValue(value);
    }
}
