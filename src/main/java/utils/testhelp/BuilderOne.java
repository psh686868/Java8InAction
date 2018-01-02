package utils.testhelp;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author PSH
 * Create by psh
 * Date: 2017/12/24
 */

@JsonInclude(Include.NON_EMPTY)
public final class BuilderOne {


    private final Map<String, Object> details;
    private final Person person;

    /**
     * Create a new {@link BuilderOne} instance with the specified person and details.
     * @param builder the Builder to use
     */
    private BuilderOne(Builder builder) {
        this.details = Collections.unmodifiableMap(builder.details);
        this.person = builder.person;
    }



    /**
     * Return the details of the health.
     * @return the details (or an empty map)
     */
    public Map<String, Object> getDetails() {
        return this.details;
    }


    /**
     * Builder for creating immutable {@link BuilderOne} instances.
     */
    public static class Builder {

        private Person person;

        private Map<String, Object> details;

        /**
         * Create new Builder instance.
         */
        public Builder() {
            //this.person =null; or 不写
            this.person = new Person();
            this.details = new LinkedHashMap<>();
        }

        /**
         * Create new Builder instance, setting status to given {@code status}.
         * @param
         */
        public Builder(Person person) {
            Objects.requireNonNull(person,"person must not be null");

            this.person = person;
            this.details = new LinkedHashMap<>();
        }

        /**
         * Create new Builder instance, setting status to given {@code status} and details
         * to given {@code details}.
         * @param
         * @param details the details {@link Map} to use
         */
        public Builder(Person person, Map<String, ?> details) {
            Objects.requireNonNull(person,"person must not be null");
            Objects.requireNonNull(details, "Details must not be null");
            this.person = person;
            this.details = new LinkedHashMap<>(details);
        }

        /**
         * Record detail for given {@link Exception}.
         * @param ex the exception
         * @return this {@link Builder} instance
         */
        public Builder withException(Throwable ex) {
            Objects.requireNonNull(ex, "Exception must not be null");
            return withDetail("error", ex.getClass().getName() + ": " + ex.getMessage());
        }

        /**
         * Record detail using given {@code key} and {@code value}.
         * @param key the detail key
         * @param value the detail value
         * @return this {@link Builder} instance
         */
        public Builder withDetail(String key, Object value) {
            Objects.requireNonNull(key, "Key must not be null");
            Objects.requireNonNull(value, "Value must not be null");
            this.details.put(key, value);
            return this;
        }


        /**
         * Create a new {@link BuilderOne} instance with the previously specified code and
         * details.
         * @return a new {@link BuilderOne} instance
         */
        public BuilderOne build() {
            return new BuilderOne(this);
        }

    }

    public static void main(String[] args) {
        Builder builder = new Builder();
        BuilderOne build = builder.build();
    }

}
