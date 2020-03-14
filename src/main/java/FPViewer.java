import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FPViewer extends JPanel {
    ArrayList<Rectangle> rooms;
    String[] roomNames;
    public FPViewer( ArrayList<Rectangle> rooms, String[] roomNames)
    {
        this.roomNames = roomNames;
        this.rooms = rooms;
        setBackground(Color.BLACK);
    }
    private int transform(double val)
    {
        int scale = 10;
        return (int) Math.round(val)*scale;
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for(int i=0; i < this.rooms.size(); i++)
        { // drawing rectangles
            g2.drawRect(transform(this.rooms.get(i).getX0()), transform(this.rooms.get(i).getY0()),
                    transform(this.rooms.get(i).getW()), transform(this.rooms.get(i).getH()));
            // Draw name of the rooms
            g2.drawString(this.roomNames[i], transform(this.rooms.get(i).getXCenter()-2),
                    transform(this.rooms.get(i).getYCenter()));
        }
    }
}
