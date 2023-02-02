package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static configUtils.ConfigFramework.getBrowser;

public class PrincipalPO {

    public static By abaDepositos = By.xpath("//a[text()='Deposits']");
    public static By abaItems = By.xpath("//a[text()='Items']");
    public static By abaInventarios = By.xpath("//a[text()='Inventory']");

    public static By btnNewDeposit = By.xpath("//a[text()='New deposit']");
    public static By formNewDeposit = By.xpath("//form[@id='new_deposit']");

    public static By btnNewItem = By.xpath("//a[text()='New item']");
    public static By formNewItem = By.xpath("//form[@id='new_item']");

    public static By btnNewInventory= By.xpath("//a[text()='New inventory']");
    public static By formNewInventory = By.xpath("//form[@id='new_inventory']");


    public static By divDeposits = By.xpath("//div[@id='deposits']");
    public static By divItems = By.xpath("//div[@id='items']");
    public static By divInventory = By.xpath("//div[@id='inventories']");


    public static By inputName = By.id("deposit_name");
    public static By inputAddress = By.id("deposit_address");
    public static By inputCity = By.id("deposit_city");
    public static By inputState = By.id("deposit_state");
    public static By inputZipCode = By.id("deposit_zipcode");

    public static By inputItemName = By.id("item_name");
    public static By inputHeight = By.id("item_height");
    public static By inputWidth = By.id("item_width");
    public static By inputWeigh = By.id("item_weight");




    public static By inputItemCount = By.id("inventory_item_count");


    public static By btnCreateDeposit = By.xpath("//input[@value=\"Create Deposit\"]");
    public static By btnUpdateDeposit = By.xpath("//input[@value=\"Update Deposit\"]");
    public static By btnDestroyDeposit = By.xpath("//button[text()=\"Destroy this deposit\"]");


    public static By btnCreateItem = By.xpath("//input[@value=\"Create Item\"]");
    public static By btnUpdateItem = By.xpath("//input[@value=\"Update Item\"]");
    public static By btnDestroyItem = By.xpath("//button[text()=\"Destroy this item\"]");

    public static By btnCreateInventory = By.xpath("//input[@value=\"Create Inventory\"]");
    public static By btnUpdateInventory = By.xpath("//input[@value=\"Update Inventory\"]");
    public static By btnDestroyInventory = By.xpath("//button[text()=\"Destroy this inventory\"]");



    public static By linkShowDeposit = By.xpath("(//a[text()='Show this deposit'])[2]");
    public static By linkShowItem = By.xpath("(//a[text()='Show this item'])[2]");
    public static By linkShowinventory = By.xpath("(//a[text()='Show this inventory'])[2]");

    public static By linkEditDeposit = By.xpath("(//a[text()='Edit this deposit'])");
    public static By linkEditItem = By.xpath("(//a[text()='Edit this item'])");
    public static By linkEditInventory = By.xpath("(//a[text()='Edit this inventory'])");




    public static By textosNaTela(String texto) {
        return By.xpath("//*[contains(text(), '" + texto + "')]");
    }
}
