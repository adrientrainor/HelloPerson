// 03/ 10/ 25
import java.util.ArrayList;

import org.springframework.web.client.RestClient;
public class RESTserver {

	public RESTserver() {
		// Auto-generated constructor stub
	}
	
	public record CourseAddress(String name, String description, String location) {};
	public record CourseAddressResponse(String request, Boolean successful, String message, ArrayList<CourseAddress> data) {};
	
	public record CourseDesc(String season, int year, String dept, String num, String section, String name, String instructor, String meetingTime, String building, String roomNumber, String id) {};
	public record CourseDescResponse(String request, Boolean successful, String message, CourseDesc data) {};

	public static void main(String argv[]) {
		// for all courses:
		// get course name & instructor
		
		// cd-hydra.centre.edu:9000/v1/Archive/course
			// ^ get course address from here
		// cd-hydra.centre.edu:9000/v1/Archive/course/COURSECODE
			// ^ course info here
		
		RestClient client = RestClient.create();
		String uriBase = "http://cs-hydra.centre.edu:9000/v1/Archive/course";
		CourseAddressResponse res = client.get()
				.uri(uriBase)
				.retrieve()
				.body(CourseAddressResponse.class);
		// System.out.println(res);
		ArrayList<String[]> allCourseInfo = new ArrayList<String[]>();
			// [code, name, prof, location]
		// ArrayList<CourseDesc> allCourseDescs = new ArrayList<CourseDesc>();
		for (CourseAddress cA : res.data()) {
			String courseID = cA.name();
			String courseLoc = uriBase+"/"+courseID;
			CourseDescResponse res2 = client.get()
					.uri(courseLoc)
					.retrieve()
					.body(CourseDescResponse.class);
			// System.out.println(res2);
			// allCourseDescs.add(res2.data());
			String courseInfo[] = {cA.name(), res2.data().name(), res2.data().instructor(), courseLoc};
			allCourseInfo.add(courseInfo);
		}
		
		// Print course info
		for (String[] ci : allCourseInfo) {
			System.out.println(ci[0]+" "+ci[1]+" - "+ci[2]);
		}
	}
	
}
