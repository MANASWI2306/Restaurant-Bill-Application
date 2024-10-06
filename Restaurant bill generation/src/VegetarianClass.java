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

public class VegetarianClass {
    static Hashtable<String, Integer> veghash = new Hashtable<>();
    static ArrayList<String> vegitem=new ArrayList<>();
    static ArrayList<String> vegprice=new ArrayList<>();
    static ArrayList<Integer> vegindexes = new ArrayList<>();
    static void veg_frame() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "system");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from veg");
            while(rs.next())
            {
                vegitem.add(rs.getString("NAME"));
                vegprice.add(String.valueOf(rs.getInt("PRICE")));
                veghash.put(rs.getString("NAME"),rs.getInt("PRICE") );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        JFrame veg=new JFrame();
        veg.setSize(25000,1000);
        veg.setVisible(true);
        veg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        veg.setLayout(null);
        veg.getContentPane().setBackground(Color.pink);
        JButton back=new JButton("<-- back to home");
        back.setBounds(20,50,200,50);
        back.setFont(new Font("samsserif",Font.PLAIN,20));
        back.setBackground(Color.LIGHT_GRAY);
        veg.add(back);
        JButton nonvegb = new JButton("NONVEG");
        nonvegb.setBounds(900, 50, 150, 30);
        nonvegb.setFont(new Font("samsserif",Font.PLAIN,20));
        nonvegb.setBackground(Color.LIGHT_GRAY);
        JButton dessertb = new JButton("DESSERT");
        dessertb.setBounds(1100, 50, 150, 30);
        dessertb.setFont(new Font("sansserif",Font.PLAIN,20));
        dessertb.setBackground(Color.LIGHT_GRAY);
        JButton db = new JButton("Done");
        db.setBounds(550, 650, 200, 40);
        db.setFont(new Font("SansSerif",Font.PLAIN,30));
        db.setBackground(Color.orange);
        veg.add(db);
        veg.add(nonvegb);
        veg.add(dessertb);
        veg.add(db);
        JCheckBox[] v = new JCheckBox[veghash.size()];
        JLabel[] price = new JLabel[veghash.size()];
        int x = 50, y = 200, w = 250, h = 30;
        int px = 300, py = 200, pw = 100, ph = 30;
        for(int i=0;i<veghash.size();i++)
        {
            v[i] = new JCheckBox(vegitem.get(i));
            v[i].setBackground(Color.PINK);
            v[i].setForeground(Color.BLUE);
            v[i].setIcon (new MetalCheckBoxIcon () {
                protected int getControlSize() { return 20; }
            });
            price[i] = new JLabel( "Rs."+veghash.get(vegitem.get(i)));
            veg.add(v[i]);
            veg.add(price[i]);
            v[i].setFont(new Font("serif",Font.ITALIC,20));
            price[i].setFont(new Font("serif",Font.ITALIC,20));
            veg.add(price[i]);
            v[i].setBounds(x, y, w, h);
            price[i].setBounds(px, py, pw, ph);
            y += 50;
            py += 50;
            if(i%9==0&&i!=0) {
                y=200;
                py=200;
                x += 350;
                px+=350;
            }
        }
        for(int each:vegindexes) {
            v[each].setSelected(true);
        }
        for (int i = 0; i < veghash.size(); i++) {
            int finalI = i;
            v[finalI].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (v[finalI].isSelected()) {
                        HomeClass.veglist.add(v[finalI].getText());
                        vegindexes.add(finalI);
                    }
                    if (!(v[finalI].isSelected()))
                    { HomeClass.veglist.remove(v[finalI].getText());
                        vegindexes.remove((Integer) finalI);}
                }
            });
        }
        nonvegb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("NONVEG"))
                    NonVegetarianClass.nonveg_frame();
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
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("<-- back to home"))
                    HomeClass.home_frame();
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
