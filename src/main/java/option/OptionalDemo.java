package option;

import option.domain.Setting;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.Assert;
import utils.testhelp.Person;

/**
 * @author PSH
 * Date: 2017/12/27
 * option的使用方式
 */
public class OptionalDemo {
    public static final List<Setting> settings = Arrays.asList(
            new Setting("psh", "color", "gray"),
            new Setting("psh", "laptop", "macbook"),
            new Setting("psh", "cpu", "i5"),
            new Setting("dsh", "laptop", "lenovo"),
            null,
            new Setting("dsh", "color", "red")
    );

    public static void main(String[] args) {
        // List<Setting> settingNames = getSettingNames();

        //未使用option
//        Optional<Setting> setting = lookupSettingByName(false);
//        if (setting.isPresent()){
//            displayCheckIn(setting.get());
//        } else {
//            displayMissingSetting();
//        }
        //改进上面的
//        setting.ifPresent(OptionalDemo::displayCheckIn);

        String aa = "fdsafdsa";



       Person person = new Person();
       person.setName("aaaa");

        System.out.println(person.getName().equals(null));
//
//        person.setCollection(Arrays.stream(aa.split("\t")).collect(Collectors.toList()));
//
//        System.out.println("person = " + person);

    }

    public static List<Setting> getSettingNames() {
        List<Setting> settingList = settings.stream().map(OptionalDemo::lookupSettingByAuthor)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return settingList;
    }

    public static Optional<Setting> lookupSettingByAuthor(Setting setting) {

        if (setting == null) {
            return Optional.empty();
        }
        if ("psh".equals(setting.getAuthor())) {
            return Optional.ofNullable(setting);
        }
        return Optional.empty();
    }

    public static Optional<Setting> lookupSettingByName(boolean flage) {
        if (flage) {
            return Optional.of(new Setting("psh", "test", "test"));
        }
        return Optional.empty();
    }

    private static void displayCheckIn (Setting setting){}
    private static void displayMissingSetting (){}



}
