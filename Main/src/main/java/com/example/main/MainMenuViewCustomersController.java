package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class controls the Main Menu View By Customer page.
 */
public class MainMenuViewCustomersController implements Initializable {
    /*Manages the variables of the buttons and labels on screen-------------------------------------------------------------------------------------------*/
    /** This button will take the user to the add appointment page.
     */
    @FXML
    private Button buttonAddCustomer;
    /** This button will delete a selected appointment. If no appointment is selected, will display an error message instructing the user to make a selection first.
     */
    @FXML
    private Button buttonDeleteCustomer;
    /** This button will take the user to the login page.
     */
    @FXML
    private Button buttonLogout;
    /** This button will take the user to the modify appointment page.
     */
    @FXML
    private Button buttonModifyCustomer;
    /** This button will take the user to the reports page.
     */
    @FXML
    private Button buttonReports;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will show all appointments.
     */
    @FXML
    private RadioButton buttonViewAll;
    /** This variable holds a radio button, and is used to retrieve data about UI interactions with the button. This button will sort appointments by customers.
     */
    @FXML
    private RadioButton buttonViewCustomers;
    /** This variable holds the label for title of the page.
     */
    @FXML
    private Label labelAppointmentSchedule;
    /** This label holds text for the sub-header.
     */
    @FXML
    private Label labelSubHeader;

    /*Manages what columns go into table------------------------------------------------------------------------------------------------------------------------*/
    /** This variable holds a column for the customer object, the address column.
     */
    @FXML
    private TableColumn<Customer, String> addressColumn;
    /** This variable holds a column for the customer object, the customer ID column.
     */
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    /** This variable holds a column for the customer object, the customer name column.
     */
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    /** This variable holds a column for the customer object, division ID column.
     */
    @FXML
    private TableColumn<Customer, Integer> divisionIdColumn;
    /** This variable holds a column for the customer object, the phone number column.
     */
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    /** This variable holds a column for the customer object, the postal code column.
     */
    @FXML
    private TableColumn<Customer, String> postalColumn;
    /** This variable holds the table view that will represent the appointment data and it's columns on the main page.
     */
    @FXML
    private TableView<Customer> tableviewMainMenuTable;
    /** This variable holds the toggle group for the radio buttons on the form.
     */
    @FXML
    private ToggleGroup viewGroup;

    /*Manages the table view------------------------------------------------------------------------------------------------------------------------*/
    /** This method sets up the two tables on the mainForm page.
     On initialization the method will set up the Part table and Product table.
     It will also handle the resource bundle that controls the language settings.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets the table view-----------------------------------------------------------------------------------------
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer ,Integer>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        divisionIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("divisionId"));
        tableviewMainMenuTable.setItems(Customer.getAllCustomers());
        //changes the language of each column-------------------------------------------------------------------
        customerIdColumn.setText(Lang.print("Customer")+" "+Lang.print("ID"));
        customerNameColumn.setText(Lang.print("Customer")+" "+ Lang.print("Name"));
        addressColumn.setText(Lang.print("Address"));
        postalColumn.setText(Lang.print("Postal")+" "+Lang.print("Code"));
        phoneColumn.setText(Lang.print("Phone")+" "+Lang.print("Number"));
        divisionIdColumn.setText(Lang.print("Division"));
        //changes the language of each label--------------------------------------------------------------------
        labelAppointmentSchedule.setText(Lang.print("Customers"));
        buttonAddCustomer.setText(Lang.print("Add")+" "+Lang.print("Customer"));
        buttonDeleteCustomer.setText(Lang.print("Delete")+" "+Lang.print("Customer"));
        buttonLogout.setText(Lang.print("Logout"));
        buttonModifyCustomer.setText(Lang.print("Modify")+" "+Lang.print("Customer"));
        buttonReports.setText(Lang.print("Reports"));
        buttonViewAll.setText(Lang.print("View")+" "+Lang.print("Appointments"));
        buttonViewCustomers.setText(Lang.print("View")+" "+Lang.print("Customers"));
    }

    /*Manages all the methods that control navigation between different forms------------------------------------------------------------------------*/
    /** This function is linked to a button, and when the button is pressed will take the user to the MainMenuViewAll form.
     */
    public void viewAllButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "mainMenuViewAll.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the AddAppointment form.
     */
    public void addCustomerButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "addCustomer.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the ModifyCustomer form with all the data passed through it from the selected Customer to be modified.
     */
    public void modifyCustomerButtonClick(ActionEvent e) throws IOException {
        /** This will hold the object selected on the main menu and pass the data into the text fields of the modify appointment form.
         */
        Customer selectedCustomer = tableviewMainMenuTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            ModifyCustomerController.customerIdData = selectedCustomer.getCustomerId();
            ModifyCustomerController.customerNameData = selectedCustomer.getCustomerName();
            ModifyCustomerController.addressData = selectedCustomer.getAddress();
            ModifyCustomerController.postalCodeData = selectedCustomer.getPostalCode();
            ModifyCustomerController.phoneData = selectedCustomer.getPhone();
            ModifyCustomerController.createDateData = selectedCustomer.getCreateDate();
            ModifyCustomerController.createdByData = selectedCustomer.getCreatedBy();
            ModifyCustomerController.divisionIdData = selectedCustomer.getDivisionId();
            ModifyCustomerController.selectedCus = selectedCustomer;
            Form.changePageTo(e, "modifyCustomer.fxml");
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Customer")+" "+Lang.print("to")+" "+Lang.print("modify")+".");
        }
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the ReportsContactSchedule form.
     */
    public void reportsButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "reportsContactSchedule.fxml");
    }
    /** This function is linked to a button, and when the button is pressed will take the user to the Login form.
     */
    public void logoutButtonClick(ActionEvent e) throws IOException {
        Form.changePageTo(e, "login.fxml");
    }
    /** This function is linked to a button, and when the button is will delete the currently selected appointment.
     */
    public void deleteCustomerButtonClick(ActionEvent e) throws IOException {
        Customer selectedCustomer = tableviewMainMenuTable.getSelectionModel().getSelectedItem();
        //This will check to make sure that the customer has no appointments linked to them, if they have linked appointments, it will not allow their deletion
        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Lang.print("Customers"));
            alert.setHeaderText(Lang.print("Delete"));
            alert.setContentText(Lang.print("Do")+" "+Lang.print("you")+" "+Lang.print("want")+" "+Lang.print("to")+" "+Lang.print("delete")
                    +" "+Lang.print("this")+" "+Lang.print("Customer")+"?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Customer.deleteCustomer(selectedCustomer);
            }
        } else {
            ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Customer")+" "+Lang.print("to")+" "+Lang.print("delete")+".");
        }
    }
    /*
    public void deleteCustomerButtonClick(ActionEvent e) throws IOException {
        Customer selectedCustomer = tableviewMainMenuTable.getSelectionModel().getSelectedItem();
        //This will check to make sure that the customer has no appointments linked to them, if they have linked appointments, it will not allow their deletion
        if (Appointment.checkIfCustomerIsEmpty(selectedCustomer) == 0){
            if (selectedCustomer != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(Lang.print("Customers"));
                alert.setHeaderText(Lang.print("Delete"));
                alert.setContentText(Lang.print("Do")+" "+Lang.print("you")+" "+Lang.print("want")+" "+Lang.print("to")+" "+Lang.print("delete")
                        +" "+Lang.print("this")+" "+Lang.print("Customer")+"?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    Customer.deleteCustomer(selectedCustomer);
                }
            } else {
                ErrorMessage.msg(Lang.print("Please")+" "+Lang.print("select")+" "+Lang.print("a")+" "+Lang.print("Customer")+" "+Lang.print("to")+" "+Lang.print("delete")+".");
            }
        } else {
            ErrorMessage.msg2(Lang.print("Please")+" "+Lang.print("make")+" "+Lang.print("sure")+" "+Lang.print("all")+" "+
                    Lang.print("associated")+" "+Lang.print("appointments")+" "+Lang.print("linked")+" "+Lang.print("to")+" "+
                    Lang.print("customer")+" "+Lang.print("are")+" "+Lang.print("deleted")+" "+Lang.print("first")+".");
        }
    }
    */
}