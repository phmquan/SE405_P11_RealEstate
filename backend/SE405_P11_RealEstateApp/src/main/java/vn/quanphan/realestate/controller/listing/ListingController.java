package vn.quanphan.realestate.controller.listing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.request.ReqListingDTO;
import vn.quanphan.realestate.domain.response.ResApiError;
import vn.quanphan.realestate.domain.response.ResListingDTO;
import vn.quanphan.realestate.service.ListingService;
import vn.quanphan.realestate.service.UserService;
import vn.quanphan.realestate.util.SecurityUtil;
import vn.quanphan.realestate.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1/listing")
@RequiredArgsConstructor
public class ListingController {

    private final SecurityUtil securityUtil;
    private final ListingService listingService;
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllListing(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional,
            @RequestParam(required = false) String listingType,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<Listing> listings = listingService.findListings(listingType, propertyType, minPrice, maxPrice, pageable);

        if (listings == null || listings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có bài đăng");
        }
        List<ResListingDTO> listingDTOs = new ArrayList<ResListingDTO>();
        for (Listing listing : listings) {
            listingDTOs.add(listingService.convertToResListingDTO(listing));
        }
        return ResponseEntity.ok(listingDTOs);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getListingByStatus(
            @RequestParam("status") String status,
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<Listing> listings = listingService.getListingByStatus(status, pageable);
        List<ResListingDTO> listingDTOs = new ArrayList<ResListingDTO>();
        for (Listing listing : listings) {
            listingDTOs.add(listingService.convertToResListingDTO(listing));
        }
        return ResponseEntity.ok(listingDTOs);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createListing(
            @Valid @RequestBody ReqListingDTO listing,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws IdInvalidException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Access Token không hợp lệ");
        }

        // Lấy access token từ header
        String accessToken = authorizationHeader.substring(7);
        // Giải mã access token
        Jwt decodedToken = this.securityUtil.checkValidAccessToken(accessToken);
        String email = decodedToken.getSubject();
        User currentUser = userService.getUserByEmail(email);
        long listingCount = listingService.countListingByUser(currentUser);
        if (listingCount >= 5 && currentUser.getSpecificationListingPage() == null) {
            ResApiError error = new ResApiError(HttpStatus.BAD_REQUEST.value(), "Số lượng listing đã đạt tối đa", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok(listingService.createListing(listing, currentUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getListingById(
            @PathVariable("id") String id
    ) {
        Listing listing = listingService.getListingById(id);

        return ResponseEntity.ok(listingService.convertToResListingDTO(listing));
    }

}
