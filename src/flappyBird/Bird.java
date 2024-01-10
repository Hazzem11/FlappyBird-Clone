package flappyBird;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * This program creates a Bird object which is used in GameGUI as the player.
 */
/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class Bird extends Rectangle {

    private Image birdPic = new ImageIcon(getClass().getResource("bird.png")).getImage();

    /**
     * This is the main constructor.
     *
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     */
    public Bird(int xPosition, int yPosition, int width, int height) {

        this.x = xPosition;
        this.y = yPosition;
        this.width = width;
        this.height = height;

    }

    /**
     * This is the parameterless constructor.
     */
    public Bird() {
        x = 250;
        y = 350;

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method displays the bird picture on the frame.
     *
     * @param g
     */
    public void drawBird(Graphics g) {
        g.drawImage(birdPic,
                (int) x,
                (int) y,
                birdPic.getWidth(null),
                birdPic.getHeight(null), null);
    }
}
