import javax.swing.*;

/**
 * Created by Kisiel on 11.03.2017.
 */
public class Main extends JFrame {

    static final int RES = 64;

    public static void main(String[] args) {
        // write your code here

        Main main = new Main();
        main.setTitle("Map Maker ||| 'Space' - Save | '1' - Dark Grass | '2' - Light Grass | 'w' - Wall | 's' - SoftWall");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrPane = new JScrollPane(new Frame(main));
        main.add(scrPane);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);

    }
}
