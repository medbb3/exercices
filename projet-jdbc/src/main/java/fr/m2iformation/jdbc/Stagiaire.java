
public class Stagiaire {
	private int id;
	private String login;
	private String mdp;
	private int age;

	public Stagiaire(String login, String mdp, int age) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
