/**
 * This program is a ever updating JPanel that is used for graphics and animation
 * in the GameGUI for the Flappy Bird Game project.
 */
package flappyBird;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class PanelUpdater extends JPanel {

    private static final int JFRAME_WIDTH = 1000;
    private static final int JFRAME_HEIGHT = 800;

    private static final int BIRD_START_X = JFRAME_WIDTH / 2;
    private static final int BIRD_START_Y = JFRAME_HEIGHT / 2;
    private static final int BIRD_END_Y = JFRAME_HEIGHT - 120;
    private static final Color BKG_COLOR = new Color(133, 235, 255);

    private Bird bird;
    private Pipe pipe;
    private ArrayList<Rectangle> pipes;
    private Random random;
    private boolean gameOver;

    private boolean playing;
    private int score;


    public PanelUpdater() {

        bird = new Bird(BIRD_START_X, BIRD_START_Y, 20, 20);
        pipe = new Pipe(450, JFRAME_HEIGHT, 20, 200);
        pipes = new ArrayList<Rectangle>();
        random = new Random();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if (GameGUI.flappyBird != null) {
//            GameGUI.flappyBird.repaint(g);
//        }
        repaint(g);

        repaint(); // allows the graphics constantly rendering.
    }

    /**
     * This method displays all the graphics and visuals of the game.
     *
     * @param g
     */
    public void repaint(Graphics g) {

        drawBackground(g);
        bird.drawBird(g);
        if (!gameOver) {
            pipe.drawPipe(g, pipes);
        }
        drawStart(g);
        drawGameOver(g);
        drawScore(g);
    }

    /**
     * This method displays the background of the game.
     *
     * @param g
     */
    public void drawBackground(Graphics g) {
        g.setColor(BKG_COLOR);
        g.fillRect(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
        g.setColor(Color.GREEN.darker());
        g.fillRect(0, BIRD_END_Y, JFRAME_WIDTH, 140);

    }

    /**
     * This method displays the starting message.
     *
     * @param g
     */
    public void drawStart(Graphics g) {
        g.setColor(Color.white);

        if (!playing) {
            g.setFont(new Font("Times New Roman", 1, 80));
            g.drawString("Click SPACE to Play!", 100, JFRAME_WIDTH / 2 - 150);
        }
    }

    /**
     * This method adds an invisible Rectangle called pipe of any size and at
     * any position.
     *
     * @param xPosition
     * @param yPosition
     * @param width
     * @param height
     */
    private void addPipe(int xPosition, int yPosition, int width, int height) {
        Rectangle pipe;
        pipe = new Rectangle(xPosition, yPosition, width, height);
        pipes.add(pipe);
    }

    /**
     * This method adds an invisible Rectangle called pipe throughout the game
     * at specific places and in specific sizes.
     */
    public void addPipes() {

        int gap = 280;
        int width = 100;
        int height;
        int xPosition;
        int yPosition;

        height = 60 + random.nextInt(300);
        xPosition = pipes.get(pipes.size() - 1).x + 600;
        yPosition = JFRAME_HEIGHT - height - 120;
        addPipe(xPosition, yPosition, width, height);

        yPosition = 0;
        xPosition = pipes.get(pipes.size() - 1).x;
        height = JFRAME_HEIGHT - height - gap;
        addPipe(xPosition, yPosition, width, height);
    }

    /**
     * This method adds an invisible Rectangle called pipe at the start of the
     * game at specific places and in specific sizes.
     */
    public void addStartPipes() {

        int gap = 280;
        int width = 100;
        int height;
        int xPosition;
        int yPosition;
        Rectangle pipe;

        for (int i = 0; i < 4; i++) {

            height = 60 + random.nextInt(300);
            xPosition = JFRAME_WIDTH + width + pipes.size() * 300;
            yPosition = JFRAME_HEIGHT - height - 120;
            addPipe(xPosition, yPosition, width, height);

            yPosition = 0;
            xPosition = JFRAME_WIDTH + width + (pipes.size() - 1) * 300;
            height = JFRAME_HEIGHT - height - gap;
            addPipe(xPosition, yPosition, width, height);
        }
    }

    /**
     * This method keeps track and counts the players score throughout the game.
     */
    public void updateScore() {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            if (pipe.y == 0 && bird.getX() + bird.getWidth() / 2 > pipe.x + pipe.width / 2 - 10
                    && bird.getX() + bird.getWidth() / 2 < pipe.getX() + pipe.width / 2 + 10) {

                score += 1;
            }
        }
    }

    /**
     * This method uses the addPipes() method and puts the pipes on the jFrame.
     */
    public void updatePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);

            if (pipe.x + pipe.width < 0) {
                pipes.remove(pipe);

                if (pipe.y == 0) {
                    addPipes();
                }
            }
        }
    }

    /**
     * This method displays the game over message.
     *
     * @param g
     */
    public void drawGameOver(Graphics g) {
        if (gameOver) {
            g.setFont(new Font("Times New Roman", 1, 100));
            g.drawString("Game Over!", 200, JFRAME_HEIGHT / 2 - 100);
            g.setFont(new Font("Times New Roman", 1, 50));
            g.drawString("Press SPACE to play again", 250, JFRAME_HEIGHT / 2 + 50);

        }
    }

    /**
     * This method displays the current score, throughout the game.
     *
     * @param g
     */
    public void drawScore(Graphics g) {
        if (!gameOver && playing) {
            g.setFont(new Font("Times New Roman", 1, 75));
            g.drawString(String.valueOf(score), JFRAME_WIDTH / 2 - 25, 100);
        }
    }

    /**
     * This method regulates the speed in which the pipes move, making the game
     * either a fast pace game making it harder or a slow pace game which would
     * be easier.
     */
    public void pipeSpeed() {
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);

            pipe.x -= 10; //controls how fats the pipes move left.
        }
    }

    /**
     * This method places the bird in a stationary position at the bottom of the
     * screen when it hits a pipe to indicate that the game is over.
     */
    public boolean resetBird() {

        boolean intersects = false;

        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            if (pipe.intersects(bird)) {
                intersects = true;
                if (bird.getX() <= pipe.x) {
                    bird.setX(480);
                    bird.setY(BIRD_END_Y);
                }
            }
        }
        return intersects;
    }

        /**
     * This method is called when the bird goes out of the bounds of the game,
     * either being too high or to low.
     */
    public boolean outOfBounds() {

        boolean outofBound = false;

        if (bird.getY() > BIRD_END_Y || bird.getY() < 0) {
            outofBound = true;
            bird.setX(480);
            bird.setY(BIRD_END_Y);
        }

        return outofBound;
    }

    public void updateBirdLocation(int gravity){
        bird.setY((int) bird.getY() + gravity);

    }

    public void resetPipes() {
        pipes.clear();
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
