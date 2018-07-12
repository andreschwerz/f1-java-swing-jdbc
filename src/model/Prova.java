package model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 *
 * @author André Schwerz
 */
public class Prova {

    private String gp;
    private Circuito circuito;
    private Date data;

    public Circuito getCircuito() {
        return circuito;
    }

    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }

    public String getDataMySQL() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String temp = dateFormat.format(this.data);

        String day = temp.substring(0, 2);
        String month = temp.substring(3, 5);
        String year = temp.substring(6, 10);
        return year + '/' + month + '/' + day;
    }

    public String getStrData() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String temp = dateFormat.format(this.data);

        String day = temp.substring(0, 2);
        String month = temp.substring(3, 5);
        String year = temp.substring(6, 10);
        return day + '/' + month + '/' + year;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setData(String data) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        this.data = (Date) format.parse(data);
    }

    public void setDataMySQL(String data) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.data =  new java.sql.Date(format.parse(data).getTime()); 
    }

    public String getGp() {
        return gp;
    }

    public void setGp(String gp) {
        this.gp = gp;
    }
}
