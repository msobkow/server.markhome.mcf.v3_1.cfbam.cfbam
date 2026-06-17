
// Description: Java 25 Default Factory implementation for EnumTag buffers

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
 *	CFBamBuffEnumTagFactory implementation of ICFBamEnumTagFactory for EnumTag
 */
public class CFBamBuffEnumTagDefaultFactory
	implements ICFBamEnumTagFactory
{
	public CFBamBuffEnumTagDefaultFactory() {
	}

	@Override
	public ICFBamEnumTagHPKey newHPKey() {
		ICFBamEnumTagHPKey hpkey =
			new CFBamBuffEnumTagHPKey();
		return( hpkey );
	}

	public CFBamBuffEnumTagHPKey ensureHPKey(ICFBamEnumTagHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffEnumTagHPKey) {
			return( (CFBamBuffEnumTagHPKey)key );
		}
		else {
			CFBamBuffEnumTagHPKey mapped = new CFBamBuffEnumTagHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagByEnumIdxKey newByEnumIdxKey() {
		ICFBamEnumTagByEnumIdxKey key =
			new CFBamBuffEnumTagByEnumIdxKey();
		return( key );
	}

	public CFBamBuffEnumTagByEnumIdxKey ensureByEnumIdxKey(ICFBamEnumTagByEnumIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffEnumTagByEnumIdxKey) {
			return( (CFBamBuffEnumTagByEnumIdxKey)key );
		}
		else {
			CFBamBuffEnumTagByEnumIdxKey mapped = new CFBamBuffEnumTagByEnumIdxKey();
			mapped.setRequiredEnumId( key.getRequiredEnumId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamEnumTagByDefSchemaIdxKey key =
			new CFBamBuffEnumTagByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffEnumTagByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamEnumTagByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffEnumTagByDefSchemaIdxKey) {
			return( (CFBamBuffEnumTagByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffEnumTagByDefSchemaIdxKey mapped = new CFBamBuffEnumTagByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagByEnumNameIdxKey newByEnumNameIdxKey() {
		ICFBamEnumTagByEnumNameIdxKey key =
			new CFBamBuffEnumTagByEnumNameIdxKey();
		return( key );
	}

	public CFBamBuffEnumTagByEnumNameIdxKey ensureByEnumNameIdxKey(ICFBamEnumTagByEnumNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffEnumTagByEnumNameIdxKey) {
			return( (CFBamBuffEnumTagByEnumNameIdxKey)key );
		}
		else {
			CFBamBuffEnumTagByEnumNameIdxKey mapped = new CFBamBuffEnumTagByEnumNameIdxKey();
			mapped.setRequiredEnumId( key.getRequiredEnumId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagByPrevIdxKey newByPrevIdxKey() {
		ICFBamEnumTagByPrevIdxKey key =
			new CFBamBuffEnumTagByPrevIdxKey();
		return( key );
	}

	public CFBamBuffEnumTagByPrevIdxKey ensureByPrevIdxKey(ICFBamEnumTagByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffEnumTagByPrevIdxKey) {
			return( (CFBamBuffEnumTagByPrevIdxKey)key );
		}
		else {
			CFBamBuffEnumTagByPrevIdxKey mapped = new CFBamBuffEnumTagByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagByNextIdxKey newByNextIdxKey() {
		ICFBamEnumTagByNextIdxKey key =
			new CFBamBuffEnumTagByNextIdxKey();
		return( key );
	}

	public CFBamBuffEnumTagByNextIdxKey ensureByNextIdxKey(ICFBamEnumTagByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffEnumTagByNextIdxKey) {
			return( (CFBamBuffEnumTagByNextIdxKey)key );
		}
		else {
			CFBamBuffEnumTagByNextIdxKey mapped = new CFBamBuffEnumTagByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTag newRec() {
		ICFBamEnumTag rec =
			new CFBamBuffEnumTag();
		return( rec );
	}

	public CFBamBuffEnumTag ensureRec(ICFBamEnumTag rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffEnumTag) {
			return( (CFBamBuffEnumTag)rec );
		}
		else {
			CFBamBuffEnumTag mapped = new CFBamBuffEnumTag();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamEnumTagH newHRec() {
		ICFBamEnumTagH hrec =
			new CFBamBuffEnumTagH();
		return( hrec );
	}

	public CFBamBuffEnumTagH ensureHRec(ICFBamEnumTagH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffEnumTagH) {
			return( (CFBamBuffEnumTagH)hrec );
		}
		else {
			CFBamBuffEnumTagH mapped = new CFBamBuffEnumTagH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
