package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This program creates a Flappy Bird clone but slightly modified. It allows the
 * user control a bird that can jump using the space bar while pipes are
 * constantly appearing trying to stop the bird from going ahead. The objective
 * of the game is to get the bird through as many pipes as possible. Your score
 * will be the number of pipes you are able to pass through.
 */
/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class GameGUI extends JFrame implements ActionListener, KeyListener {

    private static final int JFRAME_WIDTH = 1000;
    private static final int JFRAME_HEIGHT = 800;
    private static final Color BKG_COLOR = new Color(133, 235, 255);

    private PanelUpdater panelUpdater;
    private Random random;

    private int gravity;

    /**
     * This is the main game constructor.
     */
    public GameGUI() {

        panelUpdater = new PanelUpdater();
        Timer timer = new Timer(20, this);
        random = new Random();
        settingJframe();
        panelUpdater.addStartPipes();
        timer.start();
    }

    /**
     * This method is used in the main game constructor to set up the jFrame by
     * adding all necessary components to our jFrame.
     */
    public void settingJframe() {
        add(panelUpdater);
        setTitle("Flappy Bird Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        addKeyListener(this);
        setResizable(false);
        setVisible(true);
    }

    /**
     * This method allows the bird to jump upwards in mid air while gravity is
     * pulling it downwards.
     */
    public void jump() {
        if (panelUpdater.isGameOver()) {
            restartGame();
        } else {
            panelUpdater.addStartPipes();
        }

        if (!panelUpdater.isPlaying()) {
            panelUpdater.setPlaying(true);
        } else if (!panelUpdater.isGameOver()) {
            if (gravity >= 0) {
                gravity = 0;
            }
            gravity -= 11.5;

        }
    }

    public void restartGame() {
        panelUpdater.resetPipes();
        gravity = 0;
        panelUpdater.setScore(0);
        panelUpdater.setGameOver(false);
    }

    /**
     * This method controls the gravity in the game.
     */
    public void setGravity() {
        if (gravity < 15) {
            gravity += 1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (panelUpdater.isPlaying()) {
            panelUpdater.pipeSpeed();
            setGravity();
            panelUpdater.updatePipes();
            panelUpdater.updateBirdLocation(gravity);
            panelUpdater.updateScore();
            boolean hitPipe = panelUpdater.resetBird();
            boolean outofBound = panelUpdater.outOfBounds();

            if (hitPipe || outofBound) {
                panelUpdater.setGameOver(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump(); // if space bar is pressed, allow the bird to jump.
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
