package internet.test;

public class PersonInfo {

	private int _personID = 0;
	private String _personFirstName = "";
	private String _personLastName = "";
	private String _personDateBirth = "";
	private String _personAddress = "";

	public void SetPersonID(int personID) {
		_personID = personID;
	}
	public int GetPersonID() {
		return _personID;
	}
	public void SetPersonLastName(String personLastName) {
		_personLastName = personLastName;
	}
	public String GetPersonLastName() {
		return _personLastName;
	}
	public void SetPersonFirstName(String personFirstName) {
		_personFirstName = personFirstName;
	}
	public String GetPersonFirstName() {
		return _personFirstName;
	}
	public void SetPersonDateBirth(String personDateBirth) {
		_personDateBirth = personDateBirth;
	}
	public String GetPersonDateBirth() {
		return _personDateBirth;
	}
	public void SetPersonAddress(String personAddress) {
		_personAddress = personAddress;
	}
	public String GetPersonAddress() {
		return _personAddress;
	}
}
