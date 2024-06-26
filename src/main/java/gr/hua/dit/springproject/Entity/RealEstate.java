package gr.hua.dit.springproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.HashMap;

@Entity
@Table(name="real_estate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Real_estate_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="Seller_id")
    @JsonBackReference(value="RealEstate")
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Tax_Declaration_id")
    @JsonManagedReference(value="RealEstateTaxId")
    private TaxDeclaration taxDeclaration;

    @Column(name="Address")
    private String address;

    @Column(name="Road_number")
    private int road_number;

    @Column(name="Area_code")
    private int area_code;

    @Column(name="Area_size")
    private int area_size;

    @Column(name="Description")
    private String description;

    public RealEstate() {}

    public RealEstate(Long id, User seller, String address, int road_number, int area_code, int area_size, String description) {
        this.id = id;
        this.seller = seller;
        this.address = address;
        this.road_number = road_number;
        this.area_code = area_code;
        this.area_size = area_size;
        this.description = description;
        this.taxDeclaration = null;
    }

    public RealEstate(String address, int road_number, int area_code, int area_size, String description) {
        this.id = null;
        this.address = address;
        this.road_number = road_number;
        this.area_code = area_code;
        this.area_size = area_size;
        this.description = description;
        this.seller = null;
        this.taxDeclaration = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSeller() {
        return seller != null ? seller : new User();
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public TaxDeclaration getTaxDeclaration() {
        return taxDeclaration;
    }

    public void setTaxDeclaration(TaxDeclaration taxDeclaration) {
        this.taxDeclaration = taxDeclaration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoad_number() {
        return road_number;
    }

    public void setRoad_number(int road_number) {
        this.road_number = road_number;
    }

    public int getArea_code() {
        return area_code;
    }

    public void setArea_code(int area_code) {
        this.area_code = area_code;
    }

    public int getArea_size() {
        return area_size;
    }

    public void setArea_size(int area_size) {
        this.area_size = area_size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    private int print_depth = 0;
    @Override
    public String toString() {
        if (print_depth >= 1) return "RealEstate{...}";
        print_depth++;
        String str = "RealEstate{\n" +
                "\tid=" + id + ",\n" +
                "\tseller=" + seller + ",\n" +
                "\ttaxDeclaration=" + (taxDeclaration != null ? taxDeclaration.toString() : "null") + ",\n" +
                "\taddress='" + address + "',\n" +
                "\troad_number=" + road_number + ",\n" +
                "\tarea_code=" + area_code + ",\n" +
                "\tarea_size=" + area_size + ",\n" +
                "\tdescription='" + description + "',\n" +
                '}';
        print_depth--;
        return str;
    }

    public void update(HashMap<String, Object> attr) {
        attr.forEach((str, obj) -> {
            switch (str) {
                case "address" -> this.setAddress((String) obj);
                case "road_number" -> this.setRoad_number((int) obj);
                case "area_code" -> this.setArea_code((int) obj);
                case "area_size" -> this.setArea_size((int) obj);
                case "description" -> this.setDescription((String) obj);
            }
        });
    }
}
