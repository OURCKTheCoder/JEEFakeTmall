package top.ourck.beans;

public class CounterBean {

	private int count;
	private int[] counts;

	public CounterBean() {
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getCounts() {
		return counts;
	}

	public void setCounts(int[] counts) {
		this.counts = counts;
	}
	
	public int getCountsByIndex(int i) {
		return counts[i];
	}
}
