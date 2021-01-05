package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.dataModel.Contact;
import sample.dataModel.ContactData;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TableView<Contact> contactTableView;

    private ContactData data;

    public void initialize() throws IOException {
        data = new ContactData();
        data.loadContacts();
        contactTableView.setItems(data.getContacts());
    }

    @FXML
    public void addNewContact(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add a new contact");
        dialog.setHeaderText("Use this dialog to create a new contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addContact.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load()); //was root before
        }catch(IOException e){
            System.out.println("Could not load the dialog!");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            AddContactController addContactController = fxmlLoader.getController();
            Contact newContact = addContactController.getNewContact();
//            contactTableView.getSelectionModel().select(newContact);
            data.addContact(newContact);
            data.saveContacts();
        }else if(result.isPresent() && result.get() == ButtonType.CANCEL){
            System.out.println("Cancelled operation to add a new contact");
        }
    }

    @FXML
    public void editContact(){
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem();
        if(selectedContact == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected!");
            alert.setHeaderText(null);
            alert.setContentText("Select the contact you want to edit.");

            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit contact");
        dialog.setHeaderText("Use this dialog to edit the contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("edit contact.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Could not edit the file!");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        EditContactController editContactController = fxmlLoader.getController();
        editContactController.editContact(selectedContact);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            editContactController.updateContact(selectedContact);
            data.saveContacts();
        }
    }

    @FXML
    public void deleteContact(){
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem();


        if(selectedContact == null){
            System.out.println("There is no contact.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected.");
            alert.setHeaderText(null);
            alert.setContentText("You must first select a contact to delete.");
            alert.showAndWait();
            return;
        }


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete contact");
        alert.setHeaderText("Delete contact " + ((selectedContact!=null) ? selectedContact.getFullName() : "") + " ?");
        alert.setContentText("Are you sure? Press OK to confirm.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteContact(selectedContact);
        }
    }

    @FXML
    public void handleExit() throws IOException {
        System.out.println("Exit button selected!");
        Platform.exit();
    }
}
