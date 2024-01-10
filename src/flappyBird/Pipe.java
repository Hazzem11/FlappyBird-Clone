/**
 * This program creates a Pipe object which is used in GameGUI as the
 * moving pipes which are trying to hit the player and make them lose the game.
 */
package flappyBird;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class Pipe {

    private Image topPipePic = new ImageIcon(getClass().getResource("topPipe.png")).getImage();
    private Image bottomPipePic = new ImageIcon(getClass().getResource("bottomPipe.png")).getImage();
    private int x;
    private int y;

    private int width;
    private int height;

    /**
     * This is the main constructor.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Pipe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * This method displays the pipe images at specific places.
     *
     * @param g
     * @param pipes
     */
    public void drawPipe(Graphics g, ArrayList<Rectangle> pipes) {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
//            if (!gameOver) {
            if (pipe.y == 0) {
                g.drawImage(topPipePic,
                        pipe.x, pipe.y, pipe.width, pipe.height, null);
            } else {
                g.drawImage(bottomPipePic,
                        pipe.x, pipe.y, pipe.width, pipe.height, null);
            }
//            }
        }
    }

}
