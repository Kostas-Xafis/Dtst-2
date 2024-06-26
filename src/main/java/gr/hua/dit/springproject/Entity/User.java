package gr.hua.dit.springproject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Username"),
        @UniqueConstraint(columnNames = "Email")
})
public class User {

    public static Long inc = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_id")
    private Long id = (inc++);


    @NotBlank
    @Size(max = 40)
    @Column(name="Username")
    private String username;

    @Size(max = 40)
    @Column(name="Firstname")
    private String firstname;

    @Size(max = 40)
    @Column(name="Lastname")
    private String lastname;

    @NotBlank
    @Size(max = 120)
    @Column(name="Password")
    private String password;

    @NotBlank
    @Size(max = 80)
    @Column(name="Email")
    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @JsonManagedReference(value="RealEstate")
    private List<RealEstate> RealEstateList;

    @OneToMany(mappedBy="seller", cascade = CascadeType.ALL)
    @JsonManagedReference(value="seller")
    private List<TaxDeclaration> SellerTaxDeclarationList;

    @OneToMany(mappedBy="buyer", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value="buyer")
    private List<TaxDeclaration> BuyerTaxDeclarationList;

    @OneToMany(mappedBy="notary1", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value="notary1")
    private List<TaxDeclaration> SellerNotaryList;

    @OneToMany(mappedBy="notary2", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value="notary2")
    private List<TaxDeclaration> BuyerNotaryList;

    @OneToMany(mappedBy="user" , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference(value="UserRoles")
    private List<UserRoles> UserRolesList;

    public User(Long id, String firstname, String lastname, String password, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean hasRole(EnumRole enumRole) {
        for (UserRoles userRoles : UserRolesList) {
            if(userRoles.getRole().getName().equals(enumRole)) return true;
        }
        return false;
    }

    public List<RealEstate> getRealEstateList() {
        return RealEstateList;
    }

    public void setRealEstateList(List<RealEstate> realEstateList) {
        RealEstateList = realEstateList;
    }

    public Boolean hasRealEstate(Long id) {
        for (RealEstate realEstate : RealEstateList) {
            if(realEstate.getId().equals(id)) return true;
        }
        return false;
    }

    public RealEstate getRealEstate(Long id) {
        for (RealEstate realEstate : RealEstateList) {
            if(realEstate.getId().equals(id)) return realEstate;
        }
        return null;
    }

    public List<TaxDeclaration> getSellerTaxDeclarationList() {
        return SellerTaxDeclarationList;
    }

    public void setSellerTaxDeclarationList(List<TaxDeclaration> sellerTaxDeclarationList) {
        SellerTaxDeclarationList = sellerTaxDeclarationList;
    }

    public List<TaxDeclaration> getBuyerTaxDeclarationList() {
        return BuyerTaxDeclarationList;
    }

    public void setBuyerTaxDeclarationList(List<TaxDeclaration> buyerTaxDeclarationList) {
        BuyerTaxDeclarationList = buyerTaxDeclarationList;
    }

    public List<TaxDeclaration> getSellerNotaryList() {
        return SellerNotaryList;
    }

    public void setSellerNotaryList(List<TaxDeclaration> sellerNotaryList) {
        SellerNotaryList = sellerNotaryList;
    }

    public List<TaxDeclaration> getBuyerNotaryList() {
        return BuyerNotaryList;
    }

    public void setBuyerNotaryList(List<TaxDeclaration> buyerNotaryList) {
        BuyerNotaryList = buyerNotaryList;
    }

    public List<TaxDeclaration> getAllTaxes() {
        return Stream.of(getSellerNotaryList(), getBuyerTaxDeclarationList(),
                getBuyerNotaryList()).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<UserRoles> getUserRolesList() {
        return UserRolesList;
    }
    public void setUserRolesWithSet(Set<Role> roles) {
        List<UserRoles> userRolesList = new ArrayList<>();
        roles.forEach(role -> userRolesList.add(new UserRoles(this, role)));
        UserRolesList = userRolesList;
    }

    public void setUserRolesList(List<UserRoles> userRolesList) {
        UserRolesList = userRolesList;
    }

    @Transient
    private int print_depth = 0;
    @Override
    public String toString() {
        if (print_depth >= 1) return "User{...}";
        print_depth++;
        String str = "User{\n" +
                "id=" + id + ",\n" +
                "username='" + username + "',\n" +
                "firstname='" + firstname + "',\n" +
                "lastname='" + lastname + "',\n" +
                "password='" + password + "',\n" +
                "email='" + email + "',\n" +
                "roles=[" + UserRolesList + "],\n" +
                '}';
        print_depth--;
        return str;
    }

    public static User objConvert(Object[] obj) {
        User u = new User();
        u.setId((Long) obj[0]);
        u.setFirstname((String) obj[1]);
        u.setLastname((String) obj[2]);
        u.setPassword((String) obj[3]);
        u.setEmail((String) obj[4]);
        return u;
    }

    public void update(HashMap<String, Object> attr) {
        attr.forEach((str, obj) -> {
            String val = (String) obj;
            switch (str) {
                case "email" -> this.setEmail(val);
                case "firstname" -> this.setFirstname(val);
                case "lastname" -> this.setLastname(val);
                case "password" -> this.setPassword(val);
            }
        });
    }
}
