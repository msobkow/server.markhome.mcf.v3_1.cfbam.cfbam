// Description: Java 25 implJustProtementation of a Uuid6Type buffer

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
import java.io.*;
import java.math.*;
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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public class CFBamProtBuffUuid6Type
	extends CFBamProtBuffUuid6Def
	implements ICFBamUuid6Type
{
	protected ICFLibKeyHash256 requiredSchemaDefId;

	public CFBamProtBuffUuid6Type() {
		super();
		requiredSchemaDefId = CFLibDbKeyHash256.fromHex( ICFBamPubUuid6Type.SCHEMADEFID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamUuid6Type.CLASS_CODE );
	}

	@Override
	public void setJustProtRequiredContainerScope(ICFLibKeyHash256 argSchemaDefId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerSchemaDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSchemaDefId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope-args", 0, "found");
		}
		else if (found instanceof ICFBamSchemaDef) || (found instanceof ICFBamProtSchemaDef) || (found instanceof ICFBamPubSchemaDef)) {
			super.setJustProtRequiredContainerScope(argSchemaDefId);
		requiredSchemaDefId = argSchemaDefId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScope-args", "found", found, "ICFBamSchemaDefICFBamProtSchemaDefICFBamPubSchemaDef");
		}
	}

	@Override
	public void setJustProtRequiredContainerScope(ICFBamSchemaDef argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerSchemaDef", 1, "argObj");
		}
		else if ((argObj instanceof ICFBamSchemaDef) || (argObj instanceof ICFBamProtSchemaDef) || (argObj instanceof ICFBamPubSchemaDef)) {
			setJustProtRequiredSchemaDefId(argObj.getRequiredId());
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerSchemaDef", "argObj", argObj, "ICFBamSchemaDef, ICFBamProtSchemaDef), ICFBamPubSchemaDef)");
		}
	}

	@Override
	public void setJustProtRequiredContainerScope(ICFBamProtSchemaDef argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerSchemaDef", 1, "argObj");
		}
		else if ((argObj instanceof ICFBamProtSchemaDef) || (argObj instanceof ICFBamPubSchemaDef)) {
			setJustProtRequiredSchemaDefId(argObj.getRequiredId());
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerSchemaDef", "argObj", argObj, "ICFBamProtSchemaDef, ICFBamPubSchemaDef)");
		}
	}

	@Override
	public void setJustProtRequiredContainerScope(ICFBamPubSchemaDef argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerSchemaDef", 1, "argObj");
		}
		else if (argObj instanceof ICFBamPubSchemaDef) {
			setJustProtRequiredSchemaDefId(argObj.getRequiredId());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerSchemaDef", "argObj", argObj, "ICFBamPubSchemaDef");
		}
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchemaDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingCFBam.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSchemaDefId());
		return(targetRec);
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchemaDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingCFBam.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSchemaDefId());
		return(targetRec);
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchemaDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingCFBam.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamPubSchemaDef targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSchemaDefId());
		return(targetRec);
	}

	@Override
	public void setJustProtRequiredContainerScope(ICFLibKeyHash256 argSchemaDefId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerSchemaDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSchemaDefId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope-args", 0, "found");
		}
		else if (found instanceof ICFBamSchemaDef) || (found instanceof ICFBamProtSchemaDef) || (found instanceof ICFBamPubSchemaDef)) {
			super.setJustProtRequiredContainerScope(argSchemaDefId);
		requiredSchemaDefId = argSchemaDefId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScope-args", "found", found, "ICFBamSchemaDefICFBamProtSchemaDefICFBamPubSchemaDef");
		}
	}

	@Override
	public void setJustProtRequiredContainerSchemaDef(ICFLibKeyHash256 argSchemaDefId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerSchemaDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argSchemaDefId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScope-args", 0, "found");
		}
		else if (found instanceof ICFBamSchemaDef) || (found instanceof ICFBamProtSchemaDef) || (found instanceof ICFBamPubSchemaDef)) {
			super.setJustProtRequiredContainerScope(argSchemaDefId);
		requiredSchemaDefId = argSchemaDefId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScope-args", "found", found, "ICFBamSchemaDefICFBamProtSchemaDefICFBamPubSchemaDef");
		}
	}

	@Override
	public void setJustProtRequiredContainerSchemaDef(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerSchemaDef", 1, "argObj");
		}
		else {
			setJustProtRequiredSchemaDefId(argObj.getRequiredId());
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredSchemaDefId() {
		return(requiredSchemaDefId);
	}

	public void setRequiredSchemaDefId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredSchemaDefId",
				1,
				"value" );
		}
		requiredSchemaDefId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamProtUuid6Type rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6TypeH rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamValueHPKey ) {
			ICFBamProtValueHPKey rhs = (ICFBamValueHPKey)obj;
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6TypeBySchemaIdxKey rhs ) {
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6Type rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6TypeH rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtValueHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6TypeBySchemaIdxKey rhs ) {
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubUuid6Type rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubUuid6TypeH rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubValueHPKey rhs ) {
			if( getRequiredId() != null ) {
				if( rhs.getRequiredId() != null ) {
					if( ! getRequiredId().equals( rhs.getRequiredId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtUuid6TypeBySchemaIdxKey rhs ) {
			if( getRequiredSchemaDefId() != null ) {
				if( rhs.getRequiredSchemaDefId() != null ) {
					if( ! getRequiredSchemaDefId().equals( rhs.getRequiredSchemaDefId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredSchemaDefId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else {
			boolean retval = super.equals( obj );
			return( retval );
		}
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		hashCode = hashCode + getRequiredSchemaDefId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamProtUuid6Type rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFBamProtValueHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtUuid6TypeH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamUuid6TypeBySchemaIdxKey rhs ) {
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubUuid6Type rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubValueHPKey rhs ) {
			if( getRequiredRevision() < rhs.getRequiredRevision() ) {
				return( -1 );
			}
			else if( getRequiredRevision() > rhs.getRequiredRevision() ) {
				return( 1 );
			}
			if (getRequiredId() != null) {
				if (rhs.getRequiredId() != null) {
					cmp = getRequiredId().compareTo( rhs.getRequiredId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubUuid6TypeH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubUuid6TypeBySchemaIdxKey rhs ) {
			if (getRequiredSchemaDefId() != null) {
				if (rhs.getRequiredSchemaDefId() != null) {
					cmp = getRequiredSchemaDefId().compareTo( rhs.getRequiredSchemaDefId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredSchemaDefId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			cmp = super.compareTo( obj );
			return( cmp );
		}
	}

	@Override
	public void setJustProt( ICFBamValue src ) {
		if( src instanceof CFBamProtBuffUuid6Type ) {
			setJustProtUuid6Type( (CFBamProtBuffUuid6Type)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffUuid6Type" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamUuid6Type src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public void setJustProt( ICFBamValueH src ) {
		if( src instanceof ICFBamProtUuid6TypeH ) {
			setUuid6Type( (ICFBamProtUuid6TypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUuid6TypeH" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamUuid6TypeH src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public void setJustProt( ICFBamProtValue src ) {
		if( src instanceof CFBamProtBuffUuid6Type ) {
			setJustProtUuid6Type( (CFBamProtBuffUuid6Type)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffUuid6Type" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamProtUuid6Type src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public void setJustProt( ICFBamProtValueH src ) {
		if( src instanceof ICFBamProtUuid6TypeH ) {
			setUuid6Type( (ICFBamProtUuid6TypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUuid6TypeH" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamProtUuid6TypeH src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public void setJustProt( ICFBamPubValue src ) {
		if( src instanceof CFBamPubProtBuffUuid6Type ) {
			setJustProtUuid6Type( (CFBamPubProtBuffUuid6Type)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubProtBuffUuid6Type" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamPubUuid6Type src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public void setJustProt( ICFBamPubValueH src ) {
		if( src instanceof ICFBamPubUuid6TypeH ) {
			setUuid6Type( (ICFBamPubUuid6TypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamUuid6TypeH" );
		}
	}

	@Override
	public void setJustProtUuid6Type( ICFBamPubUuid6TypeH src ) {
		super.setJustProtUuid6Def( src );
		setJustProtRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
		setJustProtRequiredSchemaDefId(src.getRequiredSchemaDefId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredSchemaDefId=" + "\"" + getRequiredSchemaDefId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamProtBuffUuid6Type" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
