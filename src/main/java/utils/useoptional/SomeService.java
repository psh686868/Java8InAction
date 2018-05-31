package utils.useoptional;

import java.util.NoSuchElementException;
import java.util.Optional;
import javax.inject.Inject;

/**
 * @author PSH
 * Date: 2018/5/7
 */
public class SomeService {

    @Inject
    CompanyDao companyDao = new CompanyDao() {
        @Override
        public Optional<Company> selectCompanyById(String id) {
            return Optional.ofNullable(new Company());
        }
    };

    /**
     * return Optional<String>
     */
    public Optional<String> selectCeoCityByCompanyId0(String companyId) {
        return companyDao.selectCompanyById(companyId)
            .map(Company::getCeo)
            .flatMap(Person::getHomeAddress)
            .flatMap(Address::getCity);
    }

    /**
     * return String + default value
     */

    public String selectCeoCityByCompanyId1(String companyId) {
        return companyDao.selectCompanyById(companyId)
            .map(Company::getCeo)
            .flatMap(Person::getHomeAddress)
            .flatMap(Address::getCity)
            .orElse("UNKNOWN");
    }

    /**
     * return String + exception
     */
    public String selectCeoCityByCompanyId2(String companyId) throws NoSuchElementException {
        return companyDao.selectCompanyById(companyId)
            .map(Company::getCeo)
            .flatMap(Person::getHomeAddress)
            .flatMap(Address::getCity)
            .orElseThrow(NoSuchElementException::new);
    }
}


interface CompanyDao {

    /**
     * no compaty for such id  -> use Option<Compaty>
     */

    Optional<Company> selectCompanyById(String id);
}

class Company {

    /**
     * company always has ces -> use Person
     */
    Person ceo;

    public Person getCeo() {
        return ceo;
    }
}

class Person {

    /**
     * Person always had name;
     */
    String name;


    /**
     * person can be without address -> use Optional<Address>
     */
    Optional<Address> homeAddress = Optional.empty();

    public String getName() {
        return name;
    }

    public Optional<Address> getHomeAddress() {
        return homeAddress;
    }
}

class Address {

    /**
     * address always contains country -> use String
     */
    String country;

    /**
     * city field is optional -> use Optional<String>
     */
    Optional<String> city = Optional.empty();

    String getCountry() {
        return country;
    }

    Optional<String> getCity() {
        return city;
    }

    public static void main(String[] args) {
        SomeService someService = new SomeService();
        CompanyDao companyDao = someService.companyDao;
        Optional<Company> company = companyDao.selectCompanyById("111");
        company.ifPresent(System.out::println);
    }

}
