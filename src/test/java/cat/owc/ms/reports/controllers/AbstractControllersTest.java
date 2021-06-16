package cat.owc.ms.reports.controllers;

import cat.owc.ms.reports.AbstractTest;
import cat.owc.ms.reports.OwcReportsApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OwcReportsApplication.class })
@Transactional
public abstract class AbstractControllersTest extends AbstractTest {

	@BeforeClass
	public static void init() {
		// Sobreescribe las de la clase abstracta para no setear el contexto para que se setee a través de aop
		// como en las llamadas reales
	}

	@AfterClass
	public static void finish() {
	}


}
