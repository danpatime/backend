package com.example.api.global;

import com.example.api.board.controller.domain.CategoryDTO;
import com.example.api.board.controller.domain.ExternalCareerDTO;
import com.example.api.board.controller.domain.InnerCareerDTO;
import com.example.api.board.controller.domain.PossibleBoardDTO;
import com.example.api.domain.*;
import com.example.api.domain.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Rollback(false)
public class BaseIntegrationTest {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FlavoredRepository flavoredRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private MyInfoRepository myInfoRepository;
    @Autowired
    private OfferEmploymentRepository offerEmploymentRepository;
    @Autowired
    private PossibleBoardRepository possibleBoardRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ExternalCareerRepository externalCareerRepository;

    protected List<ExternalCareerDTO> externalCareerList;
    protected List<PossibleBoardDTO> possibleBoardList;
    protected List<InnerCareerDTO> innerCareerList;
    protected List<CategoryDTO> flavoredCategoryList;

    protected Category category1, category2;


    @BeforeEach
    void setUpData() {
        Account employee = new Account();
        employee.setAge(30);
        employee.setEmail("johndoe@example.com");
        employee.setName("John Doe");
        employee.setSex("Male");
        employee.setPhoneNumber("123-456-7890");
        employee.setNickname("Johnny");
        employee.setStarPoint(3.5f);
        employee.setWorkCount(3);
        employeeRepository.save(employee);

        Account employer = new Account();
        employer.setNickname("EmployerOne");
        employerRepository.save(employer);

        category1 = new Category();
        category1.setCategoryName("IT Services");
        categoryRepository.save(category1);

        category2 = new Category();
        category2.setCategoryName("Consulting");
        categoryRepository.save(category2);

        Business business = new Business();
        business.setBusinessName("Tech Solutions Inc.");
        business.setLocation("Seoul, South Korea");
        business.setRepresentationName("James");
        business.setOpenDate(LocalDate.now());
        business.setRegistrationNumber("123-456-789");
        business.setEmployer(employer); // 다대일 관계 설정

        BusinessCategory businessCategory1 = new BusinessCategory();
        businessCategory1.setBusiness(business);
        businessCategory1.setCategory(category1);

        BusinessCategory businessCategory2 = new BusinessCategory();
        businessCategory2.setBusiness(business);
        businessCategory2.setCategory(category2);

        business.setBusinessCategories(List.of(businessCategory1, businessCategory2));
        businessRepository.save(business);

        OfferEmployment offerEmployment = new OfferEmployment();
        offerEmployment.setBusiness(business);
        offerEmployment.setEmployee(employee);
        offerEmployment.setSuggestStartTime(LocalDateTime.of(2024, 11, 5, 9, 0));
        offerEmployment.setSuggestEndTime(LocalDateTime.of(2024, 11, 5, 17, 0));
        offerEmployment.setSuggestHourlyPay(20000);
        offerEmployment.setSuggestSucceeded(false);
        offerEmploymentRepository.save(offerEmployment);

        Contract contract = new Contract();
        contract.setOfferEmployment(offerEmployment); // 일대일 관계 설정
        contract.setContractStartTime(LocalDateTime.of(2024, 11, 10, 9, 0));
        contract.setContractEndTime(LocalDateTime.of(2024, 11, 10, 18, 0));
        contract.setContractHourlyPay(22000);
        contract.setContractSucceeded(true);
        contractRepository.save(contract);

        ExternalCareer externalCareer1 = new ExternalCareer(employee, "Consultant", "24-10-01");
        ExternalCareer externalCareer2 = new ExternalCareer(employee, "Architect", "24-10-08");
        externalCareerRepository.save(externalCareer1);
        externalCareerRepository.save(externalCareer2);

        externalCareerList = new ArrayList<>();
        externalCareerList.add(new ExternalCareerDTO(employee.getAccountId(), externalCareer1.getName(), externalCareer1.getPeriod()));
        externalCareerList.add(new ExternalCareerDTO(employee.getAccountId(), externalCareer2.getName(), externalCareer2.getPeriod()));

        Review review1 = new Review(1L, 4, "Good work experience");
        reviewRepository.save(review1);

        innerCareerList = new ArrayList<>();
        innerCareerList.add(new InnerCareerDTO(business.getBusinessName(), contract.getContractStartTime(), business.getRepresentationName(), review1));

        PossibleBoard possibleBoard1 = new PossibleBoard(employee, LocalDateTime.of(2024, 11, 5, 12, 0), LocalDateTime.of(2024, 11, 5, 18, 0));
        PossibleBoard possibleBoard2 = new PossibleBoard(employee, LocalDateTime.of(2024, 11, 6, 12, 0), LocalDateTime.of(2024, 11, 6, 18, 0));
        possibleBoardRepository.save(possibleBoard1);
        possibleBoardRepository.save(possibleBoard2);

        possibleBoardList = new ArrayList<>();
        possibleBoardList.add(new PossibleBoardDTO(employee.getAccountId(), possibleBoard1.getStartTime(), possibleBoard1.getEndTime()));
        possibleBoardList.add(new PossibleBoardDTO(employee.getAccountId(), possibleBoard2.getStartTime(), possibleBoard2.getEndTime()));

//        Flavored flavored = new Flavored();
//        flavored.setCategory(categoryRepository.findById(1L).get());
//        flavored.setEmployee(employeeRepository.findById(1L).get());
//        flavoredRepository.save(flavored);

        flavoredCategoryList = new ArrayList<>();
        flavoredCategoryList.addAll(flavoredRepository.findAllCategoryDTOByEmployeeId(1L));
    }
}