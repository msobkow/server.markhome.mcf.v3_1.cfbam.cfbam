// Description: Java 25 interface for a ClearSubDep2 record implementation

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

public interface ICFBamClearSubDep2 extends ICFBamClearDep
{
	public static final String S_CLEARSUBDEP1ID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 CLEARSUBDEP1ID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_CLEARSUBDEP1ID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public final static int CLASS_CODE = 0xa812;
	public final static String S_CLASS_CODE = "a812";

	public ICFBamClearSubDep1 getRequiredContainerClearSubDep1();
	public List<ICFBamClearSubDep3> getOptionalComponentsClearDep();
	public void setRequiredContainerClearSubDep1(ICFBamClearSubDep1 argObj);
	public void setRequiredContainerClearSubDep1(CFLibDbKeyHash256 argClearSubDep1Id);
	public CFLibDbKeyHash256 getRequiredClearSubDep1Id();
	public String getRequiredName();
	public void setRequiredName( String value );
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamScope src );
	public void setClearSubDep2( ICFBamClearSubDep2 src );
	public void set( ICFBamScopeH src );
	public void setClearSubDep2( ICFBamClearSubDep2H src );
}
