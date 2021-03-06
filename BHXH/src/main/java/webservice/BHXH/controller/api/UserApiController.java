package webservice.BHXH.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import webservice.BHXH.model.dto.UserDto;
import webservice.BHXH.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = -1)
public class UserApiController {
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public @ResponseBody UserDto add(@RequestBody UserDto dto) {
		userService.add(dto);
		return dto;
	}
	
	@PutMapping("/update")
	public @ResponseBody UserDto update(@RequestBody UserDto dto) {
		userService.update(dto);
		return dto;
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody void delete(@PathVariable(name = "id") Long id) {
		userService.delete(id);
	}

	@GetMapping("/getById")
	public @ResponseBody UserDto getById(@RequestParam Long id) {
		return userService.getById(id);
	}

	@GetMapping("/getAll")
	public @ResponseBody List<UserDto> getAll() {
		return userService.getAll();
	}

}
