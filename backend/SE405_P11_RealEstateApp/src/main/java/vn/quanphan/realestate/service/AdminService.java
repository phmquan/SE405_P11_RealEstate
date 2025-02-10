package vn.quanphan.realestate.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.stereotype.Service;

import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.response.ResAdminHomepageDTO;
import vn.quanphan.realestate.domain.response.admin.ResListingAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserAdminDTO;

@Service
public class AdminService {

    public ResAdminHomepageDTO converToAdminHomepageDTO(String userNumber, String listingNumber, String specificationNumber) {
        ResAdminHomepageDTO resAdminHomepageDTO = new ResAdminHomepageDTO();
        resAdminHomepageDTO.setUserNumber(userNumber);
        resAdminHomepageDTO.setListingNumber(listingNumber);
        resAdminHomepageDTO.setSpecificationNumber(specificationNumber);
        return resAdminHomepageDTO;
    }

    public ResUserAdminDTO convertToUserAdminDTO(User user) {
        ResUserAdminDTO resUserAdminDTO = new ResUserAdminDTO();
        resUserAdminDTO.setId(user.getId());
        resUserAdminDTO.setName(user.getName());
        resUserAdminDTO.setEmail(user.getEmail());
        resUserAdminDTO.setPhone(user.getPhoneNumber());
        resUserAdminDTO.setStatus(user.getStatus().toString());

        return resUserAdminDTO;

    }

    public ResListingAdminDTO convertToListingAdminDTO(Listing listing) {
        ResListingAdminDTO resListingAdminDTO = new ResListingAdminDTO();
        resListingAdminDTO.setId(Long.toString(listing.getId()));
        switch (listing.getPropertyType()) {
            case "house":
                resListingAdminDTO.setPropertyType("Nhà ở");
                break;
            case "apartment":
                resListingAdminDTO.setPropertyType("Chung cư");
                break;
            case "land":
                resListingAdminDTO.setPropertyType("Đất nền");
                break;
        }
        resListingAdminDTO.setBusinessType(listing.getListingType());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        symbols.setDecimalSeparator(',');

        String formattedPrice = formatter.format(listing.getProperty().getPropertyPrice());
        resListingAdminDTO.setPrice(formattedPrice);
        resListingAdminDTO.setUserListing(listing.getUser().getEmail());
        resListingAdminDTO.setStatus(listing.getStatus().toString());
        return resListingAdminDTO;

    }

    public ResSpecificationAdminDTO convertToSpecificationAdminDTO(SpecificationListingPage specificationListingPage) {
        ResSpecificationAdminDTO resSpecificationAdminDTO = new ResSpecificationAdminDTO();
        resSpecificationAdminDTO.setId(Long.toString(specificationListingPage.getId()));
        resSpecificationAdminDTO.setName(specificationListingPage.getPageName());
        resSpecificationAdminDTO.setDescription(specificationListingPage.getDescription());
        resSpecificationAdminDTO.setBrokerCertificationNumber(specificationListingPage.getBrokerCertification().getNameOnCertification());
        resSpecificationAdminDTO.setBrokerCertificationNumber(specificationListingPage.getBrokerCertification().getCertificationNumber());
        resSpecificationAdminDTO.setStatus(specificationListingPage.getStatus().toString());
        return resSpecificationAdminDTO;
    }

}
