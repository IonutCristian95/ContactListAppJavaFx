package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.dataModel.Contact;
import sample.dataModel.ContactData;

public class EditContactController {
    @FXML
    private TextField fNameEditContact;

    @FXML
    private TextField lNameEditContact;

    @FXML
    private TextField phoneNumberEditContact;

    @FXML
    private TextField notesEditContact;

    public Contact getNewContact() {
        String firstName = fNameEditContact.getText();
        String lastName = lNameEditContact.getText();
        String phoneNumber = phoneNumberEditContact.getText();
        String notes = notesEditContact.getText();

        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        return newContact;
    }

    public void editContact(Contact contact) {
        fNameEditContact.setText(contact.getFirstName());
        lNameEditContact.setText(contact.getLastName());
        phoneNumberEditContact.setText(contact.getPhoneNumber());
        notesEditContact.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(fNameEditContact.getText());
        contact.setLastName(lNameEditContact.getText());
        contact.setPhoneNumber(phoneNumberEditContact.getText());
        contact.setNotes(notesEditContact.getText());
    }
}
