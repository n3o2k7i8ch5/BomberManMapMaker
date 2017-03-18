import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Kisiel on 11.03.2017.
 */
public class Frame extends JPanel implements MouseListener{

    Main main;

    int w = 13;
    int h = 13;
    char code = 'w';

    ArrayList<Object> objects = new ArrayList<>();

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            switch (e.getKeyCode()){
                case KeyEvent.VK_RIGHT:
                    setPreferredSize(new Dimension(Main.RES*++w, Main.RES*h));
                    revalidate();
                    repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    for(Object object : objects)
                        object.x += Main.RES;
                    setPreferredSize(new Dimension(Main.RES*w, Main.RES*++h));
                    revalidate();
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    setPreferredSize(new Dimension(64*w, 64*++h));
                    revalidate();
                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    for(Object object : objects)
                        object.y += Main.RES;
                    setPreferredSize(new Dimension(64*w, 64*++h));
                    revalidate();
                    repaint();
                    break;
                case KeyEvent.VK_1:
                    code = '1';
                    break;
                case KeyEvent.VK_2:
                    code = '2';
                    break;
                case KeyEvent.VK_W:
                    code = 'w';
                    break;
                case KeyEvent.VK_S:
                    code = 's';
                    break;
                case KeyEvent.VK_SPACE:
                    save();
                    break;
            }
        }
    };

    public Frame(Main main){
        this.main = main;
        setFocusable(true);
        setPreferredSize(new Dimension(64*w, 64*h));

        addMouseListener(this);
        addKeyListener(keyAdapter);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        x = x - (x % Main.RES);
        y = y - (y % Main.RES);

        if(SwingUtilities.isLeftMouseButton(e)) {
            boolean exists = false;

            for (Object object : objects)
                if (object.x == x && object.y == y && object.code == code)
                    exists = true;

            if (!exists)
                objects.add(new Object(x, y, code));
        }else {
            Object object_to_remove = null;

            for (Object object : objects)
                if (object.x == x && object.y == y)
                    object_to_remove = object;

            if (object_to_remove != null)
                objects.remove(object_to_remove);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for(Object object : objects)
            object.draw(g2d);

    }

    private void save(){

        String string = "";

        string += w + "/";
        string += h + "/";

        for(Object object : objects)
        {
            string += object.x/Main.RES + "," + object.y/Main.RES + "," + object.code + "/";
        }

        try(PrintWriter out = new PrintWriter("filename.txt" )){
            out.println(string);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
