
// Description: Java 25 Default Factory implementation for Value buffers

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
 *	CFBamBuffValueFactory implementation of ICFBamValueFactory for Value
 */
public class CFBamBuffValueDefaultFactory
	implements ICFBamValueFactory
{
	public CFBamBuffValueDefaultFactory() {
	}

	@Override
	public ICFBamValueHPKey newHPKey() {
		ICFBamValueHPKey hpkey =
			new CFBamBuffValueHPKey();
		return( hpkey );
	}

	public CFBamBuffValueHPKey ensureHPKey(ICFBamValueHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffValueHPKey) {
			return( (CFBamBuffValueHPKey)key );
		}
		else {
			CFBamBuffValueHPKey mapped = new CFBamBuffValueHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByUNameIdxKey newByUNameIdxKey() {
		ICFBamValueByUNameIdxKey key =
			new CFBamBuffValueByUNameIdxKey();
		return( key );
	}

	public CFBamBuffValueByUNameIdxKey ensureByUNameIdxKey(ICFBamValueByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByUNameIdxKey) {
			return( (CFBamBuffValueByUNameIdxKey)key );
		}
		else {
			CFBamBuffValueByUNameIdxKey mapped = new CFBamBuffValueByUNameIdxKey();
			mapped.setRequiredScopeId( key.getRequiredScopeId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByScopeIdxKey newByScopeIdxKey() {
		ICFBamValueByScopeIdxKey key =
			new CFBamBuffValueByScopeIdxKey();
		return( key );
	}

	public CFBamBuffValueByScopeIdxKey ensureByScopeIdxKey(ICFBamValueByScopeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByScopeIdxKey) {
			return( (CFBamBuffValueByScopeIdxKey)key );
		}
		else {
			CFBamBuffValueByScopeIdxKey mapped = new CFBamBuffValueByScopeIdxKey();
			mapped.setRequiredScopeId( key.getRequiredScopeId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamValueByDefSchemaIdxKey key =
			new CFBamBuffValueByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffValueByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamValueByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByDefSchemaIdxKey) {
			return( (CFBamBuffValueByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffValueByDefSchemaIdxKey mapped = new CFBamBuffValueByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByPrevIdxKey newByPrevIdxKey() {
		ICFBamValueByPrevIdxKey key =
			new CFBamBuffValueByPrevIdxKey();
		return( key );
	}

	public CFBamBuffValueByPrevIdxKey ensureByPrevIdxKey(ICFBamValueByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByPrevIdxKey) {
			return( (CFBamBuffValueByPrevIdxKey)key );
		}
		else {
			CFBamBuffValueByPrevIdxKey mapped = new CFBamBuffValueByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByNextIdxKey newByNextIdxKey() {
		ICFBamValueByNextIdxKey key =
			new CFBamBuffValueByNextIdxKey();
		return( key );
	}

	public CFBamBuffValueByNextIdxKey ensureByNextIdxKey(ICFBamValueByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByNextIdxKey) {
			return( (CFBamBuffValueByNextIdxKey)key );
		}
		else {
			CFBamBuffValueByNextIdxKey mapped = new CFBamBuffValueByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByContPrevIdxKey newByContPrevIdxKey() {
		ICFBamValueByContPrevIdxKey key =
			new CFBamBuffValueByContPrevIdxKey();
		return( key );
	}

	public CFBamBuffValueByContPrevIdxKey ensureByContPrevIdxKey(ICFBamValueByContPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByContPrevIdxKey) {
			return( (CFBamBuffValueByContPrevIdxKey)key );
		}
		else {
			CFBamBuffValueByContPrevIdxKey mapped = new CFBamBuffValueByContPrevIdxKey();
			mapped.setRequiredScopeId( key.getRequiredScopeId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValueByContNextIdxKey newByContNextIdxKey() {
		ICFBamValueByContNextIdxKey key =
			new CFBamBuffValueByContNextIdxKey();
		return( key );
	}

	public CFBamBuffValueByContNextIdxKey ensureByContNextIdxKey(ICFBamValueByContNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffValueByContNextIdxKey) {
			return( (CFBamBuffValueByContNextIdxKey)key );
		}
		else {
			CFBamBuffValueByContNextIdxKey mapped = new CFBamBuffValueByContNextIdxKey();
			mapped.setRequiredScopeId( key.getRequiredScopeId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamValue newRec() {
		ICFBamValue rec =
			new CFBamBuffValue();
		return( rec );
	}

	public CFBamBuffValue ensureRec(ICFBamValue rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffValue) {
			return( (CFBamBuffValue)rec );
		}
		else {
			CFBamBuffValue mapped = new CFBamBuffValue();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamValueH newHRec() {
		ICFBamValueH hrec =
			new CFBamBuffValueH();
		return( hrec );
	}

	public CFBamBuffValueH ensureHRec(ICFBamValueH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffValueH) {
			return( (CFBamBuffValueH)hrec );
		}
		else {
			CFBamBuffValueH mapped = new CFBamBuffValueH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
