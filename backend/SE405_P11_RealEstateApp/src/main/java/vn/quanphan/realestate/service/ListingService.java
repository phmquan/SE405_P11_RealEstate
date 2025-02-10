package vn.quanphan.realestate.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.Apartment;
import vn.quanphan.realestate.domain.House;
import vn.quanphan.realestate.domain.Land;
import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.Property;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.request.ReqListingDTO;
import vn.quanphan.realestate.domain.request.property.ApartmentDTO;
import vn.quanphan.realestate.domain.request.property.HouseDTO;
import vn.quanphan.realestate.domain.request.property.LandDTO;
import vn.quanphan.realestate.domain.request.property.PropertyDTO;
import vn.quanphan.realestate.domain.response.ResListingDTO;
import vn.quanphan.realestate.repository.ListingRepository;
import vn.quanphan.realestate.repository.PropertyRepository;
import vn.quanphan.realestate.util.constant.ListingStatus;
import vn.quanphan.realestate.util.specification.ListingSpecification;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;
    private final PropertyRepository propertyRepository;

    public List<Listing> findListings(String listingType, String propertyType, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Listing> spec = Specification.where(ListingSpecification.hasListingType(listingType))
                .and(ListingSpecification.hasPropertyType(propertyType))
                .and(ListingSpecification.hasPriceBetween(minPrice, maxPrice));
        return listingRepository.findAll(spec, pageable).getContent();
    }

    public ResListingDTO createListing(ReqListingDTO listingDTO, User user) {
        Property property = createProperty(listingDTO);
        propertyRepository.save(property);
        Listing listing = new Listing();
        listing.setUser(user);
        listing.setListingType(listingDTO.getListingType());
        listing.setListingTitle(listingDTO.getListingTitle());
        listing.setListingDescription(listingDTO.getListingDescription());
        listing.setPropertyType(listingDTO.getPropertyType());
        listing.setProperty(property);
        listing.setStatus(ListingStatus.PENDING);
        ResListingDTO resListingDTO = convertToResListingDTO(listingRepository.save(listing), listingDTO);
        return resListingDTO;
    }

    public Property createProperty(ReqListingDTO dto) {

        switch (dto.getPropertyType().toLowerCase()) {
            case "house" -> {
                if (dto.getProperty() instanceof HouseDTO houseDTO) {
                    House house = new House();
                    house.setAddress(houseDTO.getAddress());
                    house.setDistrict(houseDTO.getDistrict());
                    house.setPropertyLength(Double.parseDouble(houseDTO.getPropertyLength()));
                    house.setPropertyWidth(Double.parseDouble(houseDTO.getPropertyWidth()));
                    house.setPropertyArea(Double.parseDouble(houseDTO.getPropertyArea()));
                    house.setLegalDocument(houseDTO.getLegalDocument());
                    house.setPropertyPrice(Double.parseDouble(houseDTO.getPropertyPrice()));
                    house.setPropertyImages(houseDTO.getPropertyImages());
                    house.setHouseType(houseDTO.getHouseType());
                    house.setHouseRoom(Double.parseDouble(houseDTO.getHouseRoom()));
                    house.setHouseToilet(Double.parseDouble(houseDTO.getHouseToilet()));
                    house.setHouseDirection(houseDTO.getHouseDirection());
                    return house;
                }
                throw new IllegalArgumentException("Invalid HouseDTO provided");
            }

            case "land" -> {
                if (dto.getProperty() instanceof LandDTO landDTO) {
                    Land land = new Land();
                    land.setAddress(landDTO.getAddress());
                    land.setDistrict(landDTO.getDistrict());
                    land.setPropertyLength(Double.parseDouble(landDTO.getPropertyLength()));
                    land.setPropertyWidth(Double.parseDouble(landDTO.getPropertyWidth()));
                    land.setPropertyArea(Double.parseDouble(landDTO.getPropertyArea()));
                    land.setLegalDocument(landDTO.getLegalDocument());
                    land.setPropertyPrice(Double.parseDouble(landDTO.getPropertyPrice()));
                    land.setPropertyImages(landDTO.getPropertyImages());
                    land.setLandType(landDTO.getLandType());
                    land.setLandDirection(landDTO.getLandDirection());
                    return land;
                }
                throw new IllegalArgumentException("Invalid LandDTO provided");
            }

            case "apartment" -> {
                if (dto.getProperty() instanceof ApartmentDTO apartmentDTO) {
                    Apartment apartment = new Apartment();
                    apartment.setAddress(apartmentDTO.getAddress());
                    apartment.setDistrict(apartmentDTO.getDistrict());
                    apartment.setPropertyLength(Double.parseDouble(apartmentDTO.getPropertyLength()));
                    apartment.setPropertyWidth(Double.parseDouble(apartmentDTO.getPropertyWidth()));
                    apartment.setPropertyArea(Double.parseDouble(apartmentDTO.getPropertyArea()));
                    apartment.setLegalDocument(apartmentDTO.getLegalDocument());
                    apartment.setPropertyPrice(Double.parseDouble(apartmentDTO.getPropertyPrice()));
                    apartment.setPropertyImages(apartmentDTO.getPropertyImages());
                    apartment.setApartmentCode(apartmentDTO.getApartmentCode());
                    apartment.setApartmentFloor(apartmentDTO.getApartmentFloor());
                    apartment.setApartmentBlock(apartmentDTO.getApartmentBlock());
                    apartment.setApartmentType(apartmentDTO.getApartmentType());
                    apartment.setApartmentRoom(Double.parseDouble(apartmentDTO.getApartmentRoom()));
                    apartment.setApartmentToilet(Double.parseDouble(apartmentDTO.getApartmentToilet()));
                    apartment.setApartmentDirection(apartmentDTO.getApartmentDirection());
                    return apartment;
                }
                throw new IllegalArgumentException("Invalid ApartmentDTO provided");
            }

            default ->
                throw new IllegalArgumentException("Unsupported property type: " + dto.getPropertyType());
        }
    }

    public PropertyDTO createPropertyDTO(ReqListingDTO dto) {
        switch (dto.getPropertyType().toLowerCase()) {
            case "house" -> {
                if (dto.getProperty() instanceof HouseDTO houseDTO) {
                    HouseDTO result = new HouseDTO();
                    result.setAddress(houseDTO.getAddress());
                    result.setDistrict(houseDTO.getDistrict());
                    result.setPropertyLength(houseDTO.getPropertyLength());
                    result.setPropertyWidth(houseDTO.getPropertyWidth());
                    result.setPropertyArea(houseDTO.getPropertyArea());
                    result.setLegalDocument(houseDTO.getLegalDocument());
                    result.setPropertyPrice(houseDTO.getPropertyPrice());
                    result.setPropertyImages(houseDTO.getPropertyImages());
                    result.setHouseType(houseDTO.getHouseType());
                    result.setHouseRoom(houseDTO.getHouseRoom());
                    result.setHouseToilet(houseDTO.getHouseToilet());
                    result.setHouseDirection(houseDTO.getHouseDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid HouseDTO provided");
            }

            case "land" -> {
                if (dto.getProperty() instanceof LandDTO landDTO) {
                    LandDTO result = new LandDTO();
                    result.setAddress(landDTO.getAddress());
                    result.setDistrict(landDTO.getDistrict());
                    result.setPropertyLength(landDTO.getPropertyLength());
                    result.setPropertyWidth(landDTO.getPropertyWidth());
                    result.setPropertyArea(landDTO.getPropertyArea());
                    result.setLegalDocument(landDTO.getLegalDocument());
                    result.setPropertyPrice(landDTO.getPropertyPrice());
                    result.setPropertyImages(landDTO.getPropertyImages());
                    result.setLandType(landDTO.getLandType());
                    result.setLandDirection(landDTO.getLandDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid LandDTO provided");
            }

            case "apartment" -> {
                if (dto.getProperty() instanceof ApartmentDTO apartmentDTO) {
                    ApartmentDTO result = new ApartmentDTO();
                    result.setAddress(apartmentDTO.getAddress());
                    result.setDistrict(apartmentDTO.getDistrict());
                    result.setPropertyLength(apartmentDTO.getPropertyLength());
                    result.setPropertyWidth(apartmentDTO.getPropertyWidth());
                    result.setPropertyArea(apartmentDTO.getPropertyArea());
                    result.setLegalDocument(apartmentDTO.getLegalDocument());
                    result.setPropertyPrice(apartmentDTO.getPropertyPrice());
                    result.setPropertyImages(apartmentDTO.getPropertyImages());
                    result.setApartmentCode(apartmentDTO.getApartmentCode());
                    result.setApartmentFloor(apartmentDTO.getApartmentFloor());
                    result.setApartmentBlock(apartmentDTO.getApartmentBlock());
                    result.setApartmentType(apartmentDTO.getApartmentType());
                    result.setApartmentRoom(apartmentDTO.getApartmentRoom());
                    result.setApartmentToilet(apartmentDTO.getApartmentToilet());
                    result.setApartmentDirection(apartmentDTO.getApartmentDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid ApartmentDTO provided");
            }

            default ->
                throw new IllegalArgumentException("Unsupported property type: " + dto.getPropertyType());
        }
    }

    public PropertyDTO createPropertyDTO(Listing dto) {
        switch (dto.getPropertyType().toLowerCase()) {
            case "house" -> {
                if (dto.getProperty() instanceof House houseDTO) {
                    HouseDTO result = new HouseDTO();
                    result.setAddress(houseDTO.getAddress());
                    result.setDistrict(houseDTO.getDistrict());
                    result.setPropertyLength(Double.toString(houseDTO.getPropertyLength()));
                    result.setPropertyWidth(Double.toString(houseDTO.getPropertyWidth()));
                    result.setPropertyArea(Double.toString(houseDTO.getPropertyArea()));
                    result.setLegalDocument(houseDTO.getLegalDocument());
                    result.setPropertyPrice(Double.toString(houseDTO.getPropertyPrice()));
                    result.setPropertyImages(houseDTO.getPropertyImages());
                    result.setHouseType(houseDTO.getHouseType());
                    result.setHouseRoom(Double.toString(houseDTO.getHouseRoom()));
                    result.setHouseToilet(Double.toString(houseDTO.getHouseToilet()));
                    result.setHouseDirection(houseDTO.getHouseDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid HouseDTO provided");
            }

            case "land" -> {
                if (dto.getProperty() instanceof Land landDTO) {
                    LandDTO result = new LandDTO();
                    result.setAddress(landDTO.getAddress());
                    result.setDistrict(landDTO.getDistrict());
                    result.setPropertyLength(Double.toString(landDTO.getPropertyLength()));
                    result.setPropertyWidth(Double.toString(landDTO.getPropertyWidth()));
                    result.setPropertyArea(Double.toString(landDTO.getPropertyArea()));
                    result.setLegalDocument(landDTO.getLegalDocument());
                    result.setPropertyPrice(Double.toString(landDTO.getPropertyPrice()));
                    result.setPropertyImages(landDTO.getPropertyImages());
                    result.setLandType(landDTO.getLandType());
                    result.setLandDirection(landDTO.getLandDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid LandDTO provided");
            }

            case "apartment" -> {
                if (dto.getProperty() instanceof Apartment apartmentDTO) {
                    ApartmentDTO result = new ApartmentDTO();
                    result.setAddress(apartmentDTO.getAddress());
                    result.setDistrict(apartmentDTO.getDistrict());
                    result.setPropertyLength(Double.toString(apartmentDTO.getPropertyLength()));
                    result.setPropertyWidth(Double.toString(apartmentDTO.getPropertyWidth()));
                    result.setPropertyArea(Double.toString(apartmentDTO.getPropertyArea()));
                    result.setLegalDocument(apartmentDTO.getLegalDocument());
                    result.setPropertyPrice(Double.toString(apartmentDTO.getPropertyPrice()));
                    result.setPropertyImages(apartmentDTO.getPropertyImages());
                    result.setApartmentCode(apartmentDTO.getApartmentCode());
                    result.setApartmentFloor(apartmentDTO.getApartmentFloor());
                    result.setApartmentBlock(apartmentDTO.getApartmentBlock());
                    result.setApartmentType(apartmentDTO.getApartmentType());
                    result.setApartmentRoom(Double.toString(apartmentDTO.getApartmentRoom()));
                    result.setApartmentToilet(Double.toString(apartmentDTO.getApartmentToilet()));
                    result.setApartmentDirection(apartmentDTO.getApartmentDirection());
                    return result;
                }
                throw new IllegalArgumentException("Invalid ApartmentDTO provided");
            }

            default ->
                throw new IllegalArgumentException("Unsupported property type: " + dto.getPropertyType());
        }
    }

    public long countListingByUser(User currentUser) {
        return listingRepository.countByUser(currentUser);
    }

    public ResListingDTO convertToResListingDTO(Listing save, ReqListingDTO listingDTO) {
        ResListingDTO res = new ResListingDTO();
        res.setId(save.getId());
        res.setListingType(save.getListingType());
        res.setListingTitle(save.getListingTitle());
        res.setListingDescription(save.getListingDescription());
        res.setPropertyType(save.getPropertyType());
        res.setListingStatus(save.getStatus().toString());
        res.setCreatedAt(save.getCreatedAt());
        res.setProperty(createPropertyDTO(listingDTO));
        return res;
    }

    public ResListingDTO convertToResListingDTO(Listing save) {
        ResListingDTO res = new ResListingDTO();
        res.setId(save.getId());
        res.setListingType(save.getListingType());
        res.setListingTitle(save.getListingTitle());
        res.setListingDescription(save.getListingDescription());
        res.setPropertyType(save.getPropertyType());
        res.setListingStatus(save.getStatus().toString());
        res.setCreatedAt(save.getCreatedAt());
        res.setProperty(createPropertyDTO(save));
        return res;
    }

    public Listing getListingById(String id) {
        return listingRepository.findById(Long.parseLong(id)).orElse(null);

    }

    public List<Listing> getListingByStatus(String status, Pageable pageable) {
        Page<Listing> page = listingRepository.findByStatus(ListingStatus.valueOf(status), pageable);
        return page.getContent();
    }

    public List<Listing> findListings(String listingType, String propertyType, Double minPrice, Double maxPrice) {
        Specification<Listing> spec = Specification.where(ListingSpecification.hasListingType(listingType))
                .and(ListingSpecification.hasPropertyType(propertyType))
                .and(ListingSpecification.hasPriceBetween(minPrice, maxPrice));
        return listingRepository.findAll(spec);
    }

    public String countByStatus(ListingStatus pending) {
        // TODO Auto-generated method stub
        return listingRepository.countByStatus(pending);
    }
}
