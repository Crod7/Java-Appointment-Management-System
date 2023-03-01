package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    /*Manages the buttons/ labels on screen-------------------------------------------------------------------------------------------*/
    @FXML
    private Button buttonLogin;
    @FXML
    private Label labelError;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelTimeZoneChangeable;

    @FXML
    private Label labelTimeZoneNonChangeable;

    @FXML
    private Label labelUsername;

    /** Manages the password entered in its text field and forwards it to another method to be used in authentication.
     */
    @FXML
    private TextField textFieldPassword;
    /** Manages the username entered in its text field and forwards it to another method to be used in authentication.
     */
    @FXML
    private TextField textFieldUsername;
    /** Sets the user for the current session
     */
    public static String username;

    /** This method is activated when pressing the login button on the login screen, it checks for authentication to make sure the USERID mataches its respective PASSWORD.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelUsername.setText(Lang.print("Username"));
        labelPassword.setText(Lang.print("Password"));
        labelTimeZoneNonChangeable.setText(Lang.print("Time")+" "+Lang.print("Zone"));
        buttonLogin.setText(Lang.print("Login"));
    }
    /** This method is activated when pressing the login button on the login screen, it checks for authentication to make sure the USERID mataches its respective PASSWORD.
     */
    public void loginButtonClick(ActionEvent e) throws IOException{
        try {
            ResultSet rs = Query.queryDB("SELECT * FROM users");
            while(rs.next()){
                if (textFieldUsername.getText().equals(rs.getString("User_Name"))) {
                    if (textFieldPassword.getText().equals(rs.getString("Password"))){
                        username = rs.getString("User_Name");
                        JDBC.openConnection();
                        Appointment.populateList();
                        Customer.populateList();
                        Form.changePageTo(e, "mainMenuViewAll.fxml");
                    } else{
                        labelError.setText(Lang.print("ERROR")+" "+Lang.print("Invalid")+" "+Lang.print("Password"));
                    }

                }else{
                    labelError.setText(Lang.print("ERROR")+" "+Lang.print("Invalid")+" "+Lang.print("Login"));
                }
            }
        }catch(SQLException se){
        }
    }
}