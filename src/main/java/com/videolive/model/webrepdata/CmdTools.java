package com.videolive.model.webrepdata;
import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.transform.stream.StreamSource;


public class CmdTools 
{
    /*
     * ������ݵ��ַ���
     */
    public static  String WriteWebreps(Webreps iWebreps, String istrEncoding)
    {

        try 
        {  
        	JAXBContext context = JAXBContext.newInstance(Webreps.class); 
        	Marshaller m = context.createMarshaller();
        	
        	m.setProperty(Marshaller.JAXB_ENCODING, istrEncoding);
        	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        	
        	StringWriter writer = new StringWriter();
        	
            //FileOutputStream os = new FileOutputStream(istrFileName);
            
            m.marshal(iWebreps, writer);
            
           
            return  writer.toString();
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
            return  null;
        }  	
        
    } 
    
	/**
	 * ���ַ��м������
	 * @param istrXmlText   xml text
	 * @param istrEncoding  ����
	 * @return
	 */
	public static Webreps loadTmpDocById(String 	istrXmlText, String istrEncoding)
	{
		try
		{
			Webreps   elementObj = null;
			if (null != istrXmlText)
			{
				InputStream  strInStream = new ByteArrayInputStream(istrXmlText.getBytes(istrEncoding));//"gb2312"));
		
	        	JAXBContext context = JAXBContext.newInstance(Webreps.class);  
	        	  
	            Unmarshaller shaller = context.createUnmarshaller();  
	  
	     
	            JAXBElement<Webreps> root = shaller.unmarshal(new StreamSource(strInStream),Webreps.class);  
	            elementObj = root.getValue();  				
			}
			
			return elementObj;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return null;
		}		
	}    
}
