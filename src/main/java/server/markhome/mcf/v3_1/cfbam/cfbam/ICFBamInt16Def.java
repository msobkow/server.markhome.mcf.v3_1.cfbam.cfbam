// Description: Java 25 interface for a Int16Def record implementation

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
//import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamInt16Def extends ICFBamAtom
{
	public static final short INITVALUE_MIN_VALUE = (short)-32768;
	public static final short MINVALUE_MIN_VALUE = (short)-32768;
	public static final short MAXVALUE_MIN_VALUE = (short)-32768;
	public static final short INITVALUE_MAX_VALUE = (short)32767;
	public static final short MINVALUE_MAX_VALUE = (short)32767;
	public static final short MAXVALUE_MAX_VALUE = (short)32767;
	public static final short INITVALUE_INIT_VALUE = (short)0;
	public static final short MINVALUE_INIT_VALUE = (short)0;
	public static final short MAXVALUE_INIT_VALUE = (short)0;
	public final static int CLASS_CODE = 0xa823;
	public final static String S_CLASS_CODE = "a823";

	public Short getOptionalInitValue();
	public void setOptionalInitValue( Short value );
	public Short getOptionalMinValue();
	public void setOptionalMinValue( Short value );
	public Short getOptionalMaxValue();
	public void setOptionalMaxValue( Short value );
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamValue src );
	public void setInt16Def( ICFBamInt16Def src );
	public void set( ICFBamValueH src );
	public void setInt16Def( ICFBamInt16DefH src );
}
