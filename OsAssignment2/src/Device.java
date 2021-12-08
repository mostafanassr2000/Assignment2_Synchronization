import java.io.IOException;

public class Device implements Runnable {

	/* Attributes */
	private String name, type;
	private int connection;
	private Router router;

	/* Constructor */
	Device(Router router, String name, String type) {
		this.name = name;
		this.type = type;
		this.router = router;
		connection = 1;
	}

	/* Methods */
	@Override
	public void run() {
		try {
			router.connect(this);
			router.perform(this);
			router.logout(this);

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	/* Getters */
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getConnection() {
		return connection;
	}

	/* Setters */
	public void setConnection(int connection) {
		this.connection = connection;
	}

}
