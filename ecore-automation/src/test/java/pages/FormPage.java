package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class FormPage {
    private WebDriver browser;

    public FormPage(WebDriver browser) {
        this.browser = browser;
    }


    public FormPage goToInvoiceFormPage() {
        String mainWindowHandle = browser.getWindowHandle();
        WebElement invoiceLink = new WebDriverWait(browser, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/invoice/0'][target='_blank']")));
        invoiceLink.click();
        new WebDriverWait(browser, 15)
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : browser.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                browser.switchTo().window(handle);
                new WebDriverWait(browser, 10)
                        //.until(ExpectedConditions.titleContains("Invoice Details"));
                        .until(ExpectedConditions.textMatches(By.cssSelector("h2.mt-5"), Pattern.compile("Invoice Details")));
                break;
            }
        }
        return this;
    }




    public FormPage validateInvoiceForm() {
        String hotelName = browser.findElement(By.cssSelector("h4.mt-5")).getText();
        Assertions.assertEquals("Rendezvous Hotel", hotelName);

        WebElement invoiceDate = browser.findElement(By.xpath("//li[contains(span/text(), 'Invoice Date:')]/span"));
        Assertions.assertEquals("Invoice Date:", invoiceDate.getText());

        //WebElement dueDate = browser.findElement(By.xpath("//li[span[contains(text(), 'Due Date:')]/span"));
        //Assertions.assertEquals("Due Date:", dueDate.getText());

        String expectedDueDate = "Due Date:";
        WebElement dueDate = browser.findElement(By.xpath("//li[span[contains(text(), 'Due Date:')]]/span[last()]"));
        Assertions.assertEquals(expectedDueDate, dueDate.getAttribute("innerText"));


        WebElement invoiceNumber = browser.findElement(By.cssSelector("h6.mt-2"));
        Assertions.assertEquals("Invoice #110 details", invoiceNumber.getText());

        WebElement bookingCode = browser.findElement(By.cssSelector("td.font-weight-bold + td"));
        Assertions.assertEquals("0875", bookingCode.getText());

        WebElement room = browser.findElement(By.xpath("//td[text()='Superior Double']"));
        Assertions.assertEquals("Superior Double", room.getText());

        WebElement totalStayCount = browser.findElement(By.xpath("//td[text()='1']"));
        Assertions.assertEquals("1", totalStayCount.getText());

        WebElement totalStayAmount = browser.findElement(By.xpath("//td[text()='$150']"));
        Assertions.assertEquals("$150", totalStayAmount.getText());

        WebElement checkin = browser.findElement(By.xpath("//td[text()='14/01/2018']"));
        Assertions.assertEquals("14/01/2018", checkin.getText());

        WebElement checkout = browser.findElement(By.xpath("//td[text()='15/01/2018']"));
        Assertions.assertEquals("15/01/2018", checkout.getText());

        return this;
    }
}



