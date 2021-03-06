/*
 * Copyright 2017 The OpenDSP Project
 *
 * The OpenDSP Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package mobi.f2time.dorado.rest.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * @author wangwp
 */
public final class ClassUtils {
	private static final Map<Class<?>,Object> primitiveDefaultHolder = new HashMap<>();
	
	static {
		primitiveDefaultHolder.put(int.class, 0);
		primitiveDefaultHolder.put(long.class, 0L);
		primitiveDefaultHolder.put(short.class, 0);
		primitiveDefaultHolder.put(float.class, 0.0F);
		primitiveDefaultHolder.put(byte.class, (byte) 0);
		primitiveDefaultHolder.put(double.class, 0.0D);
		primitiveDefaultHolder.put(boolean.class, false);
		primitiveDefaultHolder.put(char.class, (char) 0);
	}
	
	public static boolean isPrimitive(Class<?> type) {
		return type.isPrimitive();
	}

	public static boolean isWrapper(Class<?> type) {
		return (type == Integer.class) 
				|| (type == Long.class) || (type == Short.class) 
				|| (type == Float.class)|| (type == Double.class) 
				|| (type == Boolean.class) 
				|| (type == Character.class);
	}

	public static boolean isPrimitiveOrWrapper(Class<?> type) {
		return isPrimitive(type) || isWrapper(type);
	}
	
	public static boolean isSerializableType(Class<?> type) {
		return !isPrimitive(type) 
				&& !isWrapper(type) 
				&& type != String.class
				&& type != byte[].class
				&& type != Byte[].class;
	}

	public static Object primitiveDefault(Class<?> parameterType) {
		return primitiveDefaultHolder.get(parameterType);
	}
	
	
	public static void main(String[] args) throws Exception {
		String pattern = "^//*$";
		
		String path = "/";
		
		Pattern p = Pattern.compile(pattern);
		System.out.println(p.matcher(path).matches());
	}
}

