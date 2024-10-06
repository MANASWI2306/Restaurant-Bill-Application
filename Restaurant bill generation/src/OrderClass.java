import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OrderClass {
    static void Order_frame() {
        int i;
        JButton con=new JButton("CONFIRM");
        con.setBounds(1200,700,200,50);
        con.setFont(new Font("samsserif",Font.PLAIN,20));
        con.setBackground(Color.ORANGE);
        JLabel confirm=new JLabel("click on the items you want to remove and click confirm to confirm your order");
        confirm.setFont(new Font("serif",Font.ITALIC,30));
        confirm.setForeground(Color.RED);
        confirm.setBounds(200,100,1000,40);
        JFrame o = new JFrame();
        o.getContentPane().setBackground(Color.pink);
        o.setSize(2500, 1000);
        o.setVisible(true);
        o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        o.setLayout(null);
        o.add(con);
        o.add(confirm);
        con.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillClass.bill_frame();
            }
        });
        JToggleButton ob[] = new JToggleButton[HomeClass.totallist.size()];
        int x = 150, y = 250;
        for (i = 0; i < HomeClass.totallist.size(); i++) {
            ob[i] = new JToggleButton((String) HomeClass.totallist.get(i));
            ob[i].setFont(new Font("serif",Font.ITALIC,30));
            ob[i].setBounds(x, y, 300, 40);
            ob[i].setBackground(Color.lightGray);
            ob[i].setForeground(Color.BLUE);
            o.add(ob[i]);
            if(i%7==0 &&i!=0)
            {
                y=250;
                x+=350;
            }
            else
            y += 60;
        }
        for (i = 0; i < HomeClass.totallist.size(); i++) {
            int finalI = i;
            ob[finalI].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    HomeClass.totallist.remove(ob[finalI].getText());
                    Order_frame();
                }
            });

        }
    }

}
