import java.util.Properties;

public class LogInpage
{
    Browser br;
    LoginPage loginPage;
    WebDriver driver ;
    Properties browser ;
    Properties testCase;

    @BeforeSuite
    public void readBrowser(){
        browser = TestDataReader.readProperties("Browser.properties");
        br = new Browser(browser.getProperty("browser"));
    }

    @BeforeMethod
    public void readTestData(){
        br.launchBrowser();
        br.maximize();
        driver = br.getDriver();
        loginPage = new LoginPage(br.getDriver());
    }
    @Test
    public void tc001_parabank_invalidlogin(){
        testCase = TestDataReader.readProperties("tc001.properties");
        br.navigateUrl(browser.getProperty("url"));
        loginPage.enterUsername(testCase.getProperty("username"));
        loginPage.enterPassword(testCase.getProperty("password"));
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorElement().getText(), testCase.getProperty("errorMsg"));
    }

    @Test
    public void tc001_parabank_loginwithoutPassword(){
        testCase = TestDataReader.readProperties("tc001.properties");
        br.navigateUrl(browser.getProperty("url"));
        loginPage.enterUsername(testCase.getProperty("username"));
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorElement().getText(), testCase.getProperty("errorMsg"));
    }

    // @Test - only enter username -> click login -> validate error
    // @Test - only enter password -> click login -> validate error
    // @Test -> blank -> click login -> validate error




}


}
