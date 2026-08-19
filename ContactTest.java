import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void testContactCreation() {
        Contact contact = new Contact(
                "1234567890",
                "Jordon",
                "Osborne",
                "6145551234",
                "123 Main Street");

        assertEquals("1234567890", contact.getContactId());
        assertEquals("Jordon", contact.getFirstName());
        assertEquals("Osborne", contact.getLastName());
        assertEquals("6145551234", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "Jordon", "Osborne",
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testContactIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "Jordon", "Osborne",
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", null, "Osborne",
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testFirstNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Christopher", "Osborne",
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Jordon", null,
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testLastNameCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Jordon", "Longlastname",
                        "6145551234", "123 Main Street"));
    }

    @Test
    void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Jordon", "Osborne",
                        null, "123 Main Street"));
    }

    @Test
    void testPhoneMustBeExactlyTenDigits() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("1", "Jordon", "Osborne",
                                "614555123", "123 Main Street")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("1", "Jordon", "Osborne",
                                "61455512345", "123 Main Street")),
                () -> assertThrows(IllegalArgumentException.class, () ->
                        new Contact("1", "Jordon", "Osborne",
                                "61455512AB", "123 Main Street"))
        );
    }

    @Test
    void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Jordon", "Osborne",
                        "6145551234", null));
    }

    @Test
    void testAddressCannotBeLongerThanThirtyCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("1", "Jordon", "Osborne",
                        "6145551234",
                        "1234567890123456789012345678901"));
    }

    @Test
    void testContactFieldsCanBeUpdated() {
        Contact contact = new Contact(
                "1", "Jordon", "Osborne",
                "6145551234", "123 Main Street");

        contact.setFirstName("Jordan");
        contact.setLastName("Smith");
        contact.setPhone("7405559876");
        contact.setAddress("456 Oak Avenue");

        assertEquals("Jordan", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("7405559876", contact.getPhone());
        assertEquals("456 Oak Avenue", contact.getAddress());
    }
}
