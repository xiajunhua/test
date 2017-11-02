package com.imooc.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 
 * @author xia
 * @date 2017年10月23日 下午4:43:39
 */
public class Test {
	public static void main(String[] args) {
		Filter f1=new Filter();
		Filter f2=new Filter();
		Filter f3=new Filter();
		Filter2 f4=new Filter2();
		Filter2 f5=new Filter2();
		f1.setId(10);
		f2.setUserName("tom");
		f3.setPhone("15512341111,12341234123,123123123123");
		f4.setId(10);
		f5.setName("abc");
		String s1=query(f1);
		String s2=query(f2);
		String s3=query(f3);
		String s4=query(f4);
		String s5=query(f5);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
	}
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String query(Object o){
		StringBuilder sb=new StringBuilder();
		//1 获取Class
		Class<? extends Object> c=o.getClass();
		//2 获取表的名字
		boolean isExists1=c.isAnnotationPresent(Table.class);
		if(!isExists1){
			return null;
		}
		Table tb = (Table) c.getAnnotation(Table.class);
		String tableName = tb.value();
		sb.append("select * from ").append(tableName).append(" where 1=1");
		//3 遍历所有的字段
		Field[] fArray =  c.getDeclaredFields();
		for (Field field : fArray) {
			//4  处理每个字段对应的sql
			//4.1 获取字段名
			boolean isExists2 = field.isAnnotationPresent(Column.class);
			if(!isExists2){
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			//4.2 获取字段值
			String fieldName=field.getName();
			String getMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			Object fieldValue=null;
			try {
			Method getMethod=c.getMethod(getMethodName);
			 fieldValue = getMethod.invoke(o);
			 if(fieldValue==null||fieldValue instanceof Integer&&(Integer)fieldValue==0){
				 continue;
			 }
			//4.3 拼接sql
			 if(fieldValue instanceof String){
				 if(((String) fieldValue).contains(",")){
					 sb.append(" and ").append(fieldName).append(" in ").append("(");
					 String values[]=((String) fieldValue).split(",");
					for (String string : values) {
						sb.append("'").append(string).append("',");
					}
					sb.deleteCharAt(sb.length()-1);
					 sb.append(")");
				 }else{
					 sb.append(" and ").append(fieldName).append("=").append("'").append(fieldValue).append("'");
				 }
				
			 }else if(fieldValue instanceof Integer){
				 sb.append(" and ").append(fieldName).append("=").append(fieldValue);
			 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return sb.toString();
	}
}
