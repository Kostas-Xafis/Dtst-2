package gr.hua.dit.springproject.Controller;

import gr.hua.dit.springproject.Config.AuthTokenFilter;
import gr.hua.dit.springproject.DAO.RealEstateDAOImpl;
import gr.hua.dit.springproject.DAO.TaxDeclarationDAOImpl;
import gr.hua.dit.springproject.DAO.UserDAOImpl;
import gr.hua.dit.springproject.Entity.EnumRole;
import gr.hua.dit.springproject.Entity.RealEstate;
import gr.hua.dit.springproject.Entity.TaxDeclaration;
import gr.hua.dit.springproject.Entity.User;
import gr.hua.dit.springproject.Payload.Response.MessageResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/real_estate")
public class RealEstateController {

    private static final Logger logger = LoggerFactory.getLogger(RealEstateController.class);

    @Autowired
    RealEstateDAOImpl realEstateDAOImpl;

    @Autowired
    TaxDeclarationDAOImpl taxDeclarationDAOImpl;

    @Autowired
    UserDAOImpl userDAOImpl;

    @Autowired
    AuthTokenFilter authTokenFilter;

    @GetMapping()
    public List<RealEstate> getAllRealEstates(@Valid @RequestHeader HashMap<String, String> request) {
        User user = authTokenFilter.getUserFromRequestAuth(request);
        if(user.hasRole(EnumRole.ROLE_ADMIN)) return realEstateDAOImpl.getAll();
        return realEstateDAOImpl.getAllAvailable(user.getId());
    }

    @GetMapping("/{id}")
    public RealEstate getRealEstateById(@PathVariable Long id) {
        return realEstateDAOImpl.findById(id);
    }

    @Secured("ROLE_USER")
    @PostMapping()
    public ResponseEntity<ReturnID> postRealEstate(@Valid @RequestHeader HashMap<String, String> request,
                                                   @Valid @RequestBody RealEstate estate) {

        User user = authTokenFilter.getUserFromRequestAuth(request);
        estate.setId(0L);
        estate.setSeller(user);
        // Last time when I finished my project, the following line was not necessary
        // but, I suppose that other field objects are instantiated with null values...
        estate.setTaxDeclaration(null);

        ReturnID estate_id = new ReturnID(realEstateDAOImpl.save(estate));

        TaxDeclaration td = new TaxDeclaration(0L, user, estate);
        taxDeclarationDAOImpl.save(td);

        estate.setTaxDeclaration(td);
        realEstateDAOImpl.save(estate);

        return Response.Body(new ReturnID(estate_id.getId()));
    }

    @Secured("ROLE_USER")
    @PostMapping("/update")
    public ResponseEntity<MessageResponse> updateRealEstate(@Valid @RequestHeader HashMap<String, String> request,
                                 @Valid @RequestBody   HashMap<String, Object> body) {
        Long estate_id = getLongFromObject(body.get("id"));
        User user = authTokenFilter.getUserFromRequestAuth(request);
        if(user == null || !user.hasRealEstate(estate_id)) return Response.UnauthorizedAccess("User authorization failed");
        RealEstate re = user.getRealEstate(estate_id);
        re.update(body);
        realEstateDAOImpl.save(re);
        return Response.Ok("Updated real estate successfully");
    }

    @DeleteMapping("/{estate_id}")
    public ResponseEntity<MessageResponse> deleteRealEstate(@Valid @RequestHeader HashMap<String, String> request, @PathVariable Long estate_id) {
        User user = authTokenFilter.getUserFromRequestAuth(request);
        Boolean isAdmin = user.hasRole(EnumRole.ROLE_ADMIN);
        if(!isAdmin && !user.hasRealEstate(estate_id)) {
                return Response.UnauthorizedAccess("User authorization failed");
        } else if(!isAdmin){
            RealEstate re = user.getRealEstate(estate_id);
            user.getRealEstateList().remove(re);
        }
        realEstateDAOImpl.delete(estate_id);
        return Response.Ok("Deleted real estate successfully");
    }

    private static class ReturnID {
        private Long id;

        public ReturnID(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private Long getLongFromObject(Object obj) {
        return (Long) ((Number) obj).longValue();
    }

}
