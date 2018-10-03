package webCrawler;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FuncoesCrawler {
	private String localExe = "C:\\Users\\OSMAR COSTA\\Desktop\\203\\caseWebCrawler\\chromedriver.exe";
	private String titulo;
	private String url;
	private String texto;
	private String data;
	private int contadorDeNoticias = 0;

	public void executarCrawler() {
		System.setProperty("webdriver.chrome.driver", localExe);
		WebDriver driver = new ChromeDriver();

		driver.get("http://g1.globo.com/ultimas-noticias.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0,10000)");

		java.util.List<WebElement> listaDeElementosDeNoticia = driver.findElements(By.className("feed-post-body"));
		;

		for (WebElement elemento : listaDeElementosDeNoticia) {
			if (contadorDeNoticias == 10) {
				break;
			}

			titulo = elemento.findElement(By.className("feed-post-link")).getText();
			url = elemento.findElement(By.className("feed-post-link")).getAttribute("href");

			WebDriver noticia = new ChromeDriver();
			noticia.get(url);

			try {
				data = noticia.findElement(By.tagName("time")).getText();
			} catch (Exception e) {
				noticia.close();
				continue;
			}

			try {
				List<WebElement> listaDeElementosComTexto = noticia
						.findElements(By.className("content-text__container"));
				texto = listaDeElementosComTexto.get(0).getText();

				for (int i = 1; i < listaDeElementosComTexto.size(); i++) {
					texto = texto + "\n" + listaDeElementosComTexto.get(i).getText();
				}
			} catch (Exception e) {
				noticia.close();
				continue;
			}

			System.out.println(titulo + "\n");
			System.out.println(url + "\n");
			System.out.println(texto + "\n");
			System.out.println(data + "\n \n \n");

			noticia.close();
			contadorDeNoticias++;
		}
		driver.close();
	}

}
