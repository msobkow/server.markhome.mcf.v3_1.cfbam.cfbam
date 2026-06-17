
// Description: Java 25 Default Factory implementation for RelationCol buffers

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
 *	CFBamBuffRelationColFactory implementation of ICFBamRelationColFactory for RelationCol
 */
public class CFBamBuffRelationColDefaultFactory
	implements ICFBamRelationColFactory
{
	public CFBamBuffRelationColDefaultFactory() {
	}

	@Override
	public ICFBamRelationColHPKey newHPKey() {
		ICFBamRelationColHPKey hpkey =
			new CFBamBuffRelationColHPKey();
		return( hpkey );
	}

	public CFBamBuffRelationColHPKey ensureHPKey(ICFBamRelationColHPKey key) {
		if (key == null) {
			return( null );
		}
		else if( key instanceof CFBamBuffRelationColHPKey) {
			return( (CFBamBuffRelationColHPKey)key );
		}
		else {
			CFBamBuffRelationColHPKey mapped = new CFBamBuffRelationColHPKey();
			mapped.setAuditClusterId(key.getAuditClusterId());
			mapped.setAuditActionId(key.getAuditActionId());
			mapped.setAuditSessionId(key.getAuditSessionId());
			mapped.setAuditStamp(key.getAuditStamp());
			mapped.setRequiredId( key.getRequiredId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByUNameIdxKey newByUNameIdxKey() {
		ICFBamRelationColByUNameIdxKey key =
			new CFBamBuffRelationColByUNameIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByUNameIdxKey ensureByUNameIdxKey(ICFBamRelationColByUNameIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByUNameIdxKey) {
			return( (CFBamBuffRelationColByUNameIdxKey)key );
		}
		else {
			CFBamBuffRelationColByUNameIdxKey mapped = new CFBamBuffRelationColByUNameIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setRequiredName( key.getRequiredName() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByRelationIdxKey newByRelationIdxKey() {
		ICFBamRelationColByRelationIdxKey key =
			new CFBamBuffRelationColByRelationIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByRelationIdxKey ensureByRelationIdxKey(ICFBamRelationColByRelationIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByRelationIdxKey) {
			return( (CFBamBuffRelationColByRelationIdxKey)key );
		}
		else {
			CFBamBuffRelationColByRelationIdxKey mapped = new CFBamBuffRelationColByRelationIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByDefSchemaIdxKey newByDefSchemaIdxKey() {
		ICFBamRelationColByDefSchemaIdxKey key =
			new CFBamBuffRelationColByDefSchemaIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByDefSchemaIdxKey ensureByDefSchemaIdxKey(ICFBamRelationColByDefSchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByDefSchemaIdxKey) {
			return( (CFBamBuffRelationColByDefSchemaIdxKey)key );
		}
		else {
			CFBamBuffRelationColByDefSchemaIdxKey mapped = new CFBamBuffRelationColByDefSchemaIdxKey();
			mapped.setOptionalDefSchemaId( key.getOptionalDefSchemaId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByFromColIdxKey newByFromColIdxKey() {
		ICFBamRelationColByFromColIdxKey key =
			new CFBamBuffRelationColByFromColIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByFromColIdxKey ensureByFromColIdxKey(ICFBamRelationColByFromColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByFromColIdxKey) {
			return( (CFBamBuffRelationColByFromColIdxKey)key );
		}
		else {
			CFBamBuffRelationColByFromColIdxKey mapped = new CFBamBuffRelationColByFromColIdxKey();
			mapped.setRequiredFromColId( key.getRequiredFromColId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByToColIdxKey newByToColIdxKey() {
		ICFBamRelationColByToColIdxKey key =
			new CFBamBuffRelationColByToColIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByToColIdxKey ensureByToColIdxKey(ICFBamRelationColByToColIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByToColIdxKey) {
			return( (CFBamBuffRelationColByToColIdxKey)key );
		}
		else {
			CFBamBuffRelationColByToColIdxKey mapped = new CFBamBuffRelationColByToColIdxKey();
			mapped.setRequiredToColId( key.getRequiredToColId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByPrevIdxKey newByPrevIdxKey() {
		ICFBamRelationColByPrevIdxKey key =
			new CFBamBuffRelationColByPrevIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByPrevIdxKey ensureByPrevIdxKey(ICFBamRelationColByPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByPrevIdxKey) {
			return( (CFBamBuffRelationColByPrevIdxKey)key );
		}
		else {
			CFBamBuffRelationColByPrevIdxKey mapped = new CFBamBuffRelationColByPrevIdxKey();
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByNextIdxKey newByNextIdxKey() {
		ICFBamRelationColByNextIdxKey key =
			new CFBamBuffRelationColByNextIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByNextIdxKey ensureByNextIdxKey(ICFBamRelationColByNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByNextIdxKey) {
			return( (CFBamBuffRelationColByNextIdxKey)key );
		}
		else {
			CFBamBuffRelationColByNextIdxKey mapped = new CFBamBuffRelationColByNextIdxKey();
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByRelPrevIdxKey newByRelPrevIdxKey() {
		ICFBamRelationColByRelPrevIdxKey key =
			new CFBamBuffRelationColByRelPrevIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByRelPrevIdxKey ensureByRelPrevIdxKey(ICFBamRelationColByRelPrevIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByRelPrevIdxKey) {
			return( (CFBamBuffRelationColByRelPrevIdxKey)key );
		}
		else {
			CFBamBuffRelationColByRelPrevIdxKey mapped = new CFBamBuffRelationColByRelPrevIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setOptionalPrevId( key.getOptionalPrevId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColByRelNextIdxKey newByRelNextIdxKey() {
		ICFBamRelationColByRelNextIdxKey key =
			new CFBamBuffRelationColByRelNextIdxKey();
		return( key );
	}

	public CFBamBuffRelationColByRelNextIdxKey ensureByRelNextIdxKey(ICFBamRelationColByRelNextIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffRelationColByRelNextIdxKey) {
			return( (CFBamBuffRelationColByRelNextIdxKey)key );
		}
		else {
			CFBamBuffRelationColByRelNextIdxKey mapped = new CFBamBuffRelationColByRelNextIdxKey();
			mapped.setRequiredRelationId( key.getRequiredRelationId() );
			mapped.setOptionalNextId( key.getOptionalNextId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationCol newRec() {
		ICFBamRelationCol rec =
			new CFBamBuffRelationCol();
		return( rec );
	}

	public CFBamBuffRelationCol ensureRec(ICFBamRelationCol rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffRelationCol) {
			return( (CFBamBuffRelationCol)rec );
		}
		else {
			CFBamBuffRelationCol mapped = new CFBamBuffRelationCol();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamRelationColH newHRec() {
		ICFBamRelationColH hrec =
			new CFBamBuffRelationColH();
		return( hrec );
	}

	public CFBamBuffRelationColH ensureHRec(ICFBamRelationColH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffRelationColH) {
			return( (CFBamBuffRelationColH)hrec );
		}
		else {
			CFBamBuffRelationColH mapped = new CFBamBuffRelationColH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
