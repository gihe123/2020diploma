package com.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;






import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class Jacksonutil {
	
	
	public  static <T> List<T> jsontoObject(String[] strjson,Class<T> t) throws Exception
	{
		ObjectMapper objectMapper = new ObjectMapper();
		List<T> list =new ArrayList<T>();
		for(String str:strjson)
		{
			list.add(objectMapper.readValue(str, t));
		}	  	
	        return  list;
	}
	public static String objectToJson(Object obj)
	{
		ObjectMapper mapper =new ObjectMapper();		
		StringWriter stringWriter =new StringWriter();
		JsonGenerator generator =null;
		
		try {
			generator =mapper.getJsonFactory().createJsonGenerator(stringWriter);
			generator.writeObject(obj);
			generator.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			if(generator!=null)
			{
				try
				{
					generator.close();
				}catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return stringWriter.toString();
	}
}
