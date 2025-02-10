package vn.quanphan.realestate.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import vn.quanphan.realestate.domain.response.admin.ResListingDetailDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationDetailDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserDetailDTO;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        ResUserDetailDTO userDTO = adminService.convertToUserDetailDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/listing/{id}")
    public ResponseEntity<?> getListingById(@PathVariable("id") String id) {
        Listing listing = listingService.getListingById(id);
        ResListingDetailDTO listingDTO = adminService.convertToListingDetailDTO(listing);
        return ResponseEntity.ok().body(listingDTO);
    }

    @GetMapping("/specification/{id}")
    public ResponseEntity<?> getSpecificationById(@PathVariable("id") String id) {
        SpecificationListingPage specificationListingPage = specificationListingPageService.getSpecificationListingPageById(id);
        ResSpecificationDetailDTO specificationDTO = adminService.convertToSpecificationDetailDTO(specificationListingPage);
        return ResponseEntity.ok().body(specificationDTO);
    }

    @GetMapping("/user/{id}/ban")
    public ResponseEntity<?> banUser(@PathVariable("id") String id) {
        userService.banUser(id);
        return ResponseEntity.ok().body("Ban user successfully");
    }

    @PostMapping("/user/banBulk")
    public ResponseEntity<?> banUserBulk(@RequestBody List<String> ids) {
        for (String id : ids) {
            userService.banUser(id);
        }
        return ResponseEntity.ok().body("Ban user successfully");
    }

    @GetMapping("/listing/{id}/accept")
    public ResponseEntity<?> acceptListing(@PathVariable("id") String id) {
        listingService.acceptListing(id);
        return ResponseEntity.ok().body("Accept listing successfully");
    }

    @GetMapping("/specification/{id}/accept")
    public ResponseEntity<?> acceptSpecification(@PathVariable("id") String id) {
        specificationListingPageService.acceptSpecification(id);
        return ResponseEntity.ok().body("Accept listing successfully");
    }

    @GetMapping("/listing/{id}/reject")
    public ResponseEntity<?> rejectListing(@PathVariable("id") String id) {
        listingService.rejectListing(id);
        return ResponseEntity.ok().body("Accept listing successfully");
    }

    @GetMapping("/specification/{id}/reject")
    public ResponseEntity<?> rejectSpecification(@PathVariable("id") String id) {
        specificationListingPageService.rejectSpecification(id);
        return ResponseEntity.ok().body("Accept listing successfully");
    }

    @PostMapping("/listing/acceptBulk")
    public ResponseEntity<?> acceptListingBulk(@RequestBody List<String> ids) {
        for (String id : ids) {
            listingService.acceptListing(id);
        }
        return ResponseEntity.ok().body("Accept listing successfully");
    }

    @PostMapping("/listing/rejectBulk")
    public ResponseEntity<?> rejectListingBulk(@RequestBody List<String> ids) {
        for (String id : ids) {
            listingService.rejectListing(id);
        }
        return ResponseEntity.ok().body("Reject listing successfully");
    }

    @PostMapping("/specification/acceptBulk")
    public ResponseEntity<?> acceptspecificationBulk(@RequestBody List<String> ids) {
        for (String id : ids) {
            specificationListingPageService.acceptSpecification(id);
        }
        return ResponseEntity.ok().body("Accept  successfully");
    }

    @PostMapping("/specification/rejectBulk")
    public ResponseEntity<?> rejectspecificationBulk(@RequestBody List<String> ids) {
        for (String id : ids) {
            specificationListingPageService.rejectSpecification(id);
        }
        return ResponseEntity.ok().body("Reject successfully");
    }
}
