package com.pattern.projectWelld.Helper;

import com.pattern.projectWelld.Domain.Line;
import com.pattern.projectWelld.Domain.Point;
import com.pattern.projectWelld.Domain.Space;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class LineHelper {

    private static LineHelper lineHelperInstance = null;

    private LineHelper(){    }

    public static LineHelper getInstance(){
        if(lineHelperInstance == null)
            lineHelperInstance = new LineHelper();
        return lineHelperInstance;
    }

    public BigDecimal calcoloPendenzaRetta(Point p1, Point p2){
        BigDecimal pendenza = null;
        if(p1!=null && p2!=null)
            if(!p2.getX().equals(p1.getX()))
                pendenza = (p2.getY().subtract(p1.getY())).divide((p2.getX().subtract(p1.getX())), MathContext.DECIMAL128);
        return pendenza;
    }

    public BigDecimal calcoloIntersezioneY(Point p1, Point p2, BigDecimal m){
        return (m==null) ? p1.getX() : p1.getY().subtract(m.multiply(p1.getX()));
    }

    public Map<Line, List<Point>> calcoloRettaPunto(List<Point> points){
        Space space = Space.getInstanceSpace();
        Map<Line,List<Point>> mappaLineaPunto = new HashMap<>();
        for(int i = 0; i < points.size() - 1; i++){
            Point p1 = points.get(i);
            for(int j = i+1; j < points.size(); j++){
                Point p2 = points.get(j);
                BigDecimal pendenza = calcoloPendenzaRetta(p1, p2);
                BigDecimal q = calcoloIntersezioneY(p1, p2, pendenza);
                Line line = new Line(pendenza, q, p1, p2);

                //se la retta non esiste nella mappa la aggiungo insieme ai due punti che la creano
                if(!mappaLineaPunto.containsKey(line)){
                    List<Point> puntiAppartenentiRetta = new ArrayList<Point>();
                    space.setLine(line);
                    puntiAppartenentiRetta.add(p1);
                    puntiAppartenentiRetta.add(p2);
                    mappaLineaPunto.put(line, puntiAppartenentiRetta);
                }
                //se la retta esiste già nella mappa aggiungo i punti nuovi
                else {
                    mappaLineaPunto.get(line).add(p1);
                    mappaLineaPunto.get(line).add(p2);
                }
            }
        }
        return mappaLineaPunto;
    }

    public List<String> listaRette(Space space, Map<Line, List<Point>> map, int n){
        List<String> retteAppartenentiNPunti = new ArrayList<>();
        String retta = "";
        int i = 0;
        for(List<Point> points : map.values()) {
            if (points.size() >= n)
                //cerco nella lista delle sole rette la m e la q per costruire la retta in Stringa
                retteAppartenentiNPunti.add(getRetta((Line)space.getLines().get(i), points.get(0)));
            i++;
        }
        return retteAppartenentiNPunti;
    }

    public String getRetta(Line line, Point p){
        String retta = "";
        if(line.isRettaVerticale())
            retta = "x = " + p.getX();
        else if(line.getM().doubleValue() == 0)
            retta = "y = " + line.getQ();
        else if(line.getM().doubleValue() == 1)
            retta = "y = x + " + line.getQ();
        else if(line.getQ().doubleValue() == 0)
            retta = "y = " + line.getM() + "x";
        else
            retta = "y = " + line.getM() + "x + " + line.getQ();
        return retta;
    }


}
