package com.connsec.web.contorller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.connsec.constants.OPERATEMESSAGE;
import com.connsec.dao.service.OtpTokenCofingService;
import com.connsec.domain.OtpTokenCofing;
import com.connsec.web.WebContext;
import com.connsec.web.message.Message;
import com.connsec.web.message.MessageType;

@Controller
@RequestMapping(value={"/otptokencofing"})
public class OtpTokenCofingController {


		final static Logger log = Logger.getLogger(OtpTokenCofingController.class);
		
		@Autowired
		private OtpTokenCofingService otpTokenCofingService;
		
		/**
		 * 读取
		 * @return
		 */
		@RequestMapping(value={"/configtype"})
		public ModelAndView configType(){
			OtpTokenCofing otpTokenCofing = otpTokenCofingService.get("");
			return new ModelAndView("otptokencofing/configtype","model",otpTokenCofing);
		}
		
		/**
		 * 更新
		 * @param sysConfig
		 * @return
		 */
		@RequestMapping(value={"/updatetype"})
		@ResponseBody
		public Message updatConfigType(@Valid @ModelAttribute("otpTokenCofing") OtpTokenCofing otpTokenCofing,BindingResult result) {
			log.debug(" OtpTokenCofing : "+otpTokenCofing);
			if(otpTokenCofingService.update(otpTokenCofing)) {
				return new Message((OPERATEMESSAGE.UPDATE_SUCCESS),MessageType.success);
			} else {
				return new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
			}
		}
		
		/**
		 * 读取
		 * @return
		 */
		@RequestMapping(value={"/config"})
		public ModelAndView config(){
			OtpTokenCofing otpTokenCofing = otpTokenCofingService.get("");
			return new ModelAndView("otptokencofing/config","model",otpTokenCofing);
		}
		
		/**
		 * 更新
		 * @param sysConfig
		 * @return
		 */
		@RequestMapping(value={"/update"})
		@ResponseBody
		public Message updatConfig(@Valid @ModelAttribute("otpTokenCofing") OtpTokenCofing otpTokenCofing,BindingResult result) {
			log.debug("OtpTokenCofing : "+otpTokenCofing);
			if(otpTokenCofingService.update(otpTokenCofing)) {
				return new Message((OPERATEMESSAGE.UPDATE_SUCCESS),MessageType.success);
			} else {
				return new Message((OPERATEMESSAGE.UPDATE_ERROR),MessageType.error);
			}
		}
		

}
