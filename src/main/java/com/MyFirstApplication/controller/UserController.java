import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyFirstApplication.dto.UserLoginDTO;
import com.MyFirstApplication.model.Response;
import com.MyFirstApplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired 
	UserService userService;

	@PostMapping("/login")
	public  ResponseEntity<Response> login(@RequestBody UserLoginDTO userLoginDto)
	{
		Response response=userService.UserLogin(userLoginDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
