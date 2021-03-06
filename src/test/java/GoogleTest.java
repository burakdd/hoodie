import com.burakdede.Hoodie;
import com.burakdede.MethodMetadata;
import com.burakdede.ReflectiveInvocationHandler;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

/**
 * Created by burakdede on 16.10.15.
 */
public class GoogleTest {

    @Test
    public void testInvocation() {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com.tr");
        String homepage = google.gethomePage();
        assertNotNull(homepage);
    }

    @Test
    public void testGithubUserPage() {
        Google google = Hoodie.registerNewTarget(Google.class, "https://github.com");
        String githubPage = google.getGithubPageForUser("burakdd");
        assertNotNull(githubPage);
    }

    @Test
    public void testGoogleHeadFail() {
        Google google = Hoodie.registerNewTarget(Google.class, "https://www.google.com.tr");
        Response head = google.gethomeHead();
        assertNotNull(head);
    }

    @Test
    public void testQueryParam() {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com.tr");
        String response = google.gethomePageWithQueryParms("hello");
        assertNotNull(response);
    }


    @Test
    public void testMethodCache() throws NoSuchMethodException {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com");

        Method m = Google.class.getMethod("gethomePage", null);
        MethodMetadata metadataCached = ReflectiveInvocationHandler.getMethodFromCache(m);

        assertNotNull(metadataCached);
    }
}
