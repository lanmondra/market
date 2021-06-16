package cat.owc.ms.reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Utility class for testing REST controllers.
 */
public class TestUtil {
    // JWT administracion
    public static final String JWT_ADMIN = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjM2M4NmMzZC03MDg2LTRkNzctOGI3Yy1iOWY0MjliMDJjODAiLCJpYXQiOjE1OTAwNjgwOTMsImV4cCI6NDc0NTc0MTY5MywidHlwZSI6ImludHJhbmV0IiwiZGF0YSI6eyJ1c2VydXVpZCI6IjRlZWFhMGM2LTJjMDYtNGViOS05MmZiLTg3ODg2YzUwNzI3ZSIsInVzZXJuYW1lIjoibXIgcmVwb3J0IGFkbWluIn0sImF1dGhvcml0aWVzIjp7InRlc3QiOnsiZGVmYXVsdCI6WyJSRVBPUlRTX0FETUlOIl19fX0.OLMx8TSRevTZEW6g3Ff0RfvwevYiGBk4uIc5hlrSjqom-VK2TtV6E1gLa3BOxv80dD3Eh7b1XP70QgqhiNuZpAdzWpVf2ybC7CMb_M68tb7MCFU5bkrD3eQV1fIIn_S2hab2LTF0UoWtUiIbdU4_ZaIvyjd0SQpg1xh0eLP_PV8";
    public static final String JWT_ADMIN_CLUB = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiZDQ1NmMwNS05YzFjLTRlZTgtYjg1YS0xMjgzNmQzOWVmMDEiLCJpYXQiOjE1OTAwNjgzOTgsImV4cCI6NDc0NTc0MTk5OCwidHlwZSI6ImludHJhbmV0IiwiZGF0YSI6eyJ1c2VydXVpZCI6Ijc4YzhjYjBiLWU3ZjQtNGNmMS1iY2E4LWFiZTM1NDg4MTAyOCIsInVzZXJuYW1lIjoibXIgcmVwb3J0IGFkbWluIGNsdWIifSwiYXV0aG9yaXRpZXMiOnsidGVzdCI6eyJkZWZhdWx0IjpbIlJFUE9SVFNfQURNSU5fQ0xVQiJdfX19.NrE8WkqsszUE6Fnr3iZPRwogg-ImvDiWkRR0od1vYDRfV-Hq_LEmM2OLXqIKRawasYK2ozQEdBUZzMVFMHxNoHbUkI-r-0JI2T4rohzHSNp1qupx5Jx4iKC9ArTnWDP0O5YNa3QT96dRNCrS3C4nws_YBS-q3bhchWhJyfoQvHY";
    public static final String JWT_REPORTS_CAT_1 = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkM2ZmODMwMi03OTc2LTQ3YjctYTdiNy00NzUzNjM1YTEwOGMiLCJpYXQiOjE1OTE0ODI5NDEsImV4cCI6NDc0NzE1NjU0MSwidHlwZSI6ImludHJhbmV0IiwiZGF0YSI6eyJ1c2VydXVpZCI6IjhmNThiMTZiLWVhNjgtNGUwNC1hM2EzLTBmOTM3ZGU2Y2FhMiIsInVzZXJuYW1lIjoibXIgcmVwb3J0IGVkaXQgcmVwb3J0cyBjYXQgMSJ9LCJhdXRob3JpdGllcyI6eyJ0ZXN0Ijp7ImRlZmF1bHQiOlsiUkVQT1JUU19DQVRfUkVBRF8xIiwiUkVQT1JUU19DQVRfRURJVF8xIl19fX0.A2SVeuO1QBs6oMyWkrZWRjQKitbYXyRTLaPzGHVLKyNezMPPm4WdlysF2s1TlwYmQD71Wiv0aPFJHtKhl1sMOjcd6sQB8cgQafFFNLQDyh_bfkxrM7RnaiOD8oUqO446bqsEf4rKLyd7ctaldDrH7wqf1g7i4lSSPUBH_dHsxPE";
    public static final String JWT_REPORTS_CAT_1_2 = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJkZjA3ODllYy1lZTA4LTRiZWItYmM5MC02MzI5ODkxM2I4MGYiLCJpYXQiOjE1OTE0ODMxMTMsImV4cCI6NDc0NzE1NjcxMywidHlwZSI6ImludHJhbmV0IiwiZGF0YSI6eyJ1c2VydXVpZCI6IjFkYTZlNTY0LWYyNDUtNGRiOC05NzQ4LTYyNzJkZDcxZDExMSIsInVzZXJuYW1lIjoibXIgcmVwb3J0IGVkaXQgcmVwb3J0cyBjYXQgMSJ9LCJhdXRob3JpdGllcyI6eyJ0ZXN0Ijp7ImRlZmF1bHQiOlsiUkVQT1JUU19DQVRfUkVBRF8xIiwiUkVQT1JUU19DQVRfUkVBRF8yIiwiUkVQT1JUU19DQVRfRURJVF8xIiwiUkVQT1JUU19DQVRfRURJVF8yIl19fX0.gVFdX6I-Moi4JQ_X9lFKV-CoGYUBRIusCfHT0G2VEbgUGdPBAi8wliX33EIaUMr3s0HxBu5t_i3KIICYAeXObO-cCLbV21p-vFBurR8Ll_OZDbKG-Fbm_-kK27rOa_UFmF3l0P2YBJFZ0ZLEijyheACllaUnQZY3nOXxVHCeq2w";
    public static final String JWT_REPORTS_CAT_1_READ = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlN2UzYWRhZC0zM2M5LTRlYmUtOTY2Ni03ZWQ4ZTljZWQyMDkiLCJpYXQiOjE1OTE0ODMyNDksImV4cCI6NDc0NzE1Njg0OSwidHlwZSI6ImludHJhbmV0IiwiZGF0YSI6eyJ1c2VydXVpZCI6IjcwN2Y3ODJhLTgxNTItNDU1MC1hYWE4LTg3NTk5NmUxN2Q1YSIsInVzZXJuYW1lIjoibXIgcmVwb3J0IGVkaXQgcmVwb3J0cyBjYXQgMSJ9LCJhdXRob3JpdGllcyI6eyJ0ZXN0Ijp7ImRlZmF1bHQiOlsiUkVQT1JUU19DQVRfUkVBRF8xIl19fX0.B4S8OT39dvmEr3F7GXEulnAbHgiqteL-Lm-2xiIJgVncrMnJM2lWnscL-l51k0scTWcQ_womQ7UNYJmOp_ZQgcO2CwFlAERYLMoLmRF_Sm66e9XyHXf6K5l7P_efQPhSpOMBZcyNoiNWjADj1EpqEbt_rMnmL72A0HbMzv8EjDg";

    // JWT usuarios finales
    // referee 3212 / federated 152651 / user uuid 508ad6df-8fa1-11ea-b836-02b7c2952a14 (barça=
    public static final String JWT_USER_WITH_POLLS = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTg4OTcyNzE1LCJleHAiOjQ3NDQ2NDYzMTUsInR5cGUiOiJjbHViIiwiZGF0YSI6eyJmZWRlcmF0ZWQiOnsiaWQiOjE1MjY1MSwibmFtZSI6Ik1BUkMiLCJsYXN0TmFtZSI6IlNVQklSQUNIUyBQVUpBREFTIiwidXVpZCI6ImZkZDY2MzMwLTk4ZDAtMTFlOS1hMmE1LTAyMTY4MjQ3NzBjMiJ9LCJ1c2VydXVpZCI6IjUwOGFkNmRmLThmYTEtMTFlYS1iODM2LTAyYjdjMjk1MmExNCIsInJlZmVyZWUiOnsiaWQiOjMyMTIsIm5hbWUiOiJPU0NBUiBNQU5FTCIsImxhc3ROYW1lIjoiTE9QRVogUklCRVMiLCJ1dWlkIjoiYTQ0ZTA4M2UtNWQ3Ny00YWM2LTgxZDItOWM2M2ZjMWZjZTE4In0sInVzZXJuYW1lIjoiZi5jLmJhcmNlbG9uYSJ9LCJhdXRob3JpdGllcyI6eyJmY2JxIjp7ImRlZmF1bHQiOlsiUk9MRV9aT05BX1JFRkVSRUUiLCJQRVJNX1VTVUFSSVNfUkVGRVJFRSIsIlJPTEVfWk9OQV9DT0FDSCJdfX19.Fq59L5mzmJF1n4X34ChJSNzVdh45NR83Q6vfhKba7HRl1IYEQL0vwye7JJxYi7oojoeBC6us-ltb3d8a_Y5dxZ71iveB-fOYmdwk2BM7y5Dy8QHizFgwwJpXLewvrvnqd7PLRBbxO91fp-mDp4_8IdCyTLfI4Y6r9zT6mSabpAk";
    //public static final String JWT_USER_WITH_POLLS = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0OWE2NTRmMy1jY2Q5LTQ3MzctOTAxMC05ZDAzMDM1NTY0MGYiLCJpYXQiOjE1ODg3MjA3MjQsImV4cCI6NDc0NDM5NDMyNCwiZGF0YSI6eyJmZWRlcmF0ZWQiOnsiaWQiOjE1MjY1MSwibmFtZSI6Ik1BUkMiLCJsYXN0TmFtZSI6IlNVQklSQUNIUyBQVUpBREFTIiwidXVpZCI6ImZkZDY2MzMwLTk4ZDAtMTFlOS1hMmE1LTAyMTY4MjQ3NzBjMiJ9LCJ1c2VydXVpZCI6IjZkYjg4ZGNlLWVhZjAtNDNiNS1iMjM2LWI2ZDk3OGY4MDY1MCIsInJlZmVyZWUiOnsiaWQiOjMyMTIsIm5hbWUiOiJPU0NBUiBNQU5FTCIsImxhc3ROYW1lIjoiTE9QRVogUklCRVMiLCJ1dWlkIjoiYTQ0ZTA4M2UtNWQ3Ny00YWM2LTgxZDItOWM2M2ZjMWZjZTE4In0sInVzZXJuYW1lIjoibXIuIHJlcG9ydHMifSwiYXV0aG9yaXRpZXMiOnsiZmNicSI6eyJkZWZhdWx0IjpbIlJPTEVfWk9OQV9SRUZFUkVFIiwiUEVSTV9VU1VBUklTX1JFRkVSRUUiLCJST0xFX1pPTkFfQ09BQ0giXX19fQ.Yrt3-k6Ali7TVqbLCTuwsc0PaGu97-yKSfwfqF_aORWgpiVrxfONG0aYL6lItBr8ExoOqinuEBlufEpP5ktn_ifIlLsb15nTjlCLY0IkrRCL3OambMcjmN-cox2fnaGHMeQJuqX3MJueMtXJnM-LjVJRhM3S91ZYcl3qy-WFp5k";
    public static final String JWT_USER_NO_POLLS = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJmMmIyY2FjNi1lM2FhLTQ5MTItYTk4Yi1lZjkzZmQ2ZmIxYzciLCJpYXQiOjE1ODg5ODE4OTAsImV4cCI6NDc0NDY1NTQ5MCwidHlwZSI6ImNsdWIiLCJkYXRhIjp7ImZlZGVyYXRlZCI6eyJpZCI6MTUyNjUxLCJuYW1lIjoiTUFSQyIsImxhc3ROYW1lIjoiU1VCSVJBQ0hTIFBVSkFEQVMiLCJ1dWlkIjoiZmRkNjYzMzAtOThkMC0xMWU5LWEyYTUtMDIxNjgyNDc3MGMyIn0sInVzZXJ1dWlkIjoiZjg5NWJlNGUtMjA5Mi00NWM3LWE1ZDktZDg3MzM0MWU5MTJlIiwicmVmZXJlZSI6eyJpZCI6MzIxMiwibmFtZSI6Ik9TQ0FSIE1BTkVMIiwibGFzdE5hbWUiOiJMT1BFWiBSSUJFUyIsInV1aWQiOiJhNDRlMDgzZS01ZDc3LTRhYzYtODFkMi05YzYzZmMxZmNlMTgifSwidXNlcm5hbWUiOiJtciByZXBvcnQyIn0sImF1dGhvcml0aWVzIjp7ImZjYnEiOnsiZGVmYXVsdCI6WyJST0xFX1pPTkFfUkVGRVJFRSIsIlBFUk1fVVNVQVJJU19SRUZFUkVFIiwiUk9MRV9aT05BX0NPQUNIIl19fX0.m_lc62GgvoJkkk7byFyFl6yqadjBoISZy6_eQTrCilibDrQp0sXZNK9Cs0wWp5Pts-T9h57GW-EeF_yvXL8qYpV98I2sEh7OnjGb7Kzql37Z8GHzkpYxfQUoBbMV39SxtyXm7KXChUXCccNemUnhB0qcMrhT3ajywMK6XTSMBTU";


    public static HttpHeaders getTestHeaders(String jwt) {
        return getTestHeaders("test", jwt, "referee");
    }


    public static HttpHeaders getTestHeaders(String federation, String jwt) {
        return getTestHeaders(federation, jwt, "referee");
    }



    public static HttpHeaders getTestHeaders(String federation, String jwt, String origin) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("federation", federation);
        httpHeaders.add("X-App-Origin", origin);
        httpHeaders.add("Authorization", jwt);

        return httpHeaders;
    }




    /** MediaType for JSON UTF8 */
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    /**
     * Convert an object to JSON byte array.
     *
     * @param object
     *            the object to convert
     * @return the JSON byte array
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }

    /**
     * Create a byte array with a specific size filled with specified data.
     *
     * @param size the size of the byte array
     * @param data the data to put in the byte array
     * @return the JSON byte array
     */
    public static byte[] createByteArray(int size, String data) {
        byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            byteArray[i] = Byte.parseByte(data, 2);
        }
        return byteArray;
    }

    /**
     * A matcher that tests that the examined string represents the same instant as the reference datetime.
     */
    public static class ZonedDateTimeMatcher extends TypeSafeDiagnosingMatcher<String> {

        private final ZonedDateTime date;

        public ZonedDateTimeMatcher(ZonedDateTime date) {
            this.date = date;
        }

        @Override
        protected boolean matchesSafely(String item, Description mismatchDescription) {
            try {
                if (!date.isEqual(ZonedDateTime.parse(item))) {
                    mismatchDescription.appendText("was ").appendValue(item);
                    return false;
                }
                return true;
            } catch (DateTimeParseException e) {
                mismatchDescription.appendText("was ").appendValue(item)
                    .appendText(", which could not be parsed as a ZonedDateTime");
                return false;
            }

        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a String representing the same Instant as ").appendValue(date);
        }
    }

    /**
     * Creates a matcher that matches when the examined string reprensents the same instant as the reference datetime
     * @param date the reference datetime against which the examined string is checked
     */
    public static ZonedDateTimeMatcher sameInstant(ZonedDateTime date) {
        return new ZonedDateTimeMatcher(date);
    }

    /**
     * Verifies the equals/hashcode contract on the domain object.
     */
    @SuppressWarnings("unchecked")
    public static void equalsVerifier(Class clazz) throws Exception {
        Object domainObject1 = clazz.getConstructor().newInstance();
        assertThat(domainObject1.toString()).isNotNull();
        assertThat(domainObject1).isEqualTo(domainObject1);
        assertThat(domainObject1.hashCode()).isEqualTo(domainObject1.hashCode());
        // Test with an instance of another class
        Object testOtherObject = new Object();
        assertThat(domainObject1).isNotEqualTo(testOtherObject);
        assertThat(domainObject1).isNotEqualTo(null);
        // Test with an instance of the same class
        Object domainObject2 = clazz.getConstructor().newInstance();
        assertThat(domainObject1).isNotEqualTo(domainObject2);
        // HashCodes are equals because the objects are not persisted yet
        assertThat(domainObject1.hashCode()).isEqualTo(domainObject2.hashCode());
    }

    /**
     * Create a FormattingConversionService which use ISO date format, instead of the localized one.
     * @return the FormattingConversionService
     */
    public static FormattingConversionService createFormattingConversionService() {
        DefaultFormattingConversionService dfcs = new DefaultFormattingConversionService();
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(dfcs);
        return dfcs;
    }
}
