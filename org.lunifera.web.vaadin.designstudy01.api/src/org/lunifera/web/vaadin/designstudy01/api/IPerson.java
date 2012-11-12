package org.lunifera.web.vaadin.designstudy01.api;

public interface IPerson {

	/**
	 * @return the id
	 */
	public abstract long getId();

	/**
	 * @param id
	 *            the id to set
	 */
	public abstract void setId(long id);

	/**
	 * @return the version
	 */
	public abstract long getVersion();

	/**
	 * @param version
	 *            the version to set
	 */
	public abstract void setVersion(long version);

	/**
	 * @return the firstName
	 */
	public abstract String getFirstName();

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public abstract void setFirstName(String firstName);

	/**
	 * @return the lastName
	 */
	public abstract String getLastName();

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public abstract void setLastName(String lastName);

	/**
	 * @return the age
	 */
	public abstract int getAge();

	/**
	 * @param age
	 *            the age to set
	 */
	public abstract void setAge(int age);

}