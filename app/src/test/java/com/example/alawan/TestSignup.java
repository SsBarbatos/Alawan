package com.example.alawan;

/*
    import org.junit.Before;
    import org.junit.Test;
    import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.MockitoAnnotations;
    import retrofit2.Call;
    import retrofit2.Callback;
    import androidx.fragment.app.FragmentActivity;
    import androidx.fragment.app.FragmentManager;
    import androidx.navigation.NavController;
    import androidx.navigation.fragment.NavHostFragment;
    import com.example.alawan.Server.ServerInterface;
    import androidx.fragment.app.testing.FragmentScenario;
    import androidx.test.core.app.ActivityScenario;
    import androidx.test.ext.junit.runners.AndroidJUnit4;
    import org.junit.Before;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.mockito.Mock;
    import org.mockito.MockitoAnnotations;
    import retrofit2.Call;
    import retrofit2.Callback;

    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.doNothing;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.doNothing;
    import static org.mockito.Mockito.verify;
    import static org.mockito.Mockito.when;
*/

public class TestSignup {
    /*
        @Mock
        ServerInterface mockServerInterface;
        @Mock
        Call<Boolean> mockCall;
        @Mock
        NavController mockNavController;

        FragmentSignup fragmentSignup;

        @Before
        public void setup()
        {
            MockitoAnnotations.openMocks(this);

            // Mock the required dependencies
            FragmentActivity mockActivity = Mockito.mock(FragmentActivity.class);
            FragmentManager mockFragmentManager = Mockito.mock(FragmentManager.class);
            NavHostFragment mockNavHostFragment = Mockito.mock(NavHostFragment.class);

            // Provide custom implementation for toString() method
            when(mockActivity.toString()).thenReturn("MockFragmentActivity");

            when(mockActivity.getSupportFragmentManager()).thenReturn(mockFragmentManager);

            // Initialize the fragmentSignup with mocked dependencies
            fragmentSignup = new FragmentSignup();
            fragmentSignup.serverInterface = mockServerInterface;
            fragmentSignup.navController = mockNavController;
        }

        @Test
        public void signup_success()
        {
            // Mock server response
            when(mockServerInterface.addPerson(any(), any(), any(), any(), any()))
                    .thenReturn(mockCall);
            // Mock the enqueue method to do nothing
            doNothing().when(mockCall).enqueue(any(Callback.class));

            // Call the method
            fragmentSignup.Signup("Test", "Test", "test@test.com", "test");

            // Verify that the appropriate methods are called
            verify(mockServerInterface).addPerson("Test", "Test", "test@test.com", "test", null);
            verify(mockNavController).navigate(R.id.action_nav_signup_to_nav_login);
        }

        @Test
        public void signup_failure()
        {
            // Mock server response
            when(mockServerInterface.addPerson(any(), any(), any(), any(), any()))
                    .thenReturn(mockCall);
            // Mock the enqueue method to do nothing
            doNothing().when(mockCall).enqueue(any(Callback.class));

            // Call the method
            fragmentSignup.Signup("", "", "", "");

            // Verify that the appropriate methods are called
            verify(mockServerInterface).addPerson("", "", "", "", null);
            verify(mockNavController).navigate(R.id.action_nav_signup_to_nav_login);
        }
    */
}

