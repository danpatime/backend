package com.example.api.global;

import com.example.api.board.repository.*;
import com.example.api.domain.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer {
    @Autowired private BusinessRepository businessRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BusinessCategoryRepository businessCategoryRepository;
    @Autowired private FlavoredRepository flavoredRepository;
    @Autowired private ContractRepository contractRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private EmployerRepository employerRepository;
    @Autowired private MyInfoRepository myInfoRepository;
    @Autowired private OfferEmploymentRepository offerEmploymentRepository;
    @Autowired private PossibleBoardRepository possibleBoardRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private ExternalCareerRepository externalCareerRepository;

    @PostConstruct
    void setUpData() {
        Account account = new Account();
        account.setAge(30);
        account.setEmail("johndoe@example.com");
        account.setName("John Doe");
        account.setSex("Male");
        account.setPhoneNumber("123-456-7890");

        Employee employee = new Employee();
        employee.setAccount(account);
        employee.setNickname("Johnny");
        employee.setStarPoint(3.5f);
        employee.setWorkCount(3);
        employeeRepository.save(employee);

        Employer employer = new Employer();
        employer.setNickname("EmployerOne");
        employerRepository.save(employer);


        Category category1 = new Category();
        category1.setCategoryName("IT Services");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setCategoryName("Consulting");
        categoryRepository.save(category2);

        Business business = new Business();
        business.setBusinessName("Tech Solutions Inc.");
        business.setLocation("Seoul, South Korea");
        business.setRepresentationName("James");
        business.setOpenDate(LocalDate.now());
        business.setRegistrationNumber("123-456-789");
        business.setEmployer(employer);
        businessRepository.save(business);

        BusinessCategory businessCategory1 = new BusinessCategory();
        businessCategory1.setBusiness(business);
        businessCategory1.setCategory(category1);

        BusinessCategory businessCategory2 = new BusinessCategory();
        businessCategory2.setBusiness(business);
        businessCategory2.setCategory(category2);

        business.setBusinessCategories(List.of(businessCategory1, businessCategory2));
        businessCategoryRepository.save(businessCategory1);
        businessCategoryRepository.save(businessCategory2);

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

        Review review1 = new Review(1L, 4, "Good work experience");
        reviewRepository.save(review1);

        PossibleBoard possibleBoard1 = new PossibleBoard(employee, LocalDateTime.of(2024, 11, 5, 12, 0), LocalDateTime.of(2024, 11, 5, 18, 0));
        PossibleBoard possibleBoard2 = new PossibleBoard(employee, LocalDateTime.of(2024, 11, 6, 12, 0), LocalDateTime.of(2024, 11, 6, 18, 0));
        possibleBoardRepository.save(possibleBoard1);
        possibleBoardRepository.save(possibleBoard2);

        Flavored flavored = new Flavored();
        flavored.setCategory(categoryRepository.findById(1L).get());
        flavored.setEmployee(employeeRepository.findById(1L).get());
        flavoredRepository.save(flavored);
    }
}