// Description: Java 25 interface for a Value record declementation

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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;

import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

/**
 *	ICFBamValue persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFBamValue
{
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_SCOPEID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 SCOPEID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_SCOPEID_INIT_VALUE );
	public static final String S_ID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 ID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_ID_INIT_VALUE );
	public static final String S_DEFSCHEMAID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 DEFSCHEMAID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_DEFSCHEMAID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public final static boolean ISNULLABLE_INIT_VALUE = false;
	public final static boolean IMPLEMENTSPOLYMORPH_INIT_VALUE = false;
	public static final String S_PREVID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 PREVID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_PREVID_INIT_VALUE );
	public static final String S_NEXTID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 NEXTID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_NEXTID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa80d;
	public final static String S_CLASS_CODE = "a80d";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();

	public void setCreatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getCreatedAt();

	public void setCreatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getUpdatedByUserId();

	public void setUpdatedByUserId( CFLibDbKeyHash256 value );

	public LocalDateTime getUpdatedAt();

	public void setUpdatedAt( LocalDateTime value );

	public ICFLibKeyHash256 getPKey();
	public void setPKey(ICFLibKeyHash256 requiredId);
	public List<ICFBamTableCol> getOptionalChildrenRefTableCol();

	public List<ICFBamIndexCol> getOptionalChildrenRefIndexCol();

	public ICFLibKeyHash256 getRequiredId();
	public void setRequiredId( ICFLibKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public ICFBamScope getRequiredContainerScope();

	public void setRequiredContainerScope(ICFLibKeyHash256 argScopeId);

	public void setRequiredContainerScope(ICFBamScope argObj);

	public void setRequiredContainerScope(ICFBamProtScope argObj);

	public void setRequiredContainerScope(ICFBamPubScope argObj);

	public ICFBamSchemaDef getOptionalLookupDefSchema();

	public void setOptionalLookupDefSchema(ICFLibKeyHash256 argDefSchemaId);

	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj);

	public void setOptionalLookupDefSchema(ICFBamProtSchemaDef argObj);

	public void setOptionalLookupDefSchema(ICFBamPubSchemaDef argObj);

	public ICFBamValue getOptionalLookupPrev();

	public void setOptionalLookupPrev(ICFLibKeyHash256 argPrevId);

	public void setOptionalLookupPrev(ICFBamValue argObj);

	public void setOptionalLookupPrev(ICFBamProtValue argObj);

	public void setOptionalLookupPrev(ICFBamPubValue argObj);

	public ICFBamValue getOptionalLookupNext();

	public void setOptionalLookupNext(ICFLibKeyHash256 argNextId);

	public void setOptionalLookupNext(ICFBamValue argObj);

	public void setOptionalLookupNext(ICFBamProtValue argObj);

	public void setOptionalLookupNext(ICFBamPubValue argObj);

	public ICFLibKeyHash256 getRequiredScopeId();
	public void setRequiredScopeId( ICFLibKeyHash256 value );
	public ICFLibKeyHash256 getOptionalDefSchemaId();
	public void setOptionalDefSchemaId( ICFLibKeyHash256 value );
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
	public String getOptionalDefaultXmlValue();
	public void setOptionalDefaultXmlValue( String value );
	public boolean getRequiredIsNullable();
	public void setRequiredIsNullable( boolean value );
	public Boolean getOptionalGenerateId();
	public void setOptionalGenerateId( Boolean value );
	public boolean getRequiredImplementsPolymorph();
	public void setRequiredImplementsPolymorph( boolean value );
	public ICFLibKeyHash256 getOptionalPrevId();
	public void setOptionalPrevId( ICFLibKeyHash256 value );
	public ICFLibKeyHash256 getOptionalNextId();
	public void setOptionalNextId( ICFLibKeyHash256 value );
	public boolean equals( Object obj );

	public int hashCode();

	public int compareTo( Object obj );

	public void set( ICFBamValue src );

	public void setValue( ICFBamValue src );

	public void set( ICFBamValueH src );

	public void setValue( ICFBamValueH src );

	public void set( ICFBamProtValue src );

	public void setValue( ICFBamProtValue src );

	public void set( ICFBamProtValueH src );

	public void setValue( ICFBamProtValueH src );

	public void set( ICFBamPubValue src );

	public void setValue( ICFBamPubValue src );

	public void set( ICFBamPubValueH src );

	public void setValue( ICFBamPubValueH src );

	public String getXmlAttrFragment();

	public String toString();
}
