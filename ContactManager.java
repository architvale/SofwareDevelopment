import java.io.*;
import java.util.*;

public class ContactManager {
    private Map<String, Contact> contacts;
    private String fileName;

    public ContactManager(String fileName) {
        this.fileName = fileName;
        this.contacts = new HashMap<>();
        loadContactsFromFile();
    }

    private void loadContactsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (HashMap<String, Contact>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No contacts file found. Starting with an empty contact list.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }

    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.put(name, new Contact(name, phoneNumber, email));
        saveContactsToFile();
        System.out.println("Contact added successfully.");
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contact List:");
            for (Contact contact : contacts.values()) {
                System.out.println(contact);
            }
        }
    }

    public void editContact(String name, String phoneNumber, String email) {
        if (contacts.containsKey(name)) {
            Contact contact = contacts.get(name);
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contacts.put(name, contact);
            saveContactsToFile();
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void deleteContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            saveContactsToFile();
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager("contacts.ser");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Manager Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    contactManager.addContact(name, phoneNumber, email);
                    break;
                case 2:
                    contactManager.viewContacts();
                    break;
                case 3:
                    System.out.print("Enter name of contact to edit: ");
                    String editName = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String editPhoneNumber = scanner.nextLine();
                    System.out.print("Enter new email address: ");
                    String editEmail = scanner.nextLine();
                    contactManager.editContact(editName, editPhoneNumber, editEmail);
                    break;
                case 4:
                    System.out.print("Enter name of contact to delete: ");
                    String deleteName = scanner.nextLine();
                    contactManager.deleteContact(deleteName);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}
