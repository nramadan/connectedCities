package challenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
   
    @Autowired
    private CityController controller;

    @Test
    public void isConnectedTest1() throws Exception {
    	
    	String isConnected = controller.isConnected("Boston", "Newark");
        assertThat(isConnected).isEqualTo("yes");
    }
    @Test
    public void isConnectedTest2() throws Exception {
    	
    	String isConnected = controller.isConnected("Boston", "Philadelphia");
    	assertThat(isConnected).isEqualTo("yes");
    }
    
    @Test
    public void isConnectedTest3() throws Exception {
    	
    	String isConnected = controller.isConnected("Boston", "New York");
        assertThat(isConnected).isEqualTo("yes");
    }
    
    
    @Test
    public void isConnectedTest4() throws Exception {
    	
    	String isConnected = controller.isConnected("Philadelphia", "Boston");
    	assertThat(isConnected).isEqualTo("yes");
    }

    @Test
    public void isConnectedTest5() throws Exception {
    	
    	String isConnected = controller.isConnected("Philadelphia", "New York");
    	assertThat(isConnected).isEqualTo("yes");
    }
    
    @Test
    public void isConnectedTest6() throws Exception {
    	
    	String isConnected = controller.isConnected("Philadelphia", "Newark");
    	assertThat(isConnected).isEqualTo("yes");
    }
   
    @Test
    public void isNotConnectedTest1() throws Exception {
    	
    	String isConnected = controller.isConnected("Philadelphia", "Trenton");
    	assertThat(isConnected).isEqualTo("no");
    }
    
    @Test
    public void isNotConnectedTest2() throws Exception {
    	
    	String isConnected = controller.isConnected("Philadelphia", "Albany");
    	assertThat(isConnected).isEqualTo("no");
    }
    
}