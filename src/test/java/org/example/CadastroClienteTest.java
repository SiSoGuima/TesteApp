package org.example;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class CadastroClienteTest {

    @Test
    public void preencherFormularioComFaker() throws InterruptedException {
        // Inicializa o Faker em português do Brasil
        Faker faker = new Faker(new Locale("pt-BR"));

        // Gera dados fictícios
        String nome = faker.name().fullName();
        String cpf = faker.number().digits(11);
        String email = faker.internet().emailAddress();
        String celular = "12" + faker.number().digits(9);
        String dataNascimento = "01011990";  // Podemos randomizar isso depois
        String cep = "01001000"; // Mantemos um CEP fixo de SP
        String rua = "Praça da Sé";
        String numero = faker.address().buildingNumber();
        String bairro = "Centro";
        String cidade = "São Paulo";

        // Configura o Selenium
       // WebDriverManager.chromedriver().setup();
       // WebDriver driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);


        try {
            driver.get("http://royal-app.traces.com.br:8083/cliente/cadastro/1");
            driver.manage().window().maximize();
            Thread.sleep(3000); // aguarda a página carregar

            // Preenche o formulário
            driver.findElement(By.name("nome")).sendKeys(nome);
            driver.findElement(By.name("cpf")).sendKeys(cpf);
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("celular")).sendKeys(celular);
            driver.findElement(By.name("dataNascimento")).sendKeys(dataNascimento);
            driver.findElement(By.name("cep")).sendKeys(cep);
            Thread.sleep(1000); // espera o carregamento do endereço (se houver)

            driver.findElement(By.name("rua")).sendKeys(rua);
            driver.findElement(By.name("numero")).sendKeys(numero);
            driver.findElement(By.name("bairro")).sendKeys(bairro);
            driver.findElement(By.name("cidade")).sendKeys(cidade);

            // Botão Avançar
            WebElement botaoAvancar = driver.findElement(By.xpath("//button[contains(text(),'Avançar')]"));
            botaoAvancar.click();

            Thread.sleep(3000); // aguarda resposta
        } finally {
            driver.quit();
        }
    }
}

