package com.connsec.otp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;


import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.connsec.constants.STATUS;
import com.connsec.domain.UserInfo;
import com.connsec.util.StringGenerator;

public abstract class AbstractOTPAuthn {
	private final static Logger logger = LoggerFactory.getLogger(AbstractOTPAuthn.class);

	protected int interval=30;
	
	protected int digits=6;
	
	protected String crypto="HmacSHA1";
	
	StringGenerator stringGenerator;
	
	private final JdbcTemplate jdbcTemplate;
	
	public static class OPT_TYPES{
		//手机
		public static int MOBILE = 2;
		//短信
		public static int SMS=3;
		//邮箱
		public static int EMAIL = 4;
		
		public static int TIMEBASED_OPT=5;
		
		public static int COUNTERBASED_OPT=6;
		
		public static int HOTP_OPT=7;
		
		public static int RSA_OPT=8;
		
	}
	
	private static final String DEFAULT_DEFAULT_INSERT_STATEMENT = "INSERT INTO ONE_TIME_PASSWORD(ID ,OPTTYPE,USERNAME,TOKEN,RECEIVER,CREATETIME,STATUS) VALUES(?,?,?,?,?,?,"+STATUS.NORMAL+")";
	
	private static final String DEFAULT_DEFAULT_SELECT_STATEMENT = "SELECT ID ,OPTTYPE,USERNAME,TOKEN,RECEIVER,CREATETIME FROM ONE_TIME_PASSWORD WHERE STATUS ="+STATUS.NORMAL+" AND  USERNAME = ? AND TOKEN = ? AND OPTTYPE = ?";
	
	private static final String DEFAULT_DEFAULT_DELETE_STATEMENT = "UPDATE ONE_TIME_PASSWORD SET  STATUS ="+STATUS.DELETE+" WHERE USERNAME = ? AND TOKEN = ? AND OPTTYPE = ?";
	
	abstract	public boolean produce(UserInfo userInfo);
	
	abstract	public boolean validate(UserInfo userInfo,String token);
	
	protected String defaultProduce(UserInfo userInfo){
		return genToken(userInfo);
	}
	
	public String genToken(UserInfo userInfo){
		if(stringGenerator==null){
			stringGenerator=new StringGenerator(StringGenerator.DEFAULT_CODE_NUMBER,digits);
		}
		return stringGenerator.randomGenerate();
	}
	

	public AbstractOTPAuthn(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	protected void insertDataBase(UserInfo userInfo,String token,String receiver,int type){
		jdbcTemplate.update(DEFAULT_DEFAULT_INSERT_STATEMENT, 
				new Object[] { 
					java.util.UUID.randomUUID(),
					type,
					userInfo.getUsername(),
					token,
					receiver,
					new Date()
					},
				new int[] {Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.TIMESTAMP}
		);
	}
	
	
	
	public boolean validateDataBase(UserInfo userInfo,String token,int type){
		OneTimePassword oneTimePassword=jdbcTemplate.queryForObject(
				DEFAULT_DEFAULT_SELECT_STATEMENT, 
				new OneTimePasswordRowMapper(),
				userInfo.getUsername(),
				token,
				type);
		
		if(oneTimePassword!=null){
			
			jdbcTemplate.update(DEFAULT_DEFAULT_DELETE_STATEMENT, 
					new Object[] { 
						userInfo.getUsername(),
						token,
						type},
					new int[] {Types.VARCHAR,Types.VARCHAR, Types.INTEGER}
			);
			DateTime currentdateTime = new DateTime();
			DateTime oneTimePwdData=DateTime.parse(oneTimePassword.getCreateTime(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
			Duration duration = new Duration(oneTimePwdData, currentdateTime);
			int intDuration=Integer.parseInt(duration.getStandardSeconds()+"");
			logger.debug("validate duration "+intDuration);
			logger.debug("validate result "+(intDuration<=interval));
			if(intDuration<=interval){
				return true;
			}
		}
		return false;
		
	}
	


	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * @return the digits
	 */
	public int getDigits() {
		return digits;
	}

	/**
	 * @param digits the digits to set
	 */
	public void setDigits(int digits) {
		this.digits = digits;
	}

	/**
	 * @return the crypto
	 */
	public String getCrypto() {
		return crypto;
	}

	/**
	 * @param crypto the crypto to set
	 */
	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}



	public class OneTimePasswordRowMapper   implements RowMapper<OneTimePassword> {

		public OneTimePassword mapRow(ResultSet rs, int rowNum) throws SQLException {
			OneTimePassword oneTimePassword=new OneTimePassword();
			oneTimePassword.setId(rs.getString("ID"));
			oneTimePassword.setType(rs.getInt("OPTTYPE"));
			oneTimePassword.setUsername(rs.getString("USERNAME"));
			oneTimePassword.setToken(rs.getString("TOKEN"));
			oneTimePassword.setUsername(rs.getString("USERNAME"));
			oneTimePassword.setReceiver(rs.getString("RECEIVER"));
			oneTimePassword.setCreateTime(rs.getString("CREATETIME"));
			return oneTimePassword;
		}
	}
	
	
}
