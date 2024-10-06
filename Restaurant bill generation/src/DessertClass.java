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

public class DessertClass {
    static Hashtable<String, Integer> desserthash = new Hashtable<>();
    static ArrayList<String> dessertitem=new ArrayList<>();
    static ArrayList<String> dessertprice=new ArrayList<>();
    static ArrayList<Integer> dessertindexes = new ArrayList<>();
    static void dessert_frame() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restraunt", "root", "system");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from desserts");
            while(rs.next())
            {
                dessertitem.add(rs.getString("NAME"));
                dessertprice.add(String.valueOf(rs.getInt("PRICE")));
                desserthash.put(rs.getString("NAME"),rs.getInt("PRICE") );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JFrame dessert = new JFrame();
        dessert.setSize(2500, 1000);
        dessert.setVisible(true);
        dessert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dessert.setLayout(null);
        dessert.getContentPane().setBackground(Color.pink);
        JButton back = new JButton("<-- back to home");
        back.setBounds(20, 50, 200, 50);
        back.setFont(new Font("samsserif",Font.PLAIN,20));
        back.setBackground(Color.LIGHT_GRAY);
        dessert.add(back);
        JButton db = new JButton("Done");
        db.setBounds(550, 650, 200, 40);
        db.setFont(new Font("SansSerif",Font.PLAIN,30));
        db.setBackground(Color.orange);
        dessert.add(db);
        JButton vegb = new JButton("VEG");
        vegb.setBounds(900, 50, 150, 30);
        vegb.setFont(new Font("samsserif",Font.PLAIN,20));
        vegb.setBackground(Color.LIGHT_GRAY);
        JButton nonvegb = new JButton("NONVEG");
        nonvegb.setBounds(1100, 50, 150, 30);
        nonvegb.setFont(new Font("samsserif",Font.PLAIN,20));
        nonvegb.setBackground(Color.LIGHT_GRAY);
        dessert.add(vegb);
        dessert.add(nonvegb);
        int x = 100, y = 200, w = 250, h = 30;
        int px = 350, py = 200, pw = 100, ph = 30;
        JCheckBox[] d = new JCheckBox[desserthash.size()];
        JLabel[] price = new JLabel[desserthash.size()];
        for (int i = 0; i < desserthash.size(); i++) {
            d[i] = new JCheckBox(dessertitem.get(i));
            d[i].setBackground(Color.PINK);
            d[i].setForeground(Color.BLUE);
            d[i].setIcon (new MetalCheckBoxIcon() {
                protected int getControlSize() { return 20; }
            });
            price[i] = new JLabel("Rs." + String.valueOf(desserthash.get(dessertitem.get(i))));
            dessert.add(d[i]);
            d[i].setBounds(x, y, w, h);
            price[i].setBounds(px, py, pw, ph);
            d[i].setFont(new Font("serif",Font.ITALIC,20));
            price[i].setFont(new Font("serif",Font.ITALIC,20));
            dessert.add(price[i]);
            y += 50;
            py += 50;
            if (i%7==0&&i!=0) {
                y = 200;
                py = 200;
                x += 400;
                px += 400;
            }
        }
        for(int each:dessertindexes) {
            d[each].setSelected(true);
        }
        for (int i = 0; i < desserthash.size(); i++) {
            int finalI = i;
            d[finalI].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (d[finalI].isSelected()) {
                         HomeClass.dessertlist.add(d[finalI].getText());
                            dessertindexes.add(finalI);
                    }
                    if (!(d[finalI].isSelected())) {
                        HomeClass.dessertlist.remove(d[finalI].getText());
                        d[finalI].setSelected(false);
                        dessertindexes.remove((Integer) finalI);
                    }
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
        nonvegb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("NONVEG"))
                    NonVegetarianClass.nonveg_frame();
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
                    //bill_frame();
                }
            }
        });
    }
}
