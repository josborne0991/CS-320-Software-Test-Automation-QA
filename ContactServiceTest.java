import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact(
                "1",
                "Jordon",
                "Osborne",
                "6145551234",
                "123 Main Street");
    }

    @Test
    void testAddContact() {
        service.addContact(contact);

        assertEquals(1, service.getContactCount());
        assertSame(contact, service.getContact("1"));
    }

    @Test
    void testCannotAddDuplicateContactId() {
        service.addContact(contact);

        Contact duplicate = new Contact(
                "1",
                "Georgia",
                "Osborne",
                "6145554321",
                "456 Oak Avenue");

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(duplicate));
    }

    @Test
    void testDeleteContact() {
        service.addContact(contact);
        service.deleteContact("1");

        assertEquals(0, service.getContactCount());
        assertNull(service.getContact("1"));
    }

    @Test
    void testDeleteMissingContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.deleteContact("99"));
    }

    @Test
    void testUpdateFirstName() {
        service.addContact(contact);
        service.updateFirstName("1", "Jordan");

        assertEquals("Jordan",
                service.getContact("1").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(contact);
        service.updateLastName("1", "Smith");

        assertEquals("Smith",
                service.getContact("1").getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(contact);
        service.updatePhone("1", "7405559876");

        assertEquals("7405559876",
                service.getContact("1").getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(contact);
        service.updateAddress("1", "456 Oak Avenue");

        assertEquals("456 Oak Avenue",
                service.getContact("1").getAddress());
    }

    @Test
    void testUpdateMissingContactThrowsException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateFirstName("99", "Jordan")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateLastName("99", "Smith")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updatePhone("99", "7405559876")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateAddress("99", "456 Oak Avenue"))
        );
    }

    @Test
    void testInvalidUpdatesThrowExceptions() {
        service.addContact(contact);

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateFirstName("1", null)),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateLastName("1", "Longlastname")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updatePhone("1", "12345")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        service.updateAddress(
                                "1",
                                "1234567890123456789012345678901"))
        );
    }
}
