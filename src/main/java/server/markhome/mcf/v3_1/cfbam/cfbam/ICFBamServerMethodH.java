// Description: Java 25 interface for a ServerMethod history object

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

/**
 *	ICFBamServerMethodH provides access to history records matching the CFBamServerMethod object change history.
 */
public interface ICFBamServerMethodH extends ICFBamScopeH
{
	public CFLibDbKeyHash256 getRequiredTableId();
	public void setRequiredTableId( CFLibDbKeyHash256 value );
	public CFLibDbKeyHash256 getOptionalDefSchemaId();
	public void setOptionalDefSchemaId( CFLibDbKeyHash256 value );
	public String getRequiredName();
	public void setRequiredName( String value );
	public String getOptionalShortName();
	public void setOptionalShortName( String value );
	public String getOptionalLabel();
	public void setOptionalLabel( String value );
	public String getOptionalShortDescription();
	public void setOptionalShortDescription( String value );
	public String getOptionalDescription();
	public void setOptionalDescription( String value );
	public String getOptionalSuffix();
	public void setOptionalSuffix( String value );
	public boolean getRequiredIsInstanceMethod();
	public void setRequiredIsInstanceMethod( boolean value );
	public boolean getRequiredIsServerOnly();
	public void setRequiredIsServerOnly( boolean value );
	public String getRequiredJMethodBody();
	public void setRequiredJMethodBody( String value );
	public String getRequiredCppMethodBody();
	public void setRequiredCppMethodBody( String value );
	public String getRequiredCsMethodBody();
	public void setRequiredCsMethodBody( String value );
	@Override
	public boolean equals( Object obj );

	@Override
	public int hashCode();

	//@Override
	public int compareTo( Object obj );

	public void setServerMethod( ICFBamServerMethod src );
	public void setServerMethod( ICFBamServerMethodH src );}
