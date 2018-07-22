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

/**
 * 
 * @author wangwp
 */
public final class ClassUtils {

	public static boolean isStringOrPrimitive(Class<?> c) {
		if (c.isPrimitive()) {
			return true;
		}

		if (c == String.class) {
			return true;
		}

		if (c == Integer.class || c == Long.class || c == Float.class || c == Double.class || c == Short.class
				|| c == Byte.class || c == Boolean.class || c == Void.class || c == Character.class) {
			return true;
		}
		return false;
	}
}