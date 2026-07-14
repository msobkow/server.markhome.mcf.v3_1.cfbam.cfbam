// Description: Java 25 interface for a EnumTag record implementation

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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

/**
 *	ICFBamEnumTag persistence instances have CodeVis Public, meaning that any user interface or referencing schema can access it.
 */
public interface ICFBamEnumTag
{
	public static final short ENUMCODE_MIN_VALUE = (short)0;
	public static final short ENUMCODE_MAX_VALUE = (short)32767;
	public static final String S_INIT_CREATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_CREATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_CREATED_BY);
	public static final String S_INIT_UPDATED_BY = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 INIT_UPDATED_BY = CFLibDbKeyHash256.fromHex(S_INIT_UPDATED_BY);
	public static final String S_ID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 ID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_ID_INIT_VALUE );
	public static final String S_DEFSCHEMAID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 DEFSCHEMAID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_DEFSCHEMAID_INIT_VALUE );
	public static final String S_ENUMID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 ENUMID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_ENUMID_INIT_VALUE );
	public static final short ENUMCODE_INIT_VALUE = (short)0;
	public static final String NAME_INIT_VALUE = new String( "" );
	public static final String S_PREVID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 PREVID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_PREVID_INIT_VALUE );
	public static final String S_NEXTID_INIT_VALUE = "0000000000000000000000000000000000000000000000000000000000000000";
	public static final CFLibDbKeyHash256 NEXTID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_NEXTID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa822;
	public final static String S_CLASS_CODE = "a822";

	public int getClassCode();

	public CFLibDbKeyHash256 getCreatedByUserId();
	public void setCreatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getCreatedAt();
	public void setCreatedAt( LocalDateTime value );
	public CFLibDbKeyHash256 getUpdatedByUserId();
	public void setUpdatedByUserId( CFLibDbKeyHash256 value );
	public LocalDateTime getUpdatedAt();
	public void setUpdatedAt( LocalDateTime value );

	public CFLibDbKeyHash256 getPKey();
	public void setPKey(CFLibDbKeyHash256 requiredId);
	
	public CFLibDbKeyHash256 getRequiredId();
	public void setRequiredId( CFLibDbKeyHash256 value );
	public int getRequiredRevision();
	public void setRequiredRevision( int value );

	public ICFBamEnumDef getRequiredContainerEnumDef();
	public ICFBamSchemaDef getOptionalLookupDefSchema();
	public ICFBamEnumTag getOptionalLookupPrev();
	public ICFBamEnumTag getOptionalLookupNext();
	public void setRequiredContainerEnumDef(ICFBamEnumDef argObj);
	public default void setRequiredContainerEnumDef(ICFBamProtEnumDef argObj) {
		if (argObj == null) {
			setRequiredContainerEnumDef((CFLibDbKeyHash256)null);
		}
		else {
			setRequiredContainerEnumDef(argObj.getRequiredId());
		}
	}

	public default void setRequiredContainerEnumDef(ICFBamPubEnumDef argObj) {
		if (argObj == null) {
			setRequiredContainerEnumDef((CFLibDbKeyHash256)null);
		}
		else {
			setRequiredContainerEnumDef(argObj.getRequiredId());
		}
	}

	public void setRequiredContainerEnumDef(CFLibDbKeyHash256 argEnumId);
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj);
	public default void setOptionalLookupDefSchema(ICFBamProtSchemaDef argObj) {
		if (argObj == null) {
			setOptionalLookupDefSchema((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupDefSchema(argObj.getRequiredId());
		}
	}

	public default void setOptionalLookupDefSchema(ICFBamPubSchemaDef argObj) {
		if (argObj == null) {
			setOptionalLookupDefSchema((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupDefSchema(argObj.getRequiredId());
		}
	}

	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId);
	public void setOptionalLookupPrev(ICFBamEnumTag argObj);
	public default void setOptionalLookupPrev(ICFBamProtEnumTag argObj) {
		if (argObj == null) {
			setOptionalLookupPrev((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupPrev(argObj.getRequiredId());
		}
	}

	public default void setOptionalLookupPrev(ICFBamPubEnumTag argObj) {
		if (argObj == null) {
			setOptionalLookupPrev((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupPrev(argObj.getRequiredId());
		}
	}

	public void setOptionalLookupPrev(CFLibDbKeyHash256 argPrevId);
	public void setOptionalLookupNext(ICFBamEnumTag argObj);
	public default void setOptionalLookupNext(ICFBamProtEnumTag argObj) {
		if (argObj == null) {
			setOptionalLookupNext((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupNext(argObj.getRequiredId());
		}
	}

	public default void setOptionalLookupNext(ICFBamPubEnumTag argObj) {
		if (argObj == null) {
			setOptionalLookupNext((CFLibDbKeyHash256)null);
		}
		else {
			setOptionalLookupNext(argObj.getRequiredId());
		}
	}

	public void setOptionalLookupNext(CFLibDbKeyHash256 argNextId);
	public CFLibDbKeyHash256 getOptionalDefSchemaId();
	public CFLibDbKeyHash256 getRequiredEnumId();
	public Short getOptionalEnumCode();
	public void setOptionalEnumCode( Short value );
	public String getRequiredName();
	public void setRequiredName( String value );
	public CFLibDbKeyHash256 getOptionalPrevId();
	public CFLibDbKeyHash256 getOptionalNextId();
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamEnumTag src );
	public void setEnumTag( ICFBamEnumTag src );
	public void set( ICFBamEnumTagH src );
	public void setEnumTag( ICFBamEnumTagH src );

	public void set( ICFBamProtEnumTag src );
	public void setEnumTag( ICFBamProtEnumTag src );
	public void set( ICFBamProtEnumTagH src );
	public void setEnumTag( ICFBamProtEnumTagH src );

	public void set( ICFBamPubEnumTag src );
	public void setEnumTag( ICFBamPubEnumTag src );
	public void set( ICFBamPubEnumTagH src );
	public void setEnumTag( ICFBamPubEnumTagH src );

	public String getXmlAttrFragment();

	@Override
	public String toString();
}
