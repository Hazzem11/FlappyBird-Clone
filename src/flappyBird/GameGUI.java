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

//    public static GameGUI flappyBird;

    private static final int JFRAME_WIDTH = 1000;
    private static final int JFRAME_HEIGHT = 800;

//    private static final int BIRD_START_X = JFRAME_WIDTH / 2;
//    private static final int BIRD_START_Y = JFRAME_HEIGHT / 2;
//    private static final int BIRD_END_Y = JFRAME_HEIGHT - 120;

    private static final Color BKG_COLOR = new Color(133, 235, 255);

//    private boolean gameOver;
//    private boolean playing;

    private PanelUpdater panelUpdater;
//    private Bird bird;
//    private Pipe pipe;
    private Random random;
//    public ArrayList<Rectangle> pipes;

//    private int taps; // value for the number of times the space bar is pressed
    private int gravity;
//    private int score;

    /**
     * This is the main game constructor.
     */
    public GameGUI() {

        panelUpdater = new PanelUpdater();
        Timer timer = new Timer(20, this);
        random = new Random();
//        bird = new Bird(BIRD_START_X, BIRD_START_Y, 20, 20);
//        pipe = new Pipe(450, JFRAME_HEIGHT, 20, 200);
//        pipes = new ArrayList<Rectangle>();

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



//    /**
//     * This method displays all the graphics and visuals of the game.
//     *
//     * @param g
//     */
//    public void repaint(Graphics g) {
//
////        drawBackground(g);
////        bird.drawBird(g);
////        if (!gameOver) {
////            pipe.drawPipe(g, pipes);
////        }
//        drawStart(g);
//        drawGameOver(g);
//        drawScore(g);
//    }

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
            panelUpdater.setPlaying( true);
        } else if (!panelUpdater.isGameOver()) {
            if (gravity >= 0) {
                gravity = 0;
            }
            gravity -= 11.5;

        }
    }

//    /**
//     * This method adds an invisible Rectangle called pipe of any size and at
//     * any position.
//     *
//     * @param xPosition
//     * @param yPosition
//     * @param width
//     * @param height
//     */
//    private void addPipe(int xPosition, int yPosition, int width, int height) {
//        Rectangle pipe;
//        pipe = new Rectangle(xPosition, yPosition, width, height);
//        pipes.add(pipe);
//    }
//
//    /**
//     * This method adds an invisible Rectangle called pipe throughout the game
//     * at specific places and in specific sizes.
//     */
//    public void addPipes() {
//
//        int gap = 280;
//        int width = 100;
//        int height;
//        int xPosition;
//        int yPosition;
//
//        height = 60 + random.nextInt(300);
//        xPosition = pipes.get(pipes.size() - 1).x + 600;
//        yPosition = JFRAME_HEIGHT - height - 120;
//        addPipe(xPosition, yPosition, width, height);
//
//        yPosition = 0;
//        xPosition = pipes.get(pipes.size() - 1).x;
//        height = JFRAME_HEIGHT - height - gap;
//        addPipe(xPosition, yPosition, width, height);
//    }
//
//    /**
//     * This method adds an invisible Rectangle called pipe at the start of the
//     * game at specific places and in specific sizes.
//     */
//    public void addStartPipes() {
//
//        int gap = 280;
//        int width = 100;
//        int height;
//        int xPosition;
//        int yPosition;
//        Rectangle pipe;
//
//        for (int i = 0; i < 4; i++) {
//
//            height = 60 + random.nextInt(300);
//            xPosition = JFRAME_WIDTH + width + pipes.size() * 300;
//            yPosition = JFRAME_HEIGHT - height - 120;
//            addPipe(xPosition, yPosition, width, height);
//
//            yPosition = 0;
//            xPosition = JFRAME_WIDTH + width + (pipes.size() - 1) * 300;
//            height = JFRAME_HEIGHT - height - gap;
//            addPipe(xPosition, yPosition, width, height);
//        }
//    }

    /**
     * This method allows the game to restart if the bird has collided with a
     * pipe, This is called when the game is over from the press of the space
     * bar.
     */
    public void restartGame() {
//        bird = new Bird(BIRD_START_X, BIRD_START_Y, 20, 20);
        panelUpdater.resetPipes(); // pipes.clear();
        gravity = 0;
//        score = 0;
        panelUpdater.setScore(0);
        panelUpdater.setGameOver(false);
    }

//    /**
//     * This method is called when the bird goes out of the bounds of the game,
//     * either being too high or to low.
//     */
//    public void outOfBounds() {
//        if (bird.getY() > BIRD_END_Y || bird.getY() < 0) {
//            gameOver = true;
//            bird.setX(480);
//            bird.setY(BIRD_END_Y);
//        }
//    }

//    /**
//     * This method places the bird in a stationary position at the bottom of the
//     * screen when it hits a pipe to indicate that the game is over.
//     */
//    public void resetBird() {
//        for (int i = 0; i < pipes.size(); i++) {
//            Rectangle pipe = pipes.get(i);
//            if (pipe.intersects(bird)) {
//                gameOver = true;
//                if (bird.getX() <= pipe.x) {
//                    bird.setX(480);
//                    bird.setY(BIRD_END_Y);
//                }
//            }
//        }
//    }

//    /**
//     * This method keeps track and counts the players score throughout the game.
//     */
//    public void updateScore() {
//        for (int i = 0; i < pipes.size(); i++) {
//            Rectangle pipe = pipes.get(i);
//            if (pipe.y == 0 && bird.getX() + bird.getWidth() / 2 > pipe.x + pipe.width / 2 - 10
//                    && bird.getX() + bird.getWidth() / 2 < pipe.getX() + pipe.width / 2 + 10) {
//
//                score += 1;
//            }
//        }
//    }
//
//    /**
//     * This method uses the addPipes() method and puts the pipes on the jFrame.
//     */
//    public void updatePipes() {
//        for (int i = 0; i < pipes.size(); i++) {
//            Rectangle pipe = pipes.get(i);
//
//            if (pipe.x + pipe.width < 0) {
//                pipes.remove(pipe);
//
//                if (pipe.y == 0) {
//                    addPipes();
//                }
//            }
//        }
//    }

    /**
     * This method controls the gravity in the game.
     */
    public void setGravity() {
        if (gravity < 15) {
            gravity += 1;
        }
    }

//    /**
//     * This method regulates the speed in which the pipes move, making the game
//     * either a fast pace game making it harder or a slow pace game which would
//     * be easier.
//     */
//    public void pipeSpeed() {
//        for (int i = 0; i < pipes.size(); i++) {
//            Rectangle pipe = pipes.get(i);
//
//            pipe.x -= 10; //controls how fats the pipes move left.
//        }
//    }

//    /**
//     * This method displays the current score, throughout the game.
//     *
//     * @param g
//     */
//    public void drawScore(Graphics g) {
//        if (!gameOver && playing) {
//            g.setFont(new Font("Times New Roman", 1, 75));
//            g.drawString(String.valueOf(score), JFRAME_WIDTH / 2 - 25, 100);
//        }
//    }

//    /**
//     * This method displays the background of the game.
//     *
//     * @param g
//     */
//    public void drawBackground(Graphics g) {
//        g.setColor(BKG_COLOR);
//        g.fillRect(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
//        g.setColor(Color.GREEN.darker());
//        g.fillRect(0, BIRD_END_Y, JFRAME_WIDTH, 140);
//
//    }

//    /**
//     * This method displays the starting message.
//     *
//     * @param g
//     */
//    public void drawStart(Graphics g) {
//        g.setColor(Color.white);
//
//        if (!playing) {
//            g.setFont(new Font("Times New Roman", 1, 100));
//            g.drawString("Click to Play!", 100, JFRAME_WIDTH / 2 - 150);
//        }
//    }

//    /**
//     * This method displays the game over message.
//     *
//     * @param g
//     */
//    public void drawGameOver(Graphics g) {
//        if (gameOver) {
//            g.setFont(new Font("Times New Roman", 1, 100));
//            g.drawString("Game Over!", 200, JFRAME_HEIGHT / 2 - 100);
//            g.setFont(new Font("Times New Roman", 1, 50));
//            g.drawString("PRESS to play again", 250, JFRAME_HEIGHT / 2 + 50);
//
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (panelUpdater.isPlaying()) {
            panelUpdater.pipeSpeed();
            setGravity();
            panelUpdater.updatePipes();

//            bird.setY((int) bird.getY() + gravity);
            panelUpdater.updateBirdLocation(gravity);
            panelUpdater.updateScore();
            boolean hitPipe = panelUpdater.resetBird();
            boolean outofBound = panelUpdater.outOfBounds();

            if (hitPipe || outofBound){
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
