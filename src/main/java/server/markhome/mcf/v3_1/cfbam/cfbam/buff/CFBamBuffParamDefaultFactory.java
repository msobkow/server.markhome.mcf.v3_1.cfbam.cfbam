
// Description: Java 25 Default Factory implementation for Param buffers

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

package server.markhome.mcf.v3_1.cfbam.cfbam.buff;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.buff.*;
import server.markhome.mcf.v3_1.cfint.cfint.buff.*;

/*
 *	CFBamBuffParamFactory implementation of ICFBamParamFactory for Param
 */
public class CFBamBuffParamDefaultFactory
	implements ICFBamParamFactory
{
	public CFBamBuffParamDefaultFactory() {
	}

	@Override
	public ICFBamParamHPKey newHPKey() {
		ICFBamParamHPKey hpkey =
			new CFBamBuffParamHPKey();
		return( hpkey );
	}

	public CFBamBuffParamHPKey ensureHPKey(ICFBamParamHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffParamHPKey) {
			return( (CFBamBuffParamHPKey)key );
		}
		else {
			CFBamBuffParamHPKey mapped = new CFBamBuffParamHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByUNameIdxKey newByUNameIdxKey() {
		ICFBamParamByUNameIdxKey key =
			new CFBamBuffParamByUNameIdxKey();
		return( key );
	}

	public CFBamBuffParamByUNameIdxKey ensureByUNameIdxKey(ICFBamParamByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByUNameIdxKey) {
			return( (CFBamBuffParamByUNameIdxKey)key );
		}
		else {
			CFBamBuffParamByUNameIdxKey mapped = new CFBamBuffParamByUNameIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByServerMethodIdxKey newByServerMethodIdxKey() {
		ICFBamParamByServerMethodIdxKey key =
			new CFBamBuffParamByServerMethodIdxKey();
		return( key );
	}

	public CFBamBuffParamByServerMethodIdxKey ensureByServerMethodIdxKey(ICFBamParamByServerMethodIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByServerMethodIdxKey) {
			return( (CFBamBuffParamByServerMethodIdxKey)key );
		}
		else {
			CFBamBuffParamByServerMethodIdxKey mapped = new CFBamBuffParamByServerMethodIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamParamByDefSchemaIdxKey key =
			new CFBamBuffParamByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffParamByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamParamByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByDefSchemaIdxKey) {
			return( (CFBamBuffParamByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffParamByDefSchemaIdxKey mapped = new CFBamBuffParamByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByServerTypeIdxKey newByServerTypeIdxKey() {
		ICFBamParamByServerTypeIdxKey key =
			new CFBamBuffParamByServerTypeIdxKey();
		return( key );
	}

	public CFBamBuffParamByServerTypeIdxKey ensureByServerTypeIdxKey(ICFBamParamByServerTypeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByServerTypeIdxKey) {
			return( (CFBamBuffParamByServerTypeIdxKey)key );
		}
		else {
			CFBamBuffParamByServerTypeIdxKey mapped = new CFBamBuffParamByServerTypeIdxKey();
			mapped.setOptionalTypeId( key.getOptionalTypeId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByPrevIdxKey newByPrevIdxKey() {
		ICFBamParamByPrevIdxKey key =
			new CFBamBuffParamByPrevIdxKey();
		return( key );
	}

	public CFBamBuffParamByPrevIdxKey ensureByPrevIdxKey(ICFBamParamByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByPrevIdxKey) {
			return( (CFBamBuffParamByPrevIdxKey)key );
		}
		else {
			CFBamBuffParamByPrevIdxKey mapped = new CFBamBuffParamByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByNextIdxKey newByNextIdxKey() {
		ICFBamParamByNextIdxKey key =
			new CFBamBuffParamByNextIdxKey();
		return( key );
	}

	public CFBamBuffParamByNextIdxKey ensureByNextIdxKey(ICFBamParamByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByNextIdxKey) {
			return( (CFBamBuffParamByNextIdxKey)key );
		}
		else {
			CFBamBuffParamByNextIdxKey mapped = new CFBamBuffParamByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByContPrevIdxKey newByContPrevIdxKey() {
		ICFBamParamByContPrevIdxKey key =
			new CFBamBuffParamByContPrevIdxKey();
		return( key );
	}

	public CFBamBuffParamByContPrevIdxKey ensureByContPrevIdxKey(ICFBamParamByContPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByContPrevIdxKey) {
			return( (CFBamBuffParamByContPrevIdxKey)key );
		}
		else {
			CFBamBuffParamByContPrevIdxKey mapped = new CFBamBuffParamByContPrevIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParamByContNextIdxKey newByContNextIdxKey() {
		ICFBamParamByContNextIdxKey key =
			new CFBamBuffParamByContNextIdxKey();
		return( key );
	}

	public CFBamBuffParamByContNextIdxKey ensureByContNextIdxKey(ICFBamParamByContNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffParamByContNextIdxKey) {
			return( (CFBamBuffParamByContNextIdxKey)key );
		}
		else {
			CFBamBuffParamByContNextIdxKey mapped = new CFBamBuffParamByContNextIdxKey();
			mapped.setRequiredServerMethodId( key.getRequiredServerMethodId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamParam newRec() {
		ICFBamParam rec =
			new CFBamBuffParam();
		return( rec );
	}

	public CFBamBuffParam ensureRec(ICFBamParam rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffParam) {
			return( (CFBamBuffParam)rec );
		}
		else {
			CFBamBuffParam mapped = new CFBamBuffParam();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamParamH newHRec() {
		ICFBamParamH hrec =
			new CFBamBuffParamH();
		return( hrec );
	}

	public CFBamBuffParamH ensureHRec(ICFBamParamH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffParamH) {
			return( (CFBamBuffParamH)hrec );
		}
		else {
			CFBamBuffParamH mapped = new CFBamBuffParamH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
