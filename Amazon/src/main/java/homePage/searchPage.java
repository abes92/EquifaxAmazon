package homePage;

import common.WebAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static homePage.Locators.*;
public class searchPage extends WebAPI {

    @FindBy(how = How.ID, using = webElementSearchBox)
    private WebElement searchBox;
    @FindBy(how = How.XPATH, using = webElementAllOptions)
    private WebElement AllOptions;
    @FindBy(how = How.XPATH, using = webElementSearchAll)
    private WebElement ClickAll;
    @FindBy(how = How.ID, using = webElementClickSearchBar)
    private WebElement searchBar;
    @FindBy(how = How.XPATH, using = webElementClickItem)
    private WebElement clickItem;
    @FindBy(how = How.XPATH, using = webElementAddToCart)
    private WebElement addToCart;
    @FindBy(how = How.XPATH, using = webElementClickCart)
    private WebElement clickCart;
    @FindBy(how = How.XPATH, using = webElementProceedToCheckOut)
    private WebElement ProceedToCheckOut;


public void setSearchBox(){
   searchBox.sendKeys("qa testing book for beginners ");
}
    public void setSearchBar(){
    searchBar.click();
    }
   public void setClickItem(){
    clickItem.click();
   }
   public void setAddToCart(){
    addToCart.click();
   }
   public void setClickCart(){
    clickCart.click();

   }
   public void setProceedToCheckOut(){
       ProceedToCheckOut.click();
   }

    public void selectAnyElements(String Xpath,String GXpath,String element){
        driver.findElement(By.xpath(Xpath)).click();
        List<WebElement>  list=driver.findElements(By.xpath(GXpath));
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().contains(element)){
                list.get(i).click();
                break;
            }
        }
    }
}
