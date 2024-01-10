package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This program is used for when the info button is pressed. It displays a
 * smaller frame beside the main game frame which informs the user on how to
 * play the game.
 */
/**
 *
 * @author Hazzem
 * @date 19-June-2020
 */
public class InfoPage extends JFrame {

    private JLabel label;
    private Font textF = new Font("SansSerif", Font.BOLD, 14);
    private String information = "";

    public InfoPage() {
        setSize(300, 400);
        setTitle("Info");
        setLayout(null);

        label = new JLabel(information);
        label.setBounds(1, 1, 270, 320);
        label.setForeground(Color.black);
        label.setFont(textF);

        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("README.txt");
            br = new BufferedReader(fr);
            String msg;

            while ((msg = br.readLine()) != null) {
                information += msg;
            }
            label.setText("<html> How to play: <br><br>" + information + "<br> </html>");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(label);
        setVisible(true);
        setResizable(false);
    }
}
