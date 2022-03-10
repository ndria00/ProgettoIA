package application.model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("card")
public class Card implements Comparable<Card>{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + suite;
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
		Card other = (Card) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (suite != other.suite)
			return false;
		return true;
	}
	@Param(0)
	private int id;
	@Param(1)
	private int suite;
	@Param(2)
	private Integer number;
	@Param(3)
	private int value;
	
	
	public int getSuite() {
		return suite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSuite(int suite) {
		this.suite = suite;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Card [suite=" + suite + ", number=" + number + ", value=" + value + "]";
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int compareTo(Card c) {
		return this.getNumber().compareTo(c.getNumber());
	}
	
}
