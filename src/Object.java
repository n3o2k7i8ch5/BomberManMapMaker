import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kisiel on 07.03.2017.
 */
public class Object {

    public int x, y;
    String image_string;
    public Image image;

    char code;

    static final String GRASS_PATH = "res/drawables/grass.png";
    static final String GRASS_LIGHT_PATH = "res/drawables/grass_light.png";
    static final String WALL_PATH = "res/drawables/wall.png";
    static final String SOFT_WALL_PATH = "res/drawables/wall_soft.png";

    public Object(int x, int y, char code){

        this.x = x;
        this.y = y;
        this.code = code;

        switch (code){
            case '1':
                image_string = GRASS_PATH;
                break;
            case '2':
                image_string = GRASS_LIGHT_PATH;
                break;
            case 'w':
                image_string = WALL_PATH;
                break;
            case 's':
                image_string = SOFT_WALL_PATH;
                break;
        }

        ImageIcon ic = new ImageIcon(image_string);
        image = ic.getImage();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }

}
