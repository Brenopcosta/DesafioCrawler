package webCrawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class crawler{

	public static void main(String[] args) {

		String localExe = "C:\\Users\\OSMAR COSTA\\Desktop\\203\\caseWebCrawler\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver" , localExe);		
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("http://g1.globo.com/ultimas-noticias.html");
		
		java.util.List<WebElement> listaDeElementosDeNoticia = driver.findElements(By.className("feed-post-body"));;
		
		for (WebElement elemento : listaDeElementosDeNoticia) {
			String titulo = elemento.findElement(By.className("feed-post-link")).getText();
			System.out.println(titulo+"\n");
			
			String url = elemento.findElement(By.className("feed-post-link")).getAttribute("href");
			System.out.println(url+"\n");
			
			String texto = elemento.findElement(By.className("feed-post-body-resumo")).findElement(By.className("_o")).getText();
			System.out.println(texto+"\n");
			System.out.println("\n \n \n \n" );
			
			
				
		}
		
		
		driver.close();
	}

}
