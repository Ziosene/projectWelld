package com.pattern.projectWelld.Domain;

import java.math.BigDecimal;

public class Line {

    private BigDecimal m;
    private BigDecimal q;
    private boolean rettaVerticale = false;

    public Line(BigDecimal m, BigDecimal q, Point p1, Point p2){
        this.m = m;
        this.q = q;
        setRettaVerticale(p1, p2);
    }

    public BigDecimal getM() {
        return m;
    }

    public void setM(BigDecimal m) {
        this.m = m;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public boolean isRettaVerticale() {
        return rettaVerticale;
    }

    public void setRettaVerticale(Point p1, Point p2) {
        this.rettaVerticale = p2.getX().equals(p1.getX());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (m != null ? !m.equals(line.m) : line.m != null) return false;
        return q != null ? q.equals(line.q) : line.q == null;
    }

    @Override
    public int hashCode() {
        int result = m != null ? m.hashCode() : 0;
        result = 31 * result + (q != null ? q.hashCode() : 0);
        return result;
    }
}
