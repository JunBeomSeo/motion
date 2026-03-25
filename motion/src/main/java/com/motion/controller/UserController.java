package com.motion.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import com.motion.user.model.UserDTO;
import com.motion.user.service.*;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/motion")
	public String helloMotion() {
		return "user/motion";
	}

	@GetMapping("/loginPage")
	public String loginPage() {
		return "user/login";
	}

	@GetMapping("/accountPage")
	public String accountPage() {
		return "user/account";
	}

	/** 로그인 이메일 인증 */
	@PostMapping("/auth/send-code")
	@ResponseBody
	public String loginSendCode(@RequestParam String email, HttpSession session) {
		UserDTO dto = userService.selectUser(email);
		String msg = "";
		if (dto != null) {
			msg = "ok";
			userService.sendAuthCode(email, session);
		} else {
			msg = "no";
		}
		return msg;

	}

	/** 회원가입 이메일 인증 */
	@PostMapping("/account/send-code")
	@ResponseBody
	public String accountSendCode(@RequestParam String email, HttpSession session) {

		UserDTO dto = userService.selectUser(email);
		String msg = "";
		if (dto != null) {
			msg = "동일한 이메일 존재";
		} else {
			userService.sendAuthCode(email, session);
			session.setAttribute("loginUserDTO", dto);
			msg = "ok";
		}

		return msg;

	}

	@PostMapping("/authCodeCheck")
	@ResponseBody
	public String authCodeCheck(@RequestParam String authCode, HttpSession session) {

	    String sessionAuthCode = (String) session.getAttribute("authCode");
	    String email = (String) session.getAttribute("email");

	    if (authCode.equals(sessionAuthCode)) {
	        UserDTO dto = userService.selectUser(email);
	        session.setAttribute("loginUserDTO", dto);
	        return "ok";
	    } else {
	        return "no";
	    }
	}

	@PostMapping("/accountSubmit")
	public String accountSubmit(@RequestParam String userName, @RequestParam MultipartFile profile,
			HttpSession session) {
		String userEmail = (String) session.getAttribute("email");

		String fileName = null;

		if (profile != null && !profile.isEmpty()) {
			fileName = profile.getOriginalFilename();
			fileCody(profile);
		}

		userService.insertUser(userEmail, userName, fileName);

		UserDTO dto = new UserDTO();
		dto.setName(userName);
		dto.setEmail(userEmail);
		dto.setProfile(fileName);

		session.setAttribute("loginUserDTO", dto);

		return "redirect:/";
	}

	public void fileCody(MultipartFile upload) {

		try {
			String root = System.getProperty("user.dir") + "/profiles/";

			File dir = new File(root);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File f = new File(root + upload.getOriginalFilename());
			upload.transferTo(f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
