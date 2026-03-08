// Description: Java 25 interface for a EnumTag history object

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
 *	ICFBamEnumTagH provides access to history records matching the CFBamEnumTag object change history.
 */
public interface ICFBamEnumTagH
{
	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();
	public void setCreatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getCreatedAt();
	public void setCreatedAt( LocalDateTime value );
	public CFLibDbKeyHash256 getUpdatedByUserId();
	public void setUpdatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getUpdatedAt();
	public void setUpdatedAt( LocalDateTime value );

	public ICFBamEnumTagHPKey getPKey();
	public void setPKey( ICFBamEnumTagHPKey pkey );
	public CFLibDbKeyHash256 getAuditClusterId();
	public void setAuditClusterId(CFLibDbKeyHash256 auditClusterId);
	public LocalDateTime getAuditStamp();
	public void setAuditStamp(LocalDateTime auditStamp);
	public short getAuditActionId();
	public void setAuditActionId(short auditActionId);
	public int getRequiredRevision();
	public void setRequiredRevision(int revision);
	public CFLibDbKeyHash256 getAuditSessionId();
	public void setAuditSessionId(CFLibDbKeyHash256 auditSessionId);

	public CFLibDbKeyHash256 getRequiredId();
	public void setRequiredId( CFLibDbKeyHash256 requiredId );

	public CFLibDbKeyHash256 getOptionalDefSchemaId();
	public void setOptionalDefSchemaId( CFLibDbKeyHash256 value );
	public CFLibDbKeyHash256 getRequiredEnumId();
	public void setRequiredEnumId( CFLibDbKeyHash256 value );
	public Short getOptionalEnumCode();
	public void setOptionalEnumCode( Short value );
	public String getRequiredName();
	public void setRequiredName( String value );
	public CFLibDbKeyHash256 getOptionalPrevId();
	public void setOptionalPrevId( CFLibDbKeyHash256 value );
	public CFLibDbKeyHash256 getOptionalNextId();
	public void setOptionalNextId( CFLibDbKeyHash256 value );
	@Override
	public boolean equals( Object obj );

	@Override
	public int hashCode();

	//@Override
	public int compareTo( Object obj );

	public void set( ICFBamEnumTag src );
	public void set( ICFBamEnumTagH src );
	public void setEnumTag( ICFBamEnumTag src );
	public void setEnumTag( ICFBamEnumTagH src );
	public String getXmlAttrFragment();

	@Override
	public String toString();
}
