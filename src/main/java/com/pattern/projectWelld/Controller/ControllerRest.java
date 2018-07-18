package com.pattern.projectWelld.Controller;

import com.pattern.projectWelld.Domain.Line;
import com.pattern.projectWelld.Domain.Point;
import com.pattern.projectWelld.Domain.Space;
import com.pattern.projectWelld.Helper.LineHelper;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class ControllerRest {

    @RequestMapping(method = RequestMethod.POST,value="/point/{x}/{y}")
    public void addPoint(@PathVariable String x,@PathVariable String y){
        Point point = new Point(new BigDecimal(x), new BigDecimal(y));
        Space space = Space.getInstanceSpace();
        space.setPoint(point);
    }

    @RequestMapping(method= RequestMethod.GET, value="/space")
    public List getAllPoints(){
        Space space = Space.getInstanceSpace();
        return space.getPoints();
    }

    @RequestMapping(method= RequestMethod.GET, value="/lines/{n}")
    public List<String> getLines(@PathVariable int n) {
        Space space = Space.getInstanceSpace();
        LineHelper lineHelper = LineHelper.getInstance();
        List<Point> points = space.getPoints();
        //calcolo i punti appartenenti alle rette
        Map<Line,List<Point>> mappaLineaPunto = lineHelper.calcoloRettaPunto(points);

        //calcolo le possibili rette che passano almeno per n punti
        return lineHelper.listaRette(space, mappaLineaPunto, n);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/space")
    public String removePoints() {
        Space space = Space.getInstanceSpace();
        space.removePoint();
        return "Sono stati rimossi tutti i punti";
    }
}
