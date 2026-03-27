// Description: Java 25 interface for a SchemaDef record implementation

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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

public interface ICFBamSchemaDef extends ICFBamScope
{
	public static final String S_CTENANTID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 CTENANTID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_CTENANTID_INIT_VALUE );
	public static final String S_MINORVERSIONID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 MINORVERSIONID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_MINORVERSIONID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public static final String COPYRIGHTPERIOD_INIT_VALUE = new String( "2020" );
	public static final String COPYRIGHTHOLDER_INIT_VALUE = new String( "YourNameHere" );
	public static final String AUTHOREMAIL_INIT_VALUE = new String( "" );
	public static final String PROJECTURL_INIT_VALUE = new String( "" );
	public static final String PUBLISHURI_INIT_VALUE = new String( "" );
	public final static int CLASS_CODE = 0xa802;
	public final static String S_CLASS_CODE = "a802";

	public ICFIntMinorVersion getRequiredContainerMinorVersion();
	public List<ICFBamTable> getOptionalComponentsTables();
	public List<ICFBamValue> getOptionalComponentsTypes();
	public List<ICFBamSchemaRef> getOptionalComponentsSchemaRefs();
	public List<ICFBamTweak> getOptionalComponentsTweaks();
	public ICFSecTenant getRequiredOwnerCTenant();
	public void setRequiredContainerMinorVersion(ICFIntMinorVersion argObj);
	public void setRequiredContainerMinorVersion(CFLibDbKeyHash256 argMinorVersionId);
	public void setRequiredOwnerCTenant(ICFSecTenant argObj);
	public void setRequiredOwnerCTenant(CFLibDbKeyHash256 argCTenantId);
	public CFLibDbKeyHash256 getRequiredCTenantId();
	public CFLibDbKeyHash256 getRequiredMinorVersionId();
	public String getRequiredName();
	public void setRequiredName( String value );
	public String getOptionalDbName();
	public void setOptionalDbName( String value );
	public String getOptionalShortName();
	public void setOptionalShortName( String value );
	public String getOptionalLabel();
	public void setOptionalLabel( String value );
	public String getOptionalShortDescription();
	public void setOptionalShortDescription( String value );
	public String getOptionalDescription();
	public void setOptionalDescription( String value );
	public String getRequiredCopyrightPeriod();
	public void setRequiredCopyrightPeriod( String value );
	public String getRequiredCopyrightHolder();
	public void setRequiredCopyrightHolder( String value );
	public String getRequiredAuthorEMail();
	public void setRequiredAuthorEMail( String value );
	public String getRequiredProjectURL();
	public void setRequiredProjectURL( String value );
	public String getRequiredPublishURI();
	public void setRequiredPublishURI( String value );
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamScope src );
	public void setSchemaDef( ICFBamSchemaDef src );
	public void set( ICFBamScopeH src );
	public void setSchemaDef( ICFBamSchemaDefH src );
}
