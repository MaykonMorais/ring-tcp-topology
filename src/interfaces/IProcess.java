package interfaces;

public interface IProcess {
	public void execute();
	public void serviceExecutor();
	public String[] handleMessage(String message);
}
