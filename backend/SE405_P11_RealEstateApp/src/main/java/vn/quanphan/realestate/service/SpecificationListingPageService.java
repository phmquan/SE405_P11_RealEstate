package vn.quanphan.realestate.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.request.specification.ReqSpecificationListingPage;
import vn.quanphan.realestate.domain.response.ResSpecificationListingPageDTO;
import vn.quanphan.realestate.repository.SpecificationListingPageRepository;
import vn.quanphan.realestate.util.constant.SpecificationListingPageStatus;

@Service
@RequiredArgsConstructor
public class SpecificationListingPageService {

    private final SpecificationListingPageRepository specificationListingPageRepository;
    private final BrokerCertificationService brokerCertificationService;
    private final UserService userService;

    public SpecificationListingPage createSpecificationListingPage(ReqSpecificationListingPage requestSpecificationListingPage, User user) {
        SpecificationListingPage specificationListingPage = new SpecificationListingPage();

        specificationListingPage.setPageName(requestSpecificationListingPage.getPageName());
        specificationListingPage.setFullName(requestSpecificationListingPage.getFullName());
        specificationListingPage.setBrokerArea(requestSpecificationListingPage.getBrokerArea());
        specificationListingPage.setWorkingPlaceAdress(requestSpecificationListingPage.getWorkingPlaceAdress());
        specificationListingPage.setDescription(requestSpecificationListingPage.getDescription());
        specificationListingPage.setStatus(SpecificationListingPageStatus.PENDING);
        specificationListingPage.setListingSpecificationType(requestSpecificationListingPage.getListingSpecificationType());
        specificationListingPage.setAvatar(requestSpecificationListingPage.getAvatar());
        specificationListingPage.setCoverImage(requestSpecificationListingPage.getCoverImage());
        specificationListingPage.setBrokerCertification(brokerCertificationService.createBrokerCertification(requestSpecificationListingPage.getBrokerCertification()));
        specificationListingPage.setUser(user);
        user.setSpecificationListingPage(specificationListingPage);

        specificationListingPageRepository.save(specificationListingPage);
        userService.handleCreateUser(user);
        return specificationListingPage;
    }

    public ResSpecificationListingPageDTO convertToResSpecificationListingPageDTO(SpecificationListingPage specificationListingPage) {
        ResSpecificationListingPageDTO resSpecificationListingPageDTO = new ResSpecificationListingPageDTO();
        resSpecificationListingPageDTO.setId(specificationListingPage.getId());
        resSpecificationListingPageDTO.setPageName(specificationListingPage.getPageName());
        resSpecificationListingPageDTO.setFullName(specificationListingPage.getFullName());
        resSpecificationListingPageDTO.setBrokerArea(specificationListingPage.getBrokerArea());
        resSpecificationListingPageDTO.setWorkingPlaceAdress(specificationListingPage.getWorkingPlaceAdress());
        resSpecificationListingPageDTO.setDescription(specificationListingPage.getDescription());
        resSpecificationListingPageDTO.setListingSpecificationType(specificationListingPage.getListingSpecificationType());
        resSpecificationListingPageDTO.setBrokerCertification(specificationListingPage.getBrokerCertification());
        resSpecificationListingPageDTO.setUser(userService.convertToResUserDTO(specificationListingPage.getUser()));
        resSpecificationListingPageDTO.setAvartar(specificationListingPage.getAvatar());
        resSpecificationListingPageDTO.setCoverImage(specificationListingPage.getCoverImage());
        resSpecificationListingPageDTO.setStatus(specificationListingPage.getStatus().toString());
        resSpecificationListingPageDTO.setCreatedAt(specificationListingPage.getCreatedAt());
        return resSpecificationListingPageDTO;
    }

    public List<SpecificationListingPage> findSpecificationListingPages(Pageable pageable) {
        return specificationListingPageRepository.findAll(pageable).getContent();
    }

    public SpecificationListingPage getSpecificationListingPageById(String id) {
        return specificationListingPageRepository.findById(Long.parseLong(id)).orElse(null);
    }

}
