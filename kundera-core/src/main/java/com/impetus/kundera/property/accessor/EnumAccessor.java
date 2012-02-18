/*******************************************************************************
 * * Copyright 2012 Impetus Infotech.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 ******************************************************************************/
package com.impetus.kundera.property.accessor;

import java.io.UnsupportedEncodingException;

import com.impetus.kundera.Constants;
import com.impetus.kundera.property.PropertyAccessException;
import com.impetus.kundera.property.PropertyAccessor;

/**
 * @author amresh.singh
 * 
 */
@SuppressWarnings("rawtypes")
public class EnumAccessor implements PropertyAccessor<Enum>
{

    @Override
    public Enum fromBytes(Class targetClass, byte[] b) throws PropertyAccessException
    {
    	String s = null;
		try {
			s = new String(b, Constants.ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
    	return fromString(targetClass, s);
    }

    @Override
    public byte[] toBytes(Object object) throws PropertyAccessException
    {
        String s = toString(object);
        try
        {
            return ((String) s).getBytes(Constants.ENCODING);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new PropertyAccessException(e.getMessage());
        }
    }

    @Override
    public String toString(Object object)
    {
        Enum en = (Enum) object;
        return en.toString();
    }

    @Override
    public Enum fromString(Class targetClass, String string) throws PropertyAccessException
    {
        
		
		if (targetClass != null && string != null) {
			try {
				return Enum.valueOf(targetClass, string.trim().toUpperCase());
			} catch (IllegalArgumentException ex) {
				throw new PropertyAccessException(ex.getMessage());
			}
		}
         
        return null;
    }

}
