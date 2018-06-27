package sample;

/**
 * Created by VISHAL-PC on 4/13/2017.
 */
public class Process {
    public int br,dbr,ar,dar,fin,w,tr,state;
    Process()
    {
        br=0;
        dbr=br;
        ar=0;
        dar=ar;
        state=0;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBr() {
        return br;
    }

    public void setBr(int br) {
        this.br = br;
    }

    public int getDbr() {
        return dbr;
    }

    public void setDbr(int dbr) {
        this.dbr = dbr;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public int getDar() {
        return dar;
    }

    public void setDar(int dar) {
        this.dar = dar;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    Process(int x, int y)
    {
        br=y;
        dbr=br;
        ar=x;
        dar=ar;
        state=0;
    }
}
