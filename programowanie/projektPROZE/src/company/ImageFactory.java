package company;

import javax.swing.*;

public class ImageFactory {
    public static ImageIcon createImage(Image image) {
        ImageIcon imageIcon = null;

        switch (image) {
            case UFO:
                imageIcon = new ImageIcon(Constans.Ufo_image_url);
                break;
            case Lander
                imageIcon = new ImageIcon(Constans.Ufo_image_url);
                break;
            case UFO:
                imageIcon = new IMageIcon(Constans.Ufo_image_url);
                break;
            default:
                break;
        }
        return imageIcon;
    }

}