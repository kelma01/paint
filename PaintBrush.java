import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintBrush {
    public static void main(String[] args) {
        new MainFrame();
    }
}
class MainFrame extends JFrame implements MouseInputListener {
    ArrayList<Shapes> shapes = new ArrayList<>();
    Shapes newShape = null;
    Graphics g = null;
    MouseEvent e = null;
    int currRenk = 7;
    int currMod,currX,currY,thickness,startY,startX,transportColor,fromX,fromY,toX,toY = 0;
    boolean tasinabilirObje,dragging,transport = false;
    String transportType = "";

    JPanel ust_panel = new JPanel();
    JPanel renk_paneli = new JPanel();
    JPanel mavi_renk = new JPanel();
    JPanel kirmizi_renk = new JPanel();
    JPanel yesil_renk = new JPanel();
    JPanel sari_renk = new JPanel();
    JPanel turuncu_renk = new JPanel();
    JPanel mor_renk = new JPanel();
    JPanel siyah_renk = new JPanel();
    JPanel mod_paneli = new JPanel();
    JPanel bar_paneli = new JPanel();
    JPanel cizim_paneli = new JPanel();

    MainFrame(){
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        renk_paneli.setName("renk paneli");
        renk_paneli.setBackground(Color.white);
        renk_paneli.setLayout(new FlowLayout());

        mavi_renk.setBackground(Color.blue);
        kirmizi_renk.setBackground(Color.red);
        yesil_renk.setBackground(Color.green);
        sari_renk.setBackground(Color.yellow);
        turuncu_renk.setBackground(Color.orange);
        mor_renk.setBackground(Color.magenta);
        siyah_renk.setBackground(Color.black);

        mavi_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 1;
            }
        });
        kirmizi_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 2;
            }
        });
        yesil_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 3;
            }
        });
        sari_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 4;
            }
        });
        turuncu_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 5;
            }
        });
        mor_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 6;
            }
        });
        siyah_renk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currRenk = 7;
            }
        });

        mavi_renk.setPreferredSize(new Dimension(60,30));
        kirmizi_renk.setPreferredSize(new Dimension(60,30));
        yesil_renk.setPreferredSize(new Dimension(60,30));
        turuncu_renk.setPreferredSize(new Dimension(60,30));
        sari_renk.setPreferredSize(new Dimension(60,30));
        siyah_renk.setPreferredSize(new Dimension(60,30));
        mor_renk.setPreferredSize(new Dimension(60,30));

        renk_paneli.add(mavi_renk);
        renk_paneli.add(kirmizi_renk);
        renk_paneli.add(yesil_renk);
        renk_paneli.add(sari_renk);
        renk_paneli.add(turuncu_renk);
        renk_paneli.add(mor_renk);
        renk_paneli.add(siyah_renk);

        ust_panel.setLayout(new BorderLayout());
        ust_panel.add(renk_paneli,BorderLayout.NORTH);

        mod_paneli.setBackground(Color.white);
        mod_paneli.setLayout(new FlowLayout());

        JButton dikdortgen = new JButton("Rectangle");
        JButton oval = new JButton("Circle");
        JButton kalem = new JButton("Draw");
        JButton tasi = new JButton("Move");

        dikdortgen.setBackground(Color.lightGray);
        oval.setBackground(Color.lightGray);
        kalem.setBackground(Color.lightGray);
        tasi.setBackground(Color.lightGray);

        dikdortgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMod=1;
            }
        });
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMod=2;
            }
        });
        kalem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMod=3;
            }
        });
        tasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currMod=4;
            }
        });

        mod_paneli.add(dikdortgen);
        mod_paneli.add(oval);
        mod_paneli.add(kalem);
        //move does not work properly
        //mod_paneli.add(tasi);

        ust_panel.add(mod_paneli,BorderLayout.CENTER);
        ust_panel.add(bar_paneli,BorderLayout.SOUTH);

        bar_paneli.setBackground(Color.blue);
        bar_paneli.setPreferredSize(new Dimension(720,5));

        cizim_paneli.setBackground(Color.white);
        cizim_paneli.addMouseListener(this);
        cizim_paneli.addMouseMotionListener(this);

        add(ust_panel,BorderLayout.NORTH);
        add(cizim_paneli,BorderLayout.CENTER);

        setVisible(true);
    }

    void setGraphics(Graphics newG){
        g = newG;
    }
    public void setMouse(MouseEvent newE){
        this.e = newE;
    }

    public void paint(Graphics g){
        if(currMod==0) {
            super.paint(g);
            return;
        }
        setGraphics(g);
        switch (currRenk) {
            case 1 -> g.setColor(Color.blue);
            case 2 -> g.setColor(Color.red);
            case 3 -> g.setColor(Color.green);
            case 4 -> g.setColor(Color.yellow);
            case 5 -> g.setColor(Color.orange);
            case 6 -> g.setColor(Color.magenta);
            case 7 -> g.setColor(Color.black);
            case 8 -> g.setColor(Color.white);
        }

        if(currMod==1){
            newShape = new Shapes(startX+10,startY+115,currX-startX,currY-startY,"kare",currRenk);
            g.fillRect(startX+10,startY+115,currX-startX,currY-startY);
            shapes.add(newShape);
        }
        else if (currMod == 2) {
            newShape = new Shapes(startX+10,startY+115,currX-startX,currY-startY,"oval",currRenk);
            g.fillOval(startX+10,startY+115,currX-startX,currY-startY);
            shapes.add(newShape);
        }
        else if (currMod == 3) {
            g.fillOval(currX+10,currY+115,10,10);
        }
        else if(currMod == 4 && tasinabilirObje){
            g.setColor(Color.white);
            if(this.newShape.type.equals("kare")) {
                transportType = "kare";
                g.fillRect(this.newShape.startX, this.newShape.startY, this.newShape.width, this.newShape.height);
            }
            else if(this.newShape.type.equals("oval")) {
                transportType = "oval";
                g.fillOval(this.newShape.startX, this.newShape.startY, this.newShape.width, this.newShape.height);
            }
            switch (currRenk) {
                case 1 -> g.setColor(Color.blue);
                case 2 -> g.setColor(Color.red);
                case 3 -> g.setColor(Color.green);
                case 4 -> g.setColor(Color.yellow);
                case 5 -> g.setColor(Color.orange);
                case 6 -> g.setColor(Color.magenta);
                case 7 -> g.setColor(Color.black);
                case 8 -> g.setColor(Color.white);
            }
            tasinabilirObje = false;
            transport = true;
        }
        else if(currMod == 4 && transport){
            if(this.newShape.color == 8)    this.newShape.color = currRenk;
            switch (this.newShape.color){
                case 1 -> g.setColor(Color.blue);
                case 2 -> g.setColor(Color.red);
                case 3 -> g.setColor(Color.green);
                case 4 -> g.setColor(Color.yellow);
                case 5 -> g.setColor(Color.orange);
                case 6 -> g.setColor(Color.magenta);
                case 7 -> g.setColor(Color.black);
            }
            switch (transportType){
                case "kare" -> {
                    g.fillRect(toX+10,toY+115,this.newShape.width,this.newShape.height);
                    this.newShape = new Shapes(toX+10,toY+115,this.newShape.width,this.newShape.height, "kare",this.newShape.color);
                }
                case "oval" -> {
                    g.fillOval(toX+10,toY+115,this.newShape.width,this.newShape.height);
                    this.newShape = new Shapes(toX+10,toY+115,this.newShape.width,this.newShape.height, "oval",this.newShape.color);
                }
            }
            currRenk = this.newShape.color;
            shapes.add(newShape);
            transport=false;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();
        setMouse(e);
        if(e.getY()>=0 && currMod != 0) dragging  = true;
        if (currMod==4){
            for(int i=0; i< shapes.size(); i++){
                if(currX+10>shapes.get(i).startX && currX+10<(shapes.get(i).startX+shapes.get(i).width) && currY+115>shapes.get(i).startY && currY+115<(shapes.get(i).startY+shapes.get(i).height) && shapes.get(i).color<8){
                    this.newShape = shapes.get(i);
                    this.transportColor = this.newShape.color;
                    shapes.remove(shapes.get(i));
                    tasinabilirObje=true;
                    fromX = currX;
                    fromY = currY;
                    break;
                }
            }
            repaint();
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();
        if(dragging){
            if(thickness==0){
                startX = currX;
                startY = currY;
                thickness++;
            }
        }
        if(currMod == 3){
            repaint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        toX = e.getX();
        toY = e.getY();
        if(startX>currX){
            int tmp = startX+currX;
            startX = tmp - startX;
            currX = tmp - currX;
        }
        if(startY>currY){
            int tmp = startY+currY;
            startY = tmp - startY;
            currY = tmp - currY;
        }
        if(dragging && currMod != 3){
            repaint();
        }
        else if(transport && currMod == 4){
            repaint();
        }
        thickness=0;
        dragging = false;

    }
    @Override
    public void mouseExited(MouseEvent e) {
        dragging = false;
        transport = false;
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
class Shapes{
    int startX,startY,width,height,color;
    String type;
    Shapes(int startX, int startY, int width, int height, String type, int color) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = color;
    }
}
