
// Description: Java 25 Default Factory implementation for IndexCol buffers

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
 *	CFBamBuffIndexColFactory implementation of ICFBamIndexColFactory for IndexCol
 */
public class CFBamBuffIndexColDefaultFactory
	implements ICFBamIndexColFactory
{
	public CFBamBuffIndexColDefaultFactory() {
	}

	@Override
	public ICFBamIndexColHPKey newHPKey() {
		ICFBamIndexColHPKey hpkey =
			new CFBamBuffIndexColHPKey();
		return( hpkey );
	}

	public CFBamBuffIndexColHPKey ensureHPKey(ICFBamIndexColHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffIndexColHPKey) {
			return( (CFBamBuffIndexColHPKey)key );
		}
		else {
			CFBamBuffIndexColHPKey mapped = new CFBamBuffIndexColHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByUNameIdxKey newByUNameIdxKey() {
		ICFBamIndexColByUNameIdxKey key =
			new CFBamBuffIndexColByUNameIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByUNameIdxKey ensureByUNameIdxKey(ICFBamIndexColByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByUNameIdxKey) {
			return( (CFBamBuffIndexColByUNameIdxKey)key );
		}
		else {
			CFBamBuffIndexColByUNameIdxKey mapped = new CFBamBuffIndexColByUNameIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByIndexIdxKey newByIndexIdxKey() {
		ICFBamIndexColByIndexIdxKey key =
			new CFBamBuffIndexColByIndexIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByIndexIdxKey ensureByIndexIdxKey(ICFBamIndexColByIndexIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByIndexIdxKey) {
			return( (CFBamBuffIndexColByIndexIdxKey)key );
		}
		else {
			CFBamBuffIndexColByIndexIdxKey mapped = new CFBamBuffIndexColByIndexIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamIndexColByDefSchemaIdxKey key =
			new CFBamBuffIndexColByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamIndexColByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByDefSchemaIdxKey) {
			return( (CFBamBuffIndexColByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffIndexColByDefSchemaIdxKey mapped = new CFBamBuffIndexColByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByColIdxKey newByColIdxKey() {
		ICFBamIndexColByColIdxKey key =
			new CFBamBuffIndexColByColIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByColIdxKey ensureByColIdxKey(ICFBamIndexColByColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByColIdxKey) {
			return( (CFBamBuffIndexColByColIdxKey)key );
		}
		else {
			CFBamBuffIndexColByColIdxKey mapped = new CFBamBuffIndexColByColIdxKey();
			mapped.setRequiredColumnId( key.getRequiredColumnId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByPrevIdxKey newByPrevIdxKey() {
		ICFBamIndexColByPrevIdxKey key =
			new CFBamBuffIndexColByPrevIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByPrevIdxKey ensureByPrevIdxKey(ICFBamIndexColByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByPrevIdxKey) {
			return( (CFBamBuffIndexColByPrevIdxKey)key );
		}
		else {
			CFBamBuffIndexColByPrevIdxKey mapped = new CFBamBuffIndexColByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByNextIdxKey newByNextIdxKey() {
		ICFBamIndexColByNextIdxKey key =
			new CFBamBuffIndexColByNextIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByNextIdxKey ensureByNextIdxKey(ICFBamIndexColByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByNextIdxKey) {
			return( (CFBamBuffIndexColByNextIdxKey)key );
		}
		else {
			CFBamBuffIndexColByNextIdxKey mapped = new CFBamBuffIndexColByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByIdxPrevIdxKey newByIdxPrevIdxKey() {
		ICFBamIndexColByIdxPrevIdxKey key =
			new CFBamBuffIndexColByIdxPrevIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByIdxPrevIdxKey ensureByIdxPrevIdxKey(ICFBamIndexColByIdxPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByIdxPrevIdxKey) {
			return( (CFBamBuffIndexColByIdxPrevIdxKey)key );
		}
		else {
			CFBamBuffIndexColByIdxPrevIdxKey mapped = new CFBamBuffIndexColByIdxPrevIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColByIdxNextIdxKey newByIdxNextIdxKey() {
		ICFBamIndexColByIdxNextIdxKey key =
			new CFBamBuffIndexColByIdxNextIdxKey();
		return( key );
	}

	public CFBamBuffIndexColByIdxNextIdxKey ensureByIdxNextIdxKey(ICFBamIndexColByIdxNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffIndexColByIdxNextIdxKey) {
			return( (CFBamBuffIndexColByIdxNextIdxKey)key );
		}
		else {
			CFBamBuffIndexColByIdxNextIdxKey mapped = new CFBamBuffIndexColByIdxNextIdxKey();
			mapped.setRequiredIndexId( key.getRequiredIndexId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexCol newRec() {
		ICFBamIndexCol rec =
			new CFBamBuffIndexCol();
		return( rec );
	}

	public CFBamBuffIndexCol ensureRec(ICFBamIndexCol rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffIndexCol) {
			return( (CFBamBuffIndexCol)rec );
		}
		else {
			CFBamBuffIndexCol mapped = new CFBamBuffIndexCol();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamIndexColH newHRec() {
		ICFBamIndexColH hrec =
			new CFBamBuffIndexColH();
		return( hrec );
	}

	public CFBamBuffIndexColH ensureHRec(ICFBamIndexColH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffIndexColH) {
			return( (CFBamBuffIndexColH)hrec );
		}
		else {
			CFBamBuffIndexColH mapped = new CFBamBuffIndexColH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
