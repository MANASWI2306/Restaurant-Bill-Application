import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomeClass {
    static ArrayList<String> veglist = new ArrayList<>();
    static ArrayList<String> totallist = new ArrayList<>();
    static ArrayList<String> nonveglist = new ArrayList<>();
    static ArrayList<String> dessertlist = new ArrayList<>();
    static ArrayList<String> finallist = new ArrayList<>();
    static void home_frame() {
        JFrame jf = new JFrame();
        JLabel welcome=new JLabel("WELCOME TO SIMPS KITCHEN");
        welcome.setBounds(500,100,700,100);
        welcome.setFont(new Font("serif",Font.ITALIC,40));
        welcome.setForeground(Color.CYAN);
        JLabel choice=new JLabel("please place your order");
        choice.setBounds(650,250,700,100);
        choice.setFont(new Font("serif",Font.ITALIC,30));
        choice.setForeground(Color.DARK_GRAY);
        JButton veg = new JButton("VEG");
        veg.setBounds(700, 400, 200, 50);
        veg.setBackground(Color.LIGHT_GRAY);
        JButton nonveg = new JButton("NONVEG");
        nonveg.setBounds(700, 500, 200, 50);
        nonveg.setBackground(Color.LIGHT_GRAY);
        JButton dessert = new JButton("DESSERTS");
        dessert.setBounds(700, 600, 200, 50);
        dessert.setBackground(Color.LIGHT_GRAY);
        veg.setForeground(Color.BLUE);
        nonveg.setForeground(Color.BLUE);
        dessert.setForeground(Color.BLUE);
        veg.setFont(new Font("serif",Font.ITALIC,30));
        nonveg.setFont(new Font("serif",Font.ITALIC,30));
        dessert.setFont(new Font("serif",Font.ITALIC,30));
        jf.setVisible(true);
        jf.setSize(2500, 1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setBackground(Color.pink);
        jf.setLayout(null);
        jf.add(welcome);
        jf.add(choice);
        jf.add(veg);
        jf.add(nonveg);
        jf.add(dessert);
        veg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VegetarianClass.veg_frame();
            }
        });
        dessert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DessertClass.dessert_frame();
            }
        });
        nonveg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NonVegetarianClass.nonveg_frame();
            }
        });
    }
}
