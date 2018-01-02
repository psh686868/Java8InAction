package utils.testhelp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/24
 */

@JsonInclude(Include.NON_EMPTY)
public final class BuilderTwo {


    private final Map<String, Object> details;
    private final Person person;

    /**
     * Create a new {@link BuilderTwo} instance with the specified status and details.
     */
    private BuilderTwo(Person person, Map<String,Object> details) {
        this.details = details;
        this.person = person;
    }

    /**
     * Return the status of the health.
     * @return the status (never {@code null})
     */


    /**
     * Return the details of the health.
     *
     * @return the details (or an empty map)
     */
    public Map<String, Object> getDetails() {
        return this.details;
    }

    public static BuilderTwoBuilder Builder() {

        return new BuilderTwoBuilder();
    }

    static class BuilderTwoBuilder {
        private  Map<String, Object> details;
        private  Person person;

        public BuilderTwoBuilder () {

        }

        public BuilderTwoBuilder (Person person,Map<String,Object> details) {
            this.details = details;
            this.person = person;
        }

        public BuilderTwoBuilder details(Map<String,Object> details) {
            this.details = details;
            return this;
        }
        public BuilderTwoBuilder person (Person person) {
            this.person = person;
            return this;
        }

        public BuilderTwo build() {
            return new BuilderTwo(this.person,this.details);
        }
    }

    public static void main(String[] args) {
        BuilderTwo build = BuilderTwo.Builder().person(new Person()).build();
        System.out.println(build);
    }

}
