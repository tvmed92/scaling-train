package in.reqres.tests;

import in.reqres.models.User;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertTrue;

public class RestTest extends BaseTest {

    private String userId;

    @BeforeMethod
    public void prepareData() {
        userId = createUser(new User("TestUser", "QA Automation Engineer"));
    }

    @Test(description = "проверяем, что пользователь создался")
    public void testUserCreating() {
        List<String> allUserIds = new ArrayList<String>();
        int pagesCount = getPagesCount();

        for (int i = 1; i < pagesCount + 1; i++) {
            List<String> ids = getUserIds("?page=" + i);
            allUserIds.addAll(ids);
        }
        assertTrue(allUserIds.contains(userId));
    }

    @Test(description = "проверяем возможность изменения должности пользователя")
    public void testUserChanging() {
        String newPosition = "Senior QA Automation Engineer";
        updateUser(new User("TestUser", newPosition), userId)
                .assertThat().body("job", equalTo(newPosition));
    }

    @Test(description = "проверяем возможность удаления пользователя")
    public void testUserDeleting() {
        deleteUser(userId);
        getUserById(userId).statusCode(SC_NO_CONTENT);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        if (getUserById(userId).extract().statusCode() != SC_NO_CONTENT) {
            deleteUser(userId);
        }
    }
}
