package com.imooc.test;
@Table("user")
public class Filter2 {

	@Column("id")
	private Integer id;
	@Column("name")
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
