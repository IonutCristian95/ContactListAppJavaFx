package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.dataModel.Contact;
import sample.dataModel.ContactData;

public class AddContactController {
    @FXML
    private TextField fNameAddContact;

    @FXML
    private TextField lNameAddContact;

    @FXML
    private TextField phoneNumberAddContact;

    @FXML
    private TextField notesAddContact;

    public Contact getNewContact() {
        String firstName = fNameAddContact.getText();
        String lastName = lNameAddContact.getText();
        String phoneNumber = phoneNumberAddContact.getText();
        String notes = notesAddContact.getText();

        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        return newContact;
    }

    public void editContact(Contact contact) {
        fNameAddContact.setText(contact.getFirstName());
        lNameAddContact.setText(contact.getLastName());
        phoneNumberAddContact.setText(contact.getPhoneNumber());
        notesAddContact.setText(contact.getNotes());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(fNameAddContact.getText());
        contact.setLastName(lNameAddContact.getText());
        contact.setPhoneNumber(phoneNumberAddContact.getText());
        contact.setNotes(notesAddContact.getText());
    }
}
