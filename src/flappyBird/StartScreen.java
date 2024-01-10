package flappyBird;

//import static flappyBird.GameGUI.flappyBird;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This program is the starting page for the Flappy Bird clone game and also
 * serves as a home page for the game including the instruction on how to play
 * the game.
 */
/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class StartScreen extends JFrame implements ActionListener {

    private JButton play;
    private JPanel bkgPanel;
    private JLabel title;
    private JButton infoButton;
    private Font textF = new Font("SansSerif", Font.BOLD, 100);
    private Font buttonF = new Font("SansSerif", Font.ITALIC, 55);
    private static final Color BKG_COLOR = new Color(133, 235, 255);

    ImageIcon info = new ImageIcon(getClass().getResource("info.png"));

    /**
     * This method is the main constructor for the start screen.
     */
    public StartScreen() {
        setSize(1000, 800);
        setTitle("Flappy Bird Game");
        setLayout(null);

//        Background
        bkgPanel = new JPanel();
        bkgPanel.setBackground(BKG_COLOR);
        bkgPanel.setSize(1000, 800);

//        Info Icon for instructions on how to play the game
        infoButton = new JButton();
        infoButton.setIcon(info);
        infoButton.setBackground(Color.gray);
        infoButton.setBounds(900, 50, 60, 60);
        infoButton.addActionListener(this);
//        Title
        title = new JLabel("Flappy Bird");
        title.setBounds(150, 200, 700, 250);
        title.setForeground(Color.white);
        title.setBackground(Color.red);
        title.setFont(textF);
//        Play button
        play = new JButton("PLAY");
        play.setBounds(325, 550, 350, 100);
        play.setFont(buttonF);
        play.setBackground(Color.GREEN.darker());
        play.setForeground(Color.black);
        play.setFocusPainted(false);
        play.addActionListener(this);
        add(infoButton);
        add(play);
        add(title);
        add(bkgPanel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) { // if the play button is pressed than start the game.
            new GameGUI();
            this.dispose();
        } else if (e.getSource() == infoButton) {
            InfoPage i = new InfoPage();

        }
    }
}
