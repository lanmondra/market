package cat.owc.ms.reports;

import cat.owc.ms.reports.config.ClientContextHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OwcReportsApplication.class })
@Transactional
public abstract class AbstractTest extends ReportsClass {

	@BeforeClass
	public static void init() {
		ContextInfo contextInfo = new ContextInfo();
		contextInfo.setFederation("test");
		contextInfo.setRequestOrigin(ContextInfo.ORIGIN_OTHERS);
		contextInfo.setLanguage(ContextInfo.DEFAULT_LANG);

		ClientContextHolder.setCurrentContext(contextInfo);
	}
	
	@AfterClass
	public static void finish() {
		ClientContextHolder.clear();
	}


}
