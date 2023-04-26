package org.example;

import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSuite {
    static  String getExpectedReferAFriendMsg = "Your message has not been sent.";
    static String getExpectedVotingMsg = "Voting is not working.";
    static String getExpectedRegistrationCompleteMsg = "Your registration was not completed.";
    static String getExpectedEmailAFriendMsg = "Your message has not been sent.";
    static String getExpectedCompareAProductMsg = "You have two items to compare.";
    static String getExpectedProductInShoppingCartMsg = "Your Shopping Cart is empty!";
    static String getExpectedCommunityPollMsg = "You participated in community poll successfully.";


    protected static WebDriver driver;
    public static void clickOnElement (By by){
        driver.findElement(by).click();
    }
    public static void typeText (By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    public static String getTextFromElement (By by){
        return driver.findElement(by).getText();
    }
    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
    @BeforeMethod
    public  static void  openBrowser(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //maximizing the window
        driver.manage().window().maximize();
        //Typing url
        driver.get("https://demo.nopcommerce.com/");
    }
    @AfterMethod
    public static void closeBrowser(){
        //to close the browser
        driver.close();
    }
    @Test
    public static void verifyRegisteredUserShouldBeAbleToReferAProductToAFriendSuccessfully(){

        //click on register
        clickOnElement(By.className("ico-register"));
        //type firstname
        typeText(By.id("FirstName"), "FirstnameTest");
        //type lastname
        typeText(By.id("LastName"), "FirstnameTest");
        //type email address
        typeText(By.id("Email"), "vb3@gmail.com");
        //type password
        typeText(By.id("Password"), "test1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"), "test1234");
        //click on register button
        clickOnElement(By.id("register-button"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //click on login
        clickOnElement(By.className("ico-login"));
        //type email address
        typeText(By.id("Email"), "vb3@gmail.com");
        //type password
        typeText(By.id("Password"), "test1234");
        //click on LOG IN button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
        //click on Build your own computer product
        clickOnElement(By.xpath("//a[@href=\"/build-your-own-computer\"][text()=\"Build your own computer\"]"));
        //click on email a friend
        clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]"));
        //type Friend's email
        typeText(By.id("FriendEmail"),"vb20" +timestamp() +"@gmail.com");
        //type personal message
        typeText(By.id("PersonalMessage"),"Hey, check out this awesome website!");
        //click on send email
        clickOnElement(By.xpath("//button[@name=\"send-email\"]"));
        //print message
        String actualMessage;
        actualMessage = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        System.out.println("My Message: "+actualMessage);
        Assert.assertEquals(actualMessage,getExpectedReferAFriendMsg,"Your message has not been sent.");

    }
    @Test
    public static void RegisteredUserShouldVoteSuccessfully(){

        //click on register
        clickOnElement(By.className("ico-register"));
        //type firstname
        typeText(By.id("FirstName"), "FirstnameTest");
        //type lastname
        typeText(By.id("LastName"), "LastnameTest");
        //type email address
        typeText(By.id("Email"), "vb5@gmail.com");
        //type password
        typeText(By.id("Password"), "test1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"), "test1234");
        //click on register button
        clickOnElement(By.id("register-button"));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //click on login
        clickOnElement(By.className("ico-login"));
        //type email address
        typeText(By.id("Email"), "vb5@gmail.com");
        //type password
        typeText(By.id("Password"), "test1234");
        //click on LOG IN button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));
        //click on Good
        clickOnElement(By.name("pollanswers-1"));
        //click on Vote
        clickOnElement(By.xpath("//button[@class=\"button-2 vote-poll-button\"]"));
        //print out message
        getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        String actualMessage;
        actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        Assert.assertEquals(actualMessage,getExpectedVotingMsg,"Voting is not working.");

    }
    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully(){

        //click on register button
        clickOnElement(By.className("ico-register"));
        //type firstname
        typeText(By.id("FirstName"), "FirstnameTest");
        //type lastname
        typeText(By.id("LastName"), "LastNametest");
        //Timestamp timestamp;
        //static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //type email address
        typeText(By.id("Email"), "testmail" + timestamp() + "@gmail.com");
        //type password
        typeText(By.id("Password"), "test1234");
        //type confirm password
        typeText(By.id("ConfirmPassword"), "test1234");
        //click on register button
        clickOnElement(By.id("register-button"));
        //print message
        String actualMessage;
        actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        //print message
        System.out.println("My message " + actualMessage);
        Assert.assertEquals(actualMessage,getExpectedRegistrationCompleteMsg,"Your registration was not completed.");

    }
    @Test
    public static void verifyUserShouldBeAbleToEmailAFriendSuccessfully(){

        //click on ADD TO CART button of Apple MacBook Pro 13-inch
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div/div[2]/div[3]/div[2]/button[1]"));
        //click on Email a friend
        clickOnElement(By.xpath("//div[@class='email-a-friend']"));
        //type Friend's email
        typeText(By.className("friend-email"),"sp@gmail.com");
        //type your email address
        typeText(By.className("your-email"),"dp12@gmail.com");
        //click on send email
        clickOnElement(By.name("send-email"));
        //get string error message
        String actualMessage;
        actualMessage = getTextFromElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li"));
        //print message
        System.out.println("Error Message:"  +actualMessage);
        //for Expected and actual result
        Assert.assertEquals(actualMessage,getExpectedEmailAFriendMsg,"Your message has not been sent.");
    }
    @Test
    public static void verifyUserShouldBeAbleToCompareTheTwoProductsSuccessfully(){

        //click on Add to compare list for HTC One M8 Android L 5.0 Lollipop
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));

        //appear green line bar
        clickOnElement(By.xpath("//span[@class= \"close\"]"));

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //click on Add to compare list for $25 Virtual Gift Card
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));

        //close green line bar
        clickOnElement(By.xpath("//span[@class= \"close\"]"));

        //for time wait
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]")));

        //click green line
        clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]"));

        //get text of $25 Virtual Gift Card and HTC One M8 Android L 5.0 Lollipop
        String name1 = getTextFromElement(By.xpath("//a[@href=\"/25-virtual-gift-card\"][text()=\"$25 Virtual Gift Card\"]"));
        System.out.println(name1);
        String name2 = getTextFromElement(By.xpath("//a[@href=\"/htc-one-m8-android-l-50-lollipop\"][text()=\"HTC One M8 Android L 5.0 Lollipop\"]"));
        System.out.println(name2);

        //click on CLEAR LIST
        clickOnElement(By.xpath("//A[@href='#'][text()='Clear list']"));

        //get text for actual message
        String actualMessage = getTextFromElement(By.xpath("//div[@class='no-data'][text()='You have no items to compare.']"));
        System.out.println("My Message :" +actualMessage);

        Assert.assertEquals(actualMessage,getExpectedCompareAProductMsg,"You have two items to compare.");
    }
    @Test
    public static void verifyUserShouldBeAbleToSeeProductInShoppingCartSuccessfully(){
        //click on Electronics
        clickOnElement(By.linkText("Electronics"));

        //click on camera & photo
        clickOnElement(By.linkText("Camera & photo"));

        //click on leica T miirrorless digital camera
        clickOnElement(By.xpath("(//BUTTON[@type='button'][text()='Add to cart'])[2]"));

        //click on add to cart button
        clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a"));

        //print out actual message
        String actualMessage = getTextFromElement(By.xpath("//td[@class='product']//a[@href='/leica-t-mirrorless-digital-camera'][text()='Leica T Mirrorless Digital Camera']"));
        System.out.println("My Message:" +actualMessage);

        Assert.assertEquals(actualMessage,getExpectedProductInShoppingCartMsg,"Your Shopping Cart is empty!");
    }
    @Test
    public static void verifyNonRegisteredUserShouldBeAbleToUseCommunityPollSuccessfully(){

        //click on good
        clickOnElement(By.id("pollanswers-2"));

        //click on VOTE
        clickOnElement(By.id("vote-poll-1"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"block-poll-vote-error-1\"]")));

        //print out voting message
        String actualMessage = getTextFromElement(By.xpath("//div[@id=\"block-poll-vote-error-1\"]"));
        System.out.println("My Message:"+actualMessage);

        Assert.assertEquals(actualMessage,getExpectedCommunityPollMsg,"You participated in community poll successfully. ");
    }
}


