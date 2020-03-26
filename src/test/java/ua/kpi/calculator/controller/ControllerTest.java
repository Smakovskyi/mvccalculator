package ua.kpi.calculator.controller;


import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.kpi.calculator.view.View;

@RunWith(PowerMockRunner.class)
class ControllerTest {

  @Test
  public void processUserInputShouldProceed() throws IOException {
    InputStream inputStream = mock(InputStream.class);
    View view = mock(View.class);
    when(inputStream.read(anyObject(), 0, anyInt() ))
        .then(new Answer<Object>() {

          @Override
          public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            Object[] arguments = invocationOnMock.getArguments();
            byte[] data = (byte[]) arguments[0];
            final byte[] bytes = "add 2 3".getBytes("UTF-8");
            System.arraycopy(bytes, 0, data,0, bytes.length);
            return bytes.length;
          }
        });
    Controller controller = new Controller(inputStream, view);

    doNothing().when(view).printHelp();

    controller.processUserInput();

    verify(view).printHelp();
  }
}