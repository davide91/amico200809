package datatype;


//perchè non mi ascolti mai???
import java.util.regex.*;

public class Email {

	private String email;

	public Email(String email) {
		this.email = email;
	}

	public boolean controllaEMail()
	{
		//Setto il pattern per il confronto
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		//Eseguo il match della stringa data con il pattern
		Matcher m = p.matcher(this.email);

		//Restituisco risultato del match
		return m.matches();
	}
	
	@Override
	public String toString() {
		return this.email.toString();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.email == null) ? 0 : this.email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Email other = (Email) obj;
		if (this.email == null) {
			if (other.email != null)
				return false;
		} else if (!this.email.equals(other.email))
			return false;
		return true;
	}

	/**
	 * costruttori per Hibernate
	 * 
	 */

	public Email() {

	}

	public String getEmail() {
		return this.email;
	}

	@SuppressWarnings("unused")
	private void setEmail(String email) {
		this.email = email;
	}
}
