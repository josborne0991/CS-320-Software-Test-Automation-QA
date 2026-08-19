import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }

        String contactId = contact.getContactId();

        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException(
                    "A contact with this ID already exists.");
        }

        contacts.put(contactId, contact);
    }

    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID was not found.");
        }

        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        getContactOrThrow(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getContactOrThrow(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getContactOrThrow(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getContactOrThrow(contactId).setAddress(address);
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public int getContactCount() {
        return contacts.size();
    }

    private Contact getContactOrThrow(String contactId) {
        Contact contact = contacts.get(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID was not found.");
        }

        return contact;
    }
}
