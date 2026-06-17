
// Description: Java 25 Default Factory implementation for SchemaRole buffers

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
 *	CFBamBuffSchemaRoleFactory implementation of ICFBamSchemaRoleFactory for SchemaRole
 */
public class CFBamBuffSchemaRoleDefaultFactory
	implements ICFBamSchemaRoleFactory
{
	public CFBamBuffSchemaRoleDefaultFactory() {
	}

	@Override
	public ICFBamSchemaRoleBySchemaIdxKey newBySchemaIdxKey() {
		ICFBamSchemaRoleBySchemaIdxKey key =
			new CFBamBuffSchemaRoleBySchemaIdxKey();
		return( key );
	}

	public CFBamBuffSchemaRoleBySchemaIdxKey ensureBySchemaIdxKey(ICFBamSchemaRoleBySchemaIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffSchemaRoleBySchemaIdxKey) {
			return( (CFBamBuffSchemaRoleBySchemaIdxKey)key );
		}
		else {
			CFBamBuffSchemaRoleBySchemaIdxKey mapped = new CFBamBuffSchemaRoleBySchemaIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			return( mapped );
		}
	}

	@Override
	public ICFBamSchemaRoleByRoleScopeIdxKey newByRoleScopeIdxKey() {
		ICFBamSchemaRoleByRoleScopeIdxKey key =
			new CFBamBuffSchemaRoleByRoleScopeIdxKey();
		return( key );
	}

	public CFBamBuffSchemaRoleByRoleScopeIdxKey ensureByRoleScopeIdxKey(ICFBamSchemaRoleByRoleScopeIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffSchemaRoleByRoleScopeIdxKey) {
			return( (CFBamBuffSchemaRoleByRoleScopeIdxKey)key );
		}
		else {
			CFBamBuffSchemaRoleByRoleScopeIdxKey mapped = new CFBamBuffSchemaRoleByRoleScopeIdxKey();
			mapped.setRequiredRoleScope( key.getRequiredRoleScope() );
			return( mapped );
		}
	}

	@Override
	public ICFBamSchemaRoleBySchRoleScpIdxKey newBySchRoleScpIdxKey() {
		ICFBamSchemaRoleBySchRoleScpIdxKey key =
			new CFBamBuffSchemaRoleBySchRoleScpIdxKey();
		return( key );
	}

	public CFBamBuffSchemaRoleBySchRoleScpIdxKey ensureBySchRoleScpIdxKey(ICFBamSchemaRoleBySchRoleScpIdxKey key) {
		if (key == null) {
			return( null );
		}
		else if (key instanceof CFBamBuffSchemaRoleBySchRoleScpIdxKey) {
			return( (CFBamBuffSchemaRoleBySchRoleScpIdxKey)key );
		}
		else {
			CFBamBuffSchemaRoleBySchRoleScpIdxKey mapped = new CFBamBuffSchemaRoleBySchRoleScpIdxKey();
			mapped.setRequiredSchemaDefId( key.getRequiredSchemaDefId() );
			mapped.setRequiredRoleScope( key.getRequiredRoleScope() );
			return( mapped );
		}
	}

	@Override
	public ICFBamSchemaRole newRec() {
		ICFBamSchemaRole rec =
			new CFBamBuffSchemaRole();
		return( rec );
	}

	public CFBamBuffSchemaRole ensureRec(ICFBamSchemaRole rec) {
		if( rec == null ) {
			return( null );
		}
		else if (rec instanceof CFBamBuffSchemaRole) {
			return( (CFBamBuffSchemaRole)rec );
		}
		else {
			CFBamBuffSchemaRole mapped = new CFBamBuffSchemaRole();
			mapped.set(rec);
			return( mapped );
		}
	}

	@Override
	public ICFBamSchemaRoleH newHRec() {
		ICFBamSchemaRoleH hrec =
			new CFBamBuffSchemaRoleH();
		return( hrec );
	}

	public CFBamBuffSchemaRoleH ensureHRec(ICFBamSchemaRoleH hrec) {
		if (hrec == null) {
			return( null );
		}
		else if( hrec instanceof CFBamBuffSchemaRoleH) {
			return( (CFBamBuffSchemaRoleH)hrec );
		}
		else {
			CFBamBuffSchemaRoleH mapped = new CFBamBuffSchemaRoleH();
			mapped.set(hrec);
			return( mapped );
		}
	}
}
