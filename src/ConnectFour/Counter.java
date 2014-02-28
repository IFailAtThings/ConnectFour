package ConnectFour;

public class Counter {
	private CounterTypes counterType;
	
	
	public Counter(CounterTypes counterType) {
		this.counterType = counterType;
	}
	
	public CounterTypes getCounterType() {
		return counterType;
	}
	
	public void setCounterType(CounterTypes counterType) {
		this.counterType = counterType;
	}
	
}
