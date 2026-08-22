// Description: Java 25 implJustProtementation of a IndexTweak buffer

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

public class CFBamProtBuffIndexTweak
	extends CFBamProtBuffTweak
	implements ICFBamIndexTweak
{
	protected ICFLibKeyHash256 requiredIndexId;

	public CFBamProtBuffIndexTweak() {
		super();
		requiredIndexId = CFLibDbKeyHash256.fromHex( ICFBamPubIndexTweak.INDEXID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamIndexTweak.CLASS_CODE );
	}

	@Override
	public void setJustProtRequiredContainerScopeDef(ICFLibKeyHash256 argIndexId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerIndexDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argIndexId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef-args", 0, "found");
		}
		else if (found instanceof ICFBamIndex) || (found instanceof ICFBamProtIndex) || (found instanceof ICFBamPubIndex)) {
			super.setJustProtRequiredContainerScopeDef(argIndexId);
		requiredIndexId = argIndexId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScopeDef-args", "found", found, "ICFBamIndexICFBamProtIndexICFBamPubIndex");
		}
	}

	@Override
	public void setJustProtRequiredContainerScopeDef(ICFBamIndex argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerIndexDef", 1, "argObj");
		}
		else if ((argObj instanceof ICFBamIndex) || (argObj instanceof ICFBamProtIndex) || (argObj instanceof ICFBamPubIndex)) {
			setJustProtRequiredIndexId(argObj.getRequiredId());
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerIndexDef", "argObj", argObj, "ICFBamIndex, ICFBamProtIndex), ICFBamPubIndex)");
		}
	}

	@Override
	public void setJustProtRequiredContainerScopeDef(ICFBamProtIndex argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerIndexDef", 1, "argObj");
		}
		else if ((argObj instanceof ICFBamProtIndex) || (argObj instanceof ICFBamPubIndex)) {
			setJustProtRequiredIndexId(argObj.getRequiredId());
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerIndexDef", "argObj", argObj, "ICFBamProtIndex, ICFBamPubIndex)");
		}
	}

	@Override
	public void setJustProtRequiredContainerScopeDef(ICFBamPubIndex argObj) {

		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerIndexDef", 1, "argObj");
		}
		else if (argObj instanceof ICFBamPubIndex) {
			setJustProtRequiredIndexId(argObj.getRequiredId());
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtContainerIndexDef", "argObj", argObj, "ICFBamPubIndex");
		}
	}

	@Override
	public ICFBamIndex getRequiredContainerIndexDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingCFBam.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredIndexId());
		return(targetRec);
	}

	@Override
	public ICFBamIndex getRequiredContainerIndexDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingCFBam.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredIndexId());
		return(targetRec);
	}

	@Override
	public ICFBamIndex getRequiredContainerIndexDef() {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingCFBam.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "getRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamPubIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredIndexId());
		return(targetRec);
	}

	@Override
	public void setJustProtRequiredContainerScopeDef(ICFLibKeyHash256 argIndexId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerIndexDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argIndexId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef-args", 0, "found");
		}
		else if (found instanceof ICFBamIndex) || (found instanceof ICFBamProtIndex) || (found instanceof ICFBamPubIndex)) {
			super.setJustProtRequiredContainerScopeDef(argIndexId);
		requiredIndexId = argIndexId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScopeDef-args", "found", found, "ICFBamIndexICFBamProtIndexICFBamPubIndex");
		}
	}

	@Override
	public void setJustProtRequiredContainerIndexDef(ICFLibKeyHash256 argIndexId) {
		ICFBamSchema targetBackingCFBam = ICFBamSchema.getBackingCFBam();
		if (targetBackingCFBam == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerIndexDef-args", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamScopeTable targetTable = targetBackingCFBam.getTableScope();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef", 0, "ICFBamSchema.getBackingCFBam().getTableScope()");
		}
		ICFBamScope found = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), argIndexId);
		if (found == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtRequiredContainerScopeDef-args", 0, "found");
		}
		else if (found instanceof ICFBamIndex) || (found instanceof ICFBamProtIndex) || (found instanceof ICFBamPubIndex)) {
			super.setJustProtRequiredContainerScopeDef(argIndexId);
		requiredIndexId = argIndexId;
		}
		else {
			throw new CFLibUnsupportedClassException(getClass(), "setJustProtRequiredContainerScopeDef-args", "found", found, "ICFBamIndexICFBamProtIndexICFBamPubIndex");
		}
	}

	@Override
	public void setJustProtRequiredContainerIndexDef(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setJustProtContainerIndexDef", 1, "argObj");
		}
		else {
			setJustProtRequiredIndexId(argObj.getRequiredId());
		}
	}

	@Override
	public ICFLibKeyHash256 getRequiredIndexId() {
		return(requiredIndexId);
	}

	public void setRequiredIndexId( ICFLibKeyHash256 value ) {
		if( value == null || value.isNull() ) {
			throw new CFLibNullArgumentException( getClass(),
				"setRequiredIndexId",
				1,
				"value" );
		}
		requiredIndexId = value;
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamProtIndexTweak rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexTweakH rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamTweakHPKey ) {
			ICFBamProtTweakHPKey rhs = (ICFBamTweakHPKey)obj;
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
		else if( obj instanceof ICFBamProtIndexTweakByIndexIdxKey rhs ) {
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexTweak rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtIndexTweakH rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtTweakHPKey rhs ) {
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
		else if( obj instanceof ICFBamProtIndexTweakByIndexIdxKey rhs ) {
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubIndexTweak rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubIndexTweakH rhs ) {
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
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubTweakHPKey rhs ) {
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
		else if( obj instanceof ICFBamProtIndexTweakByIndexIdxKey rhs ) {
			if( getRequiredIndexId() != null ) {
				if( rhs.getRequiredIndexId() != null ) {
					if( ! getRequiredIndexId().equals( rhs.getRequiredIndexId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredIndexId() != null ) {
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
		hashCode = hashCode + getRequiredIndexId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamProtIndexTweak rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
 		}
		else if( obj instanceof ICFBamProtTweakHPKey rhs ) {
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
		else if( obj instanceof ICFBamProtIndexTweakH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamIndexTweakByIndexIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexTweak rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubTweakHPKey rhs ) {
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
		else if( obj instanceof ICFBamPubIndexTweakH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubIndexTweakByIndexIdxKey rhs ) {
			if (getRequiredIndexId() != null) {
				if (rhs.getRequiredIndexId() != null) {
					cmp = getRequiredIndexId().compareTo( rhs.getRequiredIndexId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredIndexId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			cmp = super.compareTo( obj );
			return( cmp );
		}
	}

	@Override
	public void setJustProt( ICFBamTweak src ) {
		if( src instanceof CFBamProtBuffIndexTweak ) {
			setJustProtIndexTweak( (CFBamProtBuffIndexTweak)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffIndexTweak" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamIndexTweak src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public void setJustProt( ICFBamTweakH src ) {
		if( src instanceof ICFBamProtIndexTweakH ) {
			setIndexTweak( (ICFBamProtIndexTweakH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamIndexTweakH" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamIndexTweakH src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public void setJustProt( ICFBamProtTweak src ) {
		if( src instanceof CFBamProtBuffIndexTweak ) {
			setJustProtIndexTweak( (CFBamProtBuffIndexTweak)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffIndexTweak" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamProtIndexTweak src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public void setJustProt( ICFBamProtTweakH src ) {
		if( src instanceof ICFBamProtIndexTweakH ) {
			setIndexTweak( (ICFBamProtIndexTweakH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamIndexTweakH" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamProtIndexTweakH src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public void setJustProt( ICFBamPubTweak src ) {
		if( src instanceof CFBamPubProtBuffIndexTweak ) {
			setJustProtIndexTweak( (CFBamPubProtBuffIndexTweak)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubProtBuffIndexTweak" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamPubIndexTweak src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public void setJustProt( ICFBamPubTweakH src ) {
		if( src instanceof ICFBamPubIndexTweakH ) {
			setIndexTweak( (ICFBamPubIndexTweakH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamIndexTweakH" );
		}
	}

	@Override
	public void setJustProtIndexTweak( ICFBamPubIndexTweakH src ) {
		super.setJustProtTweak( src );
		setJustProtRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
		setJustProtRequiredIndexId(src.getRequiredIndexId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredIndexId=" + "\"" + getRequiredIndexId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamProtBuffIndexTweak" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
