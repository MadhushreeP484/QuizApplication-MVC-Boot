package org.jsp.quiz.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsp.quiz.dao.BatchCodeQuizTestDao;
import org.jsp.quiz.dao.TrainerDao;
import org.jsp.quiz.dto.BatchCode;
import org.jsp.quiz.dto.QuizTest;
import org.jsp.quiz.dto.Student;
import org.jsp.quiz.dto.Trainer;
import org.jsp.quiz.helper.AES;
import org.jsp.quiz.helper.LoginHelper;
import org.jsp.quiz.helper.SendMailLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class TrainerService {

	@Autowired
	private TrainerDao trainerDao;

	@Autowired
	private SendMailLogic mailLogic;
	
	@Autowired
	private BatchCodeQuizTestDao codeQuizTestDao;

	public String signup(@Valid Trainer trainer, MultipartFile pic, ModelMap map)
			throws IOException, MessagingException {
		Trainer trainer1 = trainerDao.findByEmail(trainer.getEmail());
		Trainer trainer2 = trainerDao.findByMobile(trainer.getMobile());

		if (trainer1 == null && trainer2 == null) {
			trainer.setPassword(AES.encrypt(trainer.getPassword(), "123"));
			byte[] picture = new byte[pic.getInputStream().available()];
			pic.getInputStream().read(picture);
			trainer.setPicture(picture);

			trainer.setToken("abc" + new Random().nextInt() + "efg");

			trainerDao.save(trainer);

			mailLogic.sendMail(trainer);

			map.put("pass", "Link Sent Success, CLick on Link to Create Account");
			return "TrainerLogin";
		} else {
			if (trainer1 == trainer2) {
				if (trainer1.isVerified()) {
					map.put("fail", "Account Already Exists with the above Email and Mobile");
					map.put("trainer", trainer1);
					return "TrainerSignup";
				} else {
					trainer.setToken("abc" + new Random().nextInt() + "efg");
					trainerDao.save(trainer);
					mailLogic.sendMail(trainer);
					map.put("pass", "Link Sent Again, CLick on Link to Create Account");
					return "TrainerLogin";
				}
			} else {
				if (trainer1 == null) {
					map.put("fail", "Account with Number Already Exists");
					map.put("trainer", trainer2);
					return "TrainerSignup";
				} else {
					map.put("fail", "Account with Email Already Exists");
					map.put("trainer", trainer1);
					return "TrainerSignup";
				}
			}
		}

	}

	public String verifylink(int id, String token, ModelMap map) {
		Trainer trainer = trainerDao.findById(id);
		if (trainer == null) {
			map.put("fail", "Something Went Wrong");
			return "index";
		} else {
			if (trainer.getToken().equals(token)) {
				trainer.setVerified(true);
				trainerDao.save(trainer);
				map.put("pass", "Account verified Success");
				return "TrainerLogin";
			} else {
				map.put("fail", "Something wrong with the Link");
				return "index";
			}
		}
	}

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Trainer trainer = trainerDao.findByEmail(helper.getEmail());
		if (trainer == null)
			map.put("fail", "Invalid Email");
		else {
			if (trainer.isVerified()) {
				if (trainer.isApproved()) {
					//System.out.println(AES.decrypt("BbLaSG4xBOFcmzxBF11giQ==","123")); 
					if (AES.decrypt(trainer.getPassword(),"123").equals(helper.getPassword())) {
						session.setAttribute("trainer", trainer);
						map.put("pass", "Login Success");
						map.put("batchCode", codeQuizTestDao.fetchAllBatchCodes());
						return "TrainerHome";
					} else
						map.put("fail", "Incorrect Password");
				} else
					map.put("fail", "Wait for Admins Approval");
			} else
				map.put("fail", "First Verify Email");
		}
		return "TrainerLogin";
	}
	
	public String forgotPassword(ModelMap map) {
		map.put("pass", "Enter Details to change the password");
		return "TrainerPassword";
	}

	public String emailVerification(String email, ModelMap map) throws UnsupportedEncodingException, MessagingException {
		Trainer trainer=trainerDao.findByEmail(email);
		if(trainer==null) {
			map.put("fail", "Invalid email");
			return "TrainerLogin";
		}else {
			mailLogic.sendMailForChangePassword(trainer);
			map.put("pass", "Verification link sent");
			return "TrainerLogin";
		}
	}

	public String changePassword(String email,String password, ModelMap map) {
		Trainer trainer=trainerDao.findByEmail(email);
		if(trainer==null) {
			map.put("fail", "Something went wrong, try again!");
			return "TrainerPassword";
		}else {
				trainer.setPassword(AES.encrypt(password, "123"));
				trainerDao.save(trainer);
				map.put("pass", "Password Changed Successfully");
				return "TrainerLogin";
		}
	}

	public String enterPassword(String email, ModelMap map) {
		Trainer trainer=trainerDao.findByEmail(email);
		if(trainer==null) {
			map.put("fail", "Something went wrong, try again!");
			return "TrainerPassword";
		}else {
			map.put("pass", "Enter New Password");
			map.put("email", email);
			return "TrainerChangePassword";
		}
	}

	public String createBatchCode(HttpSession session, ModelMap map) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else
			return "AddBatchCode";
	}

	public String createBatchCode(BatchCode batchCode, HttpSession session, ModelMap map) {
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else {
			BatchCode code=codeQuizTestDao.fetchById(batchCode.getBatchCode());
			if(code==null) {
				batchCode.setBatchCreatedDate(LocalDate.now());
				codeQuizTestDao.saveBatchCode(batchCode);
				map.put("pass", "Batch Code is created");
				map.put("batchCode", codeQuizTestDao.fetchAllBatchCodes());
				return "TrainerHome";
			}else {
				map.put("fail", "BatchCode is already exists, And is Created in the year "+code.getBatchCreatedDate());
				return "AddBatchCode";
			}
		}
	}

	public String createTest(QuizTest quizTest,String batchCode, ModelMap map, HttpSession session) {
		int mark1=0, mark2=0, mark3=0;
		Trainer trainer=(Trainer) session.getAttribute("trainer");
		if(trainer==null) {
			map.put("fail", "Invalid Session");
			return "TrainerLogin";
		}else {
			if(quizTest.getMcqQuestions()!=null)
				mark1=quizTest.getMcqQuestions().stream().mapToInt(m->m.getMarks()).sum();
			if(quizTest.getTrueFalseQuestions()!=null)
				mark2=quizTest.getTrueFalseQuestions().stream().mapToInt(m->m.getMarks()).sum();
			if(quizTest.getDescriptionQuestions()!=null)
				mark3=quizTest.getDescriptionQuestions().stream().mapToInt(m->m.getMarks()).sum();
			
			quizTest.setMarks(mark1+mark2+mark3);
			BatchCode code=codeQuizTestDao.fetchById(batchCode);
			List<QuizTest> tests=code.getQuizTest();
			if(tests.isEmpty())
				tests=new ArrayList<>();
			tests.add(quizTest);
			code.setQuizTest(tests);
			codeQuizTestDao.saveTest(quizTest);
			map.put("pass", "Test Created");
			map.put("batchCode", codeQuizTestDao.fetchAllBatchCodes());
			return "TrainerHome";
		}
	}

}
