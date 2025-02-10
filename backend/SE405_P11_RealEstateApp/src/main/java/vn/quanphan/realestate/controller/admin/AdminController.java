package vn.quanphan.realestate.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.Role;
import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.domain.response.ResListingDTO;
import vn.quanphan.realestate.service.AdminService;
import vn.quanphan.realestate.service.ListingService;
import vn.quanphan.realestate.service.RoleService;
import vn.quanphan.realestate.service.SpecificationListingPageService;
import vn.quanphan.realestate.service.UserService;
import vn.quanphan.realestate.util.constant.ListingStatus;
import vn.quanphan.realestate.util.constant.SpecificationListingPageStatus;

import org.springframework.web.bind.annotation.RequestParam;

import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.response.admin.ResListingAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserAdminDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final ListingService listingService;
    private final SpecificationListingPageService specificationListingPageService;
    private final AdminService adminService;
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getHomepageInfor() {
        Role roleName = roleService.findRoleByName("ADMIN");
        String userNumber = userService.countExceptRole(roleName);
        String listingNumber = listingService.countByStatus(ListingStatus.PENDING);
        String specificationNumber = specificationListingPageService.countByStatus(SpecificationListingPageStatus.PENDING);
        return ResponseEntity.ok().body(adminService.converToAdminHomepageDTO(userNumber, listingNumber, specificationNumber));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfor(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<User> users = userService.getAllUser(pageable);
        List<ResUserAdminDTO> userDTOs = new ArrayList<ResUserAdminDTO>();
        for (User user : users) {
            userDTOs.add(adminService.convertToUserAdminDTO(user));
        }
        return ResponseEntity.ok().body(userDTOs);
    }

    @GetMapping("/listing")
    public ResponseEntity<?> getListingInfor(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<Listing> listings = listingService.getListingByStatus("PENDING", pageable);
        List<ResListingAdminDTO> listingDTOs = new ArrayList<ResListingAdminDTO>();
        for (Listing listing : listings) {
            listingDTOs.add(adminService.convertToListingAdminDTO(listing));
        }
        return ResponseEntity.ok().body(listingDTOs);
    }

    @GetMapping("/specification")
    public ResponseEntity<?> getSpecificationInfor(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<SpecificationListingPage> SpecificationListingPages = specificationListingPageService.getSpecificationListingPageByStatus("PENDING", pageable);
        List<ResSpecificationAdminDTO> SpecificationDTOs = new ArrayList<ResSpecificationAdminDTO>();
        for (SpecificationListingPage specificationListingPage : SpecificationListingPages) {
            SpecificationDTOs.add(adminService.convertToSpecificationAdminDTO(specificationListingPage));
        }
        return ResponseEntity.ok().body(SpecificationDTOs);
    }

}
