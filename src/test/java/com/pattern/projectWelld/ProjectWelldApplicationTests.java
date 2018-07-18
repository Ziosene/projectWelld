package com.pattern.projectWelld;

import com.pattern.projectWelld.Controller.ControllerRest;
import com.pattern.projectWelld.Domain.Point;
import com.pattern.projectWelld.Domain.Space;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectWelldApplicationTests {

	@Test
	public void testProject() {
		ControllerRest controllerRest = new ControllerRest();
        Space space = Space.getInstanceSpace();

        controllerRest.addPoint("1","1");
		controllerRest.addPoint("2","2");

		//test sull'aggiunta dei punti
		List<Point> listaPunti = space.getPoints();
		Point point1 = listaPunti.get(0);
		Point point2 = listaPunti.get(1);
        Assert.assertEquals(new Point(new BigDecimal(1), new BigDecimal(1)), point1);
        Assert.assertEquals(new Point(new BigDecimal(2), new BigDecimal(2)), point2);

        //test sulla lista dei punti nello spazio
        List<Point> listaAttesa = new ArrayList<Point>();
        listaAttesa.add(new Point(new BigDecimal(1), new BigDecimal(1)));
        listaAttesa.add(new Point(new BigDecimal(2), new BigDecimal(2)));
        Assert.assertThat(controllerRest.getAllPoints(), is(listaAttesa));

        //test sulle rette
        controllerRest.addPoint("0","1");
        List <String> listaRette = controllerRest.getLines(2);
        boolean presente = false;
        for(String retta : listaRette){
            if(retta.equals("y = x + 0"))
                presente = true;
        }
        Assert.assertTrue(presente);
        Assert.assertTrue(!listaRette.isEmpty());

        //test sulla rimozione dei punti
        controllerRest.removePoints();
        listaAttesa.clear();
        Assert.assertThat(listaPunti, is(listaAttesa));
	}

}
