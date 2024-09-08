public class Customer {
    private String id;
    private String name;
    private String contactNumber;
    private String address;

    public Customer(String id, String name, String contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return "Customer[id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", address=" + address + "]";
    }
}
