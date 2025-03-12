// 03/07/25
import org.springframework.web.client.RestClient;
public class RestAPIExample {

	public RestAPIExample() {
		// Auto-generated constructor stub
	}
	
	public static void main(String argv[]) {
		// (1) create RestClient
		RestClient userClient = RestClient.create();
		
		// (2) request password for user "coolguy2"
		String password;
		password = userClient.get()
				.uri("http://cs-hydra.centre.edu:9000/request/coolguy2")
				.retrieve()
				.body(String.class);
		
		// (3) authenticate user "coolguy2" using username and password
		String auth = userClient.get()
				.uri("http://cs-hydra.centre.edu:9000/auth/coolguy2/{password}", password)
				.retrieve()
				.body(String.class);
	}

	
}
