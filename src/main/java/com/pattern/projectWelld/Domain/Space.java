package com.pattern.projectWelld.Domain;

import java.util.ArrayList;
import java.util.List;

public class Space {

    private Point point;
    private List<Point> points;
    private List<Line> lines;
    private static Space instanceSpace;

    private Space(){
        this.points = new ArrayList<Point>();
        this.lines = new ArrayList<Line>();
    };

    public static Space getInstanceSpace(){
        if(instanceSpace == null)
            instanceSpace = new Space();
        return instanceSpace;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
//        if(!existsPoint(point))
        if(!points.contains(point))
            points.add(point);
    }

    public void setLine(Line line) {
//        if(!existsLine(line))
        if(!lines.contains(line))
            lines.add(line);
    }

    public List getPoints() {
        return points;
    }

    public List getLines() {
        return lines;
    }

    //rimuovo tutti i punti dallo spazio
    public void removePoint(){
        this.points.clear();
        this.lines.clear();
    }

    //controllo se il punto esiste già nello spazio
    public boolean existsPoint(Point point){
        for (Point p: this.points) {
            if(p.getX() == point.getX() && p.getY() == point.getY())
                return true;
        }
        return false;
    }

    //controllo se il punto esiste già nello spazio
    public boolean existsLine(Line line){
        for (Line l: this.lines) {
            if(line.getM() == l.getM() && line.getQ() == l.getQ())
                return true;
        }
        return false;
    }
}
