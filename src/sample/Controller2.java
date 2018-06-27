package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by VISHAL-PC on 4/16/2017.
 */
public class Controller2 implements Initializable {

    public Process p[]=new Process[4];
    public int x,y,i,count,ti,s=0;
    public double awt,atr,uti;


    @FXML
    public BarChart<String,Double> barChart;
    public TextField at1,at2,at3,at0,bt1,bt2,bt3,bt0;
    public Button btn2;
    XYChart.Series<String, Double> series1 = new XYChart.Series<>();

    XYChart.Series<String, Double> series2 = new XYChart.Series<>();

    XYChart.Series<String, Double> series3 = new XYChart.Series<>();



    public void btn2clicked()throws IOException
    {

            Stage stage;
            Parent root;
            stage=(Stage) btn2.getScene().getWindow();
            root= FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("CPU Scheduling");
            stage.setScene(scene);
            stage.show();

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

    public void btf()
    {
        if(s==0)
        {
            assignValue();
            s++;
        }
        System.out.println("FCFS");
        count=0;
        ti=0;
        awt=0;
        atr=0;
        uti=0;
        while(count<4)
        {
            for(i=0;i<4;i++)
            {
                if(p[i].getDar()<=ti && p[i].getState()==0)
                {
                    ti=ti+p[i].getBr();
                    p[i].setState(1);
                    p[i].setW(ti-p[i].getBr()-p[i].getAr());
                    p[i].setTr(ti-p[i].getAr());
                    awt=awt+p[i].getW();
                    atr=atr+p[i].getTr();
                    uti=uti+p[i].getBr();
                    count++;
                    System.out.println("ti: "+ti+"P"+(i+1)+" : awt:"+awt+" atr: "+atr);
                    ti--;
                    break;
                }
            }
            ti++;
        }
        awt=awt/4;
        atr=atr/4;
        uti=uti/ti;
        System.out.println("awt: "+awt+" atr: "+atr+" uti: "+uti);
        for(i=0;i<4;i++)
        {
            p[i].setState(0);
        }
        series1.getData().add(new XYChart.Data<String, Double>("FCFS", awt));
        series2.getData().add(new XYChart.Data<String, Double>("FCFS", atr));
        series3.getData().add(new XYChart.Data<String, Double>("FCFS", uti));
        barChart.getData().addAll(series1, series2, series3);
    }

    public void bts()
    {
        if(s==0)
        {
            assignValue();
            s++;
        }
        System.out.println("SJF");
        int v=-1,l=99;
        count=0;
        ti=0;
        awt=0;
        atr=0;
        uti=0;
        while(count<4)
        {
            for(i=0;i<4;i++)
            {
                if(p[i].getDar()<=ti && p[i].getState()==0)
                {
                    if(p[i].getBr()<l)
                    {
                        v=i;
                        l=p[i].getBr();
                    }
                }
            }
            i=v;
            if(i>-1)
            {
                ti = ti + p[i].getBr();
                p[i].setState(1);
                p[i].setW(ti - p[i].getBr() - p[i].getAr());
                p[i].setTr(ti - p[i].getAr());
                awt = awt + p[i].getW();
                atr = atr + p[i].getTr();
                uti = uti + p[i].getBr();
                count++;
                System.out.println("ti: " + ti + "P" + (i + 1) + " : awt:" + awt + " atr: " + atr);
                ti--;
                v=-1;
                l=99;
            }
            ti++;
            for(i=0;i<4;i++)
            {
                if(p[i].getDar()<=ti && p[i].getState()==1)
                {
                    v=i;
                }
            }
        }
        awt=awt/4;
        atr=atr/4;
        uti=uti/ti;
        System.out.println("awt: "+awt+" atr: "+atr+" uti: "+uti);
        for(i=0;i<4;i++)
        {
            p[i].setState(0);
        }
        series1.getData().add(new XYChart.Data<String, Double>("SJF", awt));
        series2.getData().add(new XYChart.Data<String, Double>("SJF", atr));
        series3.getData().add(new XYChart.Data<String, Double>("SJF", uti));
        barChart.getData().addAll(series1, series2, series3);
    }


    public void btl()
    {
        if(s==0)
        {
            assignValue();
            s++;
        }
        System.out.println("LJF");
        int v=-1,l=-1;
        count=0;
        ti=0;
        awt=0;
        atr=0;
        uti=0;
        while(count<4)
        {
            for(i=0;i<4;i++)
            {
                if(p[i].getDar()<=ti && p[i].getState()==0)
                {
                    if(p[i].getBr()>l)
                    {
                        v=i;
                        l=p[i].getBr();
                    }
                }
            }
            i=v;
            if(i>-1)
            {
                ti = ti + p[i].getBr();
                p[i].setState(1);
                p[i].setW(ti - p[i].getBr() - p[i].getAr());
                p[i].setTr(ti - p[i].getAr());
                awt = awt + p[i].getW();
                atr = atr + p[i].getTr();
                uti = uti + p[i].getBr();
                count++;
                System.out.println("ti: " + ti + "P" + (i + 1) + " : awt:" + awt + " atr: " + atr);
                ti--;
                v=-1;
                l=-1;
            }
            ti++;
            for(i=0;i<4;i++)
            {
                if(p[i].getDar()<=ti && p[i].getState()==2)
                {
                    v=i;
                }
            }
        }
        awt=awt/4;
        atr=atr/4;
        uti=uti/ti;
        System.out.println("awt: "+awt+" atr: "+atr+" uti: "+uti);
        for(i=0;i<4;i++)
        {
            p[i].setState(0);
        }
        series1.getData().add(new XYChart.Data<String, Double>("LJF", awt));
        series2.getData().add(new XYChart.Data<String, Double>("LJF", atr));
        series3.getData().add(new XYChart.Data<String, Double>("LJF", uti));
        barChart.getData().addAll(series1, series2, series3);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        series1.setName("WT");

        series2.setName("TR");

        series3.setName("UT");

    }
}
