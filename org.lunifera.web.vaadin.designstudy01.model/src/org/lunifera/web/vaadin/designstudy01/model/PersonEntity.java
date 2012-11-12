package org.lunifera.web.vaadin.designstudy01.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.lunifera.web.vaadin.designstudy01.api.IPerson;

@Entity
public class PersonEntity implements IPerson {

	@Transient
	protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);

	@Id
	@Column
	@GeneratedValue
	private long id;
	@Column
	@Version
	private long version;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private int age;

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		long oldValue = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldValue, id);

	}

	@Override
	public long getVersion() {
		return version;
	}

	@Override
	public void setVersion(long version) {
		long oldValue = this.version;
		this.version = version;
		changeSupport.firePropertyChange("version", oldValue, version);
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		String oldValue = this.firstName;
		this.firstName = firstName;
		changeSupport.firePropertyChange("firstName", oldValue, firstName);
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		String oldValue = this.lastName;
		this.lastName = lastName;
		changeSupport.firePropertyChange("lastName", oldValue, lastName);
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		int oldValue = this.age;
		this.age = age;
		changeSupport.firePropertyChange("age", oldValue, age);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PersonEntity other = (PersonEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
