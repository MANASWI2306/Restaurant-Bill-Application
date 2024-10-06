import javax.swing.*;
import javax.swing.plaf.metal.MetalCheckBoxIcon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class NonVegetarianClass {
    static Hashtable<String, Integer> nonveghash = new Hashtable<>();
    static ArrayList<String> nonvegitem=new ArrayList<>();
    static ArrayList<String> nonvegprice=new ArrayList<>();
    static ArrayList<Integer> nonvegindexes = new ArrayList<>();
    static void nonveg_frame() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restraunt", "root", "system");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from nonveg");
            while(rs.next())
            {
                nonvegitem.add(rs.getString("NAME"));
                nonvegprice.add(String.valueOf(rs.getInt("PRICE")));
                nonveghash.put(rs.getString("NAME"),rs.getInt("PRICE") );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JFrame nonveg = new JFrame();
        nonveg.setSize(2500, 1000);
        nonveg.setVisible(true);
        nonveg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nonveg.setLayout(null);
        nonveg.getContentPane().setBackground(Color.pink);
        JButton back = new JButton("<-- back to home");
        back.setBounds(20, 50, 200, 50);
        back.setFont(new Font("sansSerif",Font.PLAIN,20));
        back.setBackground(Color.LIGHT_GRAY);
        nonveg.add(back);
        JButton db = new JButton("Done");
        db.setBounds(550, 650, 200, 40);
        db.setFont(new Font("SansSerif",Font.PLAIN,30));
        db.setBackground(Color.orange);
        nonveg.add(db);
        JButton vegb = new JButton("VEG");
        vegb.setBounds(900, 50, 150, 30);
        vegb.setFont(new Font("sansSerif",Font.PLAIN,20));
        vegb.setBackground(Color.LIGHT_GRAY);
        JButton dessertb = new JButton("DESSERT");
        dessertb.setBounds(1100, 50, 150, 30);
        dessertb.setFont(new Font("samsserif",Font.PLAIN,20));
        dessertb.setBackground(Color.LIGHT_GRAY);
        nonveg.add(vegb);
        nonveg.add(dessertb);
        int x = 50, y = 200, w = 250, h = 30;
        int px = 300, py = 200, pw = 100, ph = 30;
        JCheckBox[] nv = new JCheckBox[nonveghash.size()];
        JLabel[] price = new JLabel[nonveghash.size()];
        for (int i = 0; i < nonveghash.size(); i++) {
            nv[i] = new JCheckBox(nonvegitem.get(i));
            nv[i].setBackground(Color.PINK);
            nv[i].setForeground(Color.BLUE);
            nv[i].setIcon (new MetalCheckBoxIcon() {
                protected int getControlSize() { return 20; }
            });
            price[i] = new JLabel("Rs." + String.valueOf(nonveghash.get(nonvegitem.get(i))));
            nonveg.add(nv[i]);
            nv[i].setBounds(x, y, w, h);
            price[i].setBounds(px, py, pw, ph);
            nv[i].setFont(new Font("serif",Font.ITALIC,20));
            price[i].setFont(new Font("serif",Font.ITALIC,20));
            nonveg.add(price[i]);
            y += 50;
            py += 50;
            if (i%8==0&&i!=0) {
                y = 200;
                py = 200;
                x += 400;
                px += 400;
            }
        }
        for(int e:nonvegindexes) {
            nv[e].setSelected(true);
        }
        for (int i = 0; i < nonveghash.size(); i++) {
            int finalI = i;
            nv[finalI].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (nv[finalI].isSelected())
                    { HomeClass.nonveglist.add(nv[finalI].getText());
                        nonvegindexes.add(finalI);
                    }
                    if (!(nv[finalI].isSelected())) {
                        HomeClass.nonveglist.remove(nv[finalI].getText());
                        nonvegindexes.remove((Integer) finalI); }
                }
            });
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("<-- back to home"))
                    HomeClass.home_frame();

            }
        });
        vegb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("VEG"))
                    VegetarianClass.veg_frame();
            }
        });
        dessertb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("DESSERT"))
                    DessertClass.dessert_frame();
            }
        });
        db.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("Done")) {
                    HomeClass.totallist.addAll(HomeClass.veglist);
                    HomeClass.totallist.addAll(HomeClass.nonveglist);
                    HomeClass.totallist.addAll(HomeClass.dessertlist);
                    OrderClass.Order_frame();
                    // bill_frame();
                }
            }
        });
    }

}
