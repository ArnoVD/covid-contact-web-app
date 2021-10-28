package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Person {

	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public Person(String userid, String email, String password, String firstName, String lastName) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public Person() {
	}

	// TODO: Implement password hashing

	private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//create MessageDigest
		MessageDigest crypt = MessageDigest.getInstance("SHA-512");
		//reset
		crypt.reset();
		//update
		byte[] passwordBytes = password.getBytes("UTF-8");
		crypt.update(passwordBytes);
		//digest
		byte[] digest = crypt.digest();
		//convert to String
		BigInteger digestAsBigInteger = new BigInteger(1, digest);
		//return hashed password
		return digestAsBigInteger.toString(16);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new DomainException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new DomainException("No email given");
		}

		// TODO: Bug fix -> Wanneer onderstaande code uit commentaar wordt gezet kan ik niet meer naar mijn overview

		/*
		String USERID_PATTERN =
				"^[a-zA-Z0-9_+&*-]+(?:\\."+
						"[a-zA-Z0-9_+&*-]+)*@" +
						"(?:[a-zA-Z0-9-]+\\.)+[a-z" +
						"A-Z]{2,7}$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new DomainException("Email not valid");
		}*/

		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	// TODO: Verander terug naar private
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			return false;
		}
		return getPassword().equals(password);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new DomainException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new DomainException("No last name given");
		}
		this.lastName = lastName;
	}

	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
}
