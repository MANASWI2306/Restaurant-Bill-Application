import javax.swing.*;
import java.awt.*;

public class BillClass {
    static void bill_frame()
    {
        int i=0;
        JFrame bill=new JFrame();
        bill.getContentPane().setBackground(Color.pink);
        bill.setLayout(null);
        bill.setVisible(true);
        bill.setSize(2500,1000);
        bill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel message=new JLabel("Your Bill");
        message.setFont(new Font("serif",Font.ITALIC,50));
        message.setBounds(600,60,600,50);
        message.setForeground(Color.BLACK);
        bill.add(message);
        JLabel[] jl=new JLabel[HomeClass.totallist.size()];
        JLabel[] ml=new JLabel[HomeClass.totallist.size()];
        int x=200,y=150,h=50,w=300;
        int bx=400,by=150,bh=30,bw=100;
        for(int j=0;j<HomeClass.totallist.size();j++)
        {
            jl[j]=new JLabel(HomeClass.totallist.get(j));
            jl[j].setFont(new Font("serif",Font.ITALIC,20));
            jl[j].setForeground(Color.BLUE);
            jl[j].setBounds(x,y,w,h);
            if(VegetarianClass.veghash.containsKey(HomeClass.totallist.get(j))) {
                ml[j] = new JLabel("Rs"+String.valueOf(VegetarianClass.veghash.get(HomeClass.totallist.get(j))));
                ml[j].setFont(new Font("serif",Font.ITALIC,20));
                HomeClass.finallist.add(String.valueOf(VegetarianClass.veghash.get(HomeClass.totallist.get(j))));
            }
            else if(NonVegetarianClass.nonveghash.containsKey(HomeClass.totallist.get(j))) {
                ml[j] = new JLabel("Rs."+String.valueOf(NonVegetarianClass.nonveghash.get(HomeClass.totallist.get(j))));
                ml[j].setFont(new Font("serif",Font.ITALIC,20));
                HomeClass.finallist.add(String.valueOf(NonVegetarianClass.nonveghash.get(HomeClass.totallist.get(j))));
            }
            else
            {
                ml[j] = new JLabel("Rs."+String.valueOf(DessertClass.desserthash.get(HomeClass.totallist.get(j))));
                ml[j].setFont(new Font("serif",Font.ITALIC,20));
                HomeClass.finallist.add(String.valueOf(DessertClass.desserthash.get(HomeClass.totallist.get(j))));
            }
            ml[j].setBounds(bx,by,bw,bh);
            y+=50;
            by+=50;
            bill.add(ml[j]);
            bill.add(jl[j]);
        }
        String st[]=new String[HomeClass.finallist.size()];
        HomeClass.finallist.toArray(st);
        int n,sum=0;
        for(int k=0;k< st.length;k++)
        {
            n=Integer.parseInt(st[k]);
            sum=sum+n;
        }
        JLabel totalbill=new JLabel("TOTAL BILL AMOUNT");
        totalbill.setFont(new Font("serif",Font.BOLD,30));
        JLabel amt=new JLabel("Rs."+String.valueOf(sum));
        amt.setFont(new Font("serif",Font.BOLD,30));
        totalbill.setBounds(800,700,400,40);
        amt.setBounds(1200,700,100,40);
        bill.add(totalbill);
        bill.add(amt);
    }
}
