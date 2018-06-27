package sample;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {

    public Process p[]=new Process[4];
    public int x,y,i,ti=-1,cre=0,tre=0,pro=-1,hre=0,count,cg=0,back=0;
    public int re[]=new int[4];


    @FXML
    public Label lm1,lm2,lm3,lm0,lr1,lr2,lr3,lr0,lp,lg1,lg2,lg3,lg0;
    public TextField at1,at2,at3,at0,bt1,bt2,bt3,bt0;
    public Label whead,w1,w2,w3,w4,tahead,ta1,ta2,ta3,ta4,awt,ata,cuti,timmer;
    public Button btn1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void btn1clicked() throws IOException
    {
        if(back==0) {
            if (ti == -1) {
                //Function to assign arrival time and burst time to processes.
                assignValue();
            }


            //Process moving from memory to ready Queue.
            memToReady();

            if (cre > 0 || pro != -1) {
                //Process moving from ready Queue to processor
                redToPro();
            }

            ti++;
            timmer.setText("" + ti);
            System.out.println("time  " + ti);
            System.out.println("cre " + cre + " tre " + tre + " hre " + hre + " pro " + pro + " cg " + cg + " count " + count);
            if (count == 4) {
                System.out.println("Finished");
                count++;
                //Display average waiting time and turn aroung time
                display();
                btn1.setText("Home");
                back++;
            }
        }
        else
        {
            Stage stage;
            Parent root;
            stage=(Stage) btn1.getScene().getWindow();
            root= FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("CPU Scheduling");
            stage.setScene(scene);
            stage.show();
        }
    }


    //To initalize values.
    public void assignValue()
    {
        System.out.println("Arrival time   Burst time");
        for(i=0;i<4;i++)
        {
            if(i==0)
            {
                x=Integer.parseInt(at0.getText());
                y=Integer.parseInt(bt0.getText());
            }
            else if(i==1)
            {
                x=Integer.parseInt(at1.getText());
                y=Integer.parseInt(bt1.getText());
            }
            else if(i==2)
            {
                x=Integer.parseInt(at2.getText());
                y=Integer.parseInt(bt2.getText());
            }
            else
            {
                x=Integer.parseInt(at3.getText());
                y=Integer.parseInt(bt3.getText());
            }

            p[i]=new Process(x,y);
            System.out.println("    " + p[i].getDar() + "         " + p[i].getDbr());
        }
    }

    //To move process from memory to ready queue.
    public void memToReady()
    {
        for(i=0;i<4;i++)
        {
            if(p[i].getState()==0 && p[i].getAr()<=ti+1)
            {
                re[cre]=i;
                switch(cre)
                {
                    case 0: lr0.setText("P"+(i+1));break;
                    case 1: lr1.setText("P"+(i+1));break;
                    case 2: lr2.setText("P"+(i+1));break;
                    case 3: lr3.setText("P"+(i+1));break;
                }
                cre++;
                tre++;
                switch(i)
                {
                    case 0: lm0.setText("");break;
                    case 1: lm1.setText("");break;
                    case 2: lm2.setText("");break;
                    case 3: lm3.setText("");break;
                }
                p[i].setState(1);
            }
        }
    }

    //Process moving from ready Queue to processor
    public void redToPro()
    {
        int l=pro,j,i,v=99,k=-1;
        if(pro==-1)
        {
            for(i=0;i<cre;i++)
            {
                if(p[re[i]].getState()==1 && p[re[i]].getAr()<=ti+1 && p[re[i]].getDbr()>0 && p[re[i]].getBr()<v)
                {
                    l=re[i];
                    k=i;
                    v=p[re[i]].getBr();
                }
            }
        }
        if(p[l].getState()==1)
        {
            pro=l;
            p[l].setState(2);
            lp.setText("P" + (l + 1));
            cre--;
            for (j = k; j < cre; j++)
            {
                switch (j) {
                    case 0:
                        lr0.setText("P" + (re[j + 1] + 1));
                        break;
                    case 1:
                        lr1.setText("P" + (re[j + 1] + 1));
                        break;
                    case 2:
                        lr2.setText("P" + (re[j + 1] + 1));
                        break;
                    case 3:
                        lr3.setText("P" + (re[j + 1] + 1));
                        break;
                }
            }
            for(j=k;j<cre;j++)
            {
                re[j]=re[j+1];
            }
            switch (cre) {
                case 0:
                    lr0.setText("");
                    break;
                case 1:
                    lr1.setText("");
                    break;
                case 2:
                    lr2.setText("");
                    break;
                case 3:
                    lr3.setText("");
                    break;
            }
            System.out.println("In if"+"P"+(l+1));
        }
        else
        {
            p[l].setDbr(p[l].getDbr()-1);
            if(p[l].getDbr()==0)
            {
                //Process moving from processor to gant
                proToGan();
                count++;
                //hre++;
                pro=-1;
                p[l].setFin(ti+1);
                p[l].setW(p[l].getFin()-p[l].getAr()-p[l].getBr());
                p[l].setTr(p[l].getFin()-p[l].getBr());
                if(count<4 && cre>0)
                {
                    memToReady();
                    redToPro();
                }
            }
            System.out.println("P"+(l+1)+"   :"+p[l].getDbr());
        }

    }


    public void proToGan()
    {
        switch (cg) {
            case 0:
                lg0.setText("P"+(pro+1));
                break;
            case 1:
                lg1.setText("P"+(pro+1));
                break;
            case 2:
                lg2.setText("P"+(pro+1));
                break;
            case 3:
                lg3.setText("P"+(pro+1));
                break;
        }
        cg++;
        lp.setText("");
    }



    //Display average waiting time and turn aroung time
    public void display()
    {
        //whead.setText("Waiting Time");
        w1.setText(""+p[0].getW());
        w2.setText(""+p[1].getW());
        w3.setText(""+p[2].getW());
        w4.setText(""+p[3].getW());
        //tahead.setText("Turnaround Time");
        ta1.setText(""+p[0].getTr());
        ta2.setText(""+p[1].getTr());
        ta3.setText(""+p[2].getTr());
        ta4.setText(""+p[3].getTr());
        float a=0,b=0,c=0;
        int i;
        for(i=0;i<4;i++)
        {
            a=a+p[i].getW();
            b=b+p[i].getTr();
            c=c+p[i].getBr();
        }
        a=a/4;
        b=b/4;
        c=c/ti;
        c=c*100;
        awt.setText("Average Wating Time: "+a);
        ata.setText("Average Turnaround Time: "+b);
        cuti.setText("CPU Utilization: "+c);
    }


}
