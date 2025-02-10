package vn.quanphan.realestate.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.stereotype.Service;

import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.response.ResAdminHomepageDTO;
import vn.quanphan.realestate.domain.response.admin.ResListingAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResListingDetailDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResSpecificationDetailDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserAdminDTO;
import vn.quanphan.realestate.domain.response.admin.ResUserDetailDTO;

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
        resSpecificationAdminDTO.setNameOnCertification(specificationListingPage.getBrokerCertification().getNameOnCertification());
        resSpecificationAdminDTO.setBrokerCertificationNumber(specificationListingPage.getBrokerCertification().getCertificationNumber());
        resSpecificationAdminDTO.setStatus(specificationListingPage.getStatus().toString());
        return resSpecificationAdminDTO;
    }

    public ResUserDetailDTO convertToUserDetailDTO(User user) {
        ResUserDetailDTO resUserDetailDTO = new ResUserDetailDTO();
        resUserDetailDTO.setName(user.getName());
        resUserDetailDTO.setEmail(user.getEmail());
        resUserDetailDTO.setAge(Integer.toString(user.getAge()));
        resUserDetailDTO.setPhone(user.getPhoneNumber());
        resUserDetailDTO.setStatus(user.getStatus().toString());
        resUserDetailDTO.setAvatar(user.getAvatar());
        return resUserDetailDTO;
    }

    public ResListingDetailDTO convertToListingDetailDTO(Listing listing) {
        ResListingDetailDTO resListingDetailDTO = new ResListingDetailDTO();
        ResListingDetailDTO.Property property = resListingDetailDTO.new Property();
        resListingDetailDTO.setUserEmail(listing.getUser().getEmail());
        resListingDetailDTO.setListingType(listing.getListingType());
        resListingDetailDTO.setPropertyType(listing.getPropertyType());
        property.setAddress(listing.getProperty().getAddress());
        property.setDistrict(listing.getProperty().getDistrict());
        property.setPropertyArea(Double.toString(listing.getProperty().getPropertyArea()));
        property.setLegalDocument(listing.getProperty().getLegalDocument());
        resListingDetailDTO.setProperty(property);
        return resListingDetailDTO;
    }

    public ResSpecificationDetailDTO convertToSpecificationDetailDTO(SpecificationListingPage specificationListingPage) {
        ResSpecificationDetailDTO resSpecificationDetailDTO = new ResSpecificationDetailDTO();
        ResSpecificationDetailDTO.BrokerCertification brokerCertification = resSpecificationDetailDTO.new BrokerCertification();
        resSpecificationDetailDTO.setPageName(specificationListingPage.getPageName());
        resSpecificationDetailDTO.setFullName(specificationListingPage.getFullName());
        resSpecificationDetailDTO.setBrokerArea(specificationListingPage.getBrokerArea());
        resSpecificationDetailDTO.setWorkingPlaceAdress(specificationListingPage.getWorkingPlaceAdress());
        brokerCertification.setNameOnCertification(specificationListingPage.getBrokerCertification().getNameOnCertification());
        brokerCertification.setCertificationNumber(specificationListingPage.getBrokerCertification().getCertificationNumber());
        brokerCertification.setCertificationAuthority(specificationListingPage.getBrokerCertification().getCertificationAuthority());
        resSpecificationDetailDTO.setBrokerCertification(brokerCertification);
        return resSpecificationDetailDTO;
    }

}
