package TableTennis.CallBacks;

import org.junit.jupiter.api.extension.*;

public class GlobalExtension implements AfterEachCallback, BeforeEachCallback
        , AfterAllCallback, BeforeAllCallback , AfterTestExecutionCallback,BeforeTestExecutionCallback{
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println(context.getDisplayName());
        System.out.println(context.getElement());
        System.out.println(context.getTestClass());
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {

    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {

    }
}
