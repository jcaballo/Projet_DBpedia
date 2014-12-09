package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jujulejaffa on 09/12/14.
 */
public class photoPanel extends JPanel {

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    private Image img;

    public photoPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public photoPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }


}
