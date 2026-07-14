// Description: Java 25 implementation of a IndexTweak buffer

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
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfbam.cfbamobj.*;

public class CFBamBuffIndexTweak
	extends CFBamBuffTweak
	implements ICFBamIndexTweak
{
	protected CFLibDbKeyHash256 requiredIndexId;

	public CFBamBuffIndexTweak() {
		super();
		requiredIndexId = CFLibDbKeyHash256.fromHex( ICFBamIndexTweak.INDEXID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamIndexTweak.CLASS_CODE );
	}

	@Override
	public ICFBamIndex getRequiredContainerIndexDef() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamIndexTable targetTable = targetBackingSchema.getTableIndex();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerIndexDef", 0, "ICFBamSchema.getBackingCFBam().getTableIndex()");
		}
		ICFBamIndex targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredIndexId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerIndexDef(ICFBamIndex argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerIndexDef", 1, "argObj");
		}
		else {
			requiredIndexId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerIndexDef(CFLibDbKeyHash256 argIndexId) {
		requiredIndexId = argIndexId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredIndexId() {
		return( requiredIndexId );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamIndexTweak rhs ) {
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
		else if( obj instanceof ICFBamIndexTweakH ) {
			ICFBamIndexTweakH rhs = (ICFBamIndexTweakH)obj;
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
			ICFBamTweakHPKey rhs = (ICFBamTweakHPKey)obj;
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
		else if( obj instanceof ICFBamIndexTweakByIndexIdxKey ) {
			ICFBamIndexTweakByIndexIdxKey rhs = (ICFBamIndexTweakByIndexIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexTweakByIndexIdxKey ) {
			ICFBamIndexTweakByIndexIdxKey rhs = (ICFBamIndexTweakByIndexIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexTweakByIndexIdxKey ) {
			ICFBamIndexTweakByIndexIdxKey rhs = (ICFBamIndexTweakByIndexIdxKey)obj;
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
		else if( obj instanceof ICFBamIndexTweak rhs ) {
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
		else if( obj instanceof ICFBamTweakHPKey rhs ) {
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
		else if( obj instanceof ICFBamIndexTweakH rhs ) {
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
		else if( obj instanceof ICFBamProtIndexTweakByIndexIdxKey rhs ) {
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
	public void set( ICFBamTweak src ) {
		if( src instanceof CFBamBuffIndexTweak ) {
			setIndexTweak( (CFBamBuffIndexTweak)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamBuffIndexTweak" );
		}
	}

	@Override
	public void setIndexTweak( ICFBamIndexTweak src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
	}

	@Override
	public void set( ICFBamTweakH src ) {
		if( src instanceof ICFBamIndexTweakH ) {
			setIndexTweak( (ICFBamIndexTweakH)src );
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
	public void setIndexTweak( ICFBamIndexTweakH src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredIndexId());
	}

	@Override
	public void set( ICFBamProtTweak src ) {
		if( src instanceof CFBamProtBuffIndexTweak ) {
			setIndexTweak( (CFBamProtBuffIndexTweak)src );
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
	public void setIndexTweak( ICFBamProtIndexTweak src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
	}

	@Override
	public void set( ICFBamProtTweakH src ) {
		if( src instanceof ICFBamProtIndexTweakH ) {
			setIndexTweak( (ICFBamProtIndexTweakH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamProtIndexTweakH" );
		}
	}

	@Override
	public void setIndexTweak( ICFBamProtIndexTweakH src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredIndexId());
	}

	@Override
	public void set( ICFBamPubTweak src ) {
		if( src instanceof CFBamPubBuffIndexTweak ) {
			setIndexTweak( (CFBamPubBuffIndexTweak)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubBuffIndexTweak" );
		}
	}

	@Override
	public void setIndexTweak( ICFBamPubIndexTweak src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredContainerIndexDef());
	}

	@Override
	public void set( ICFBamPubTweakH src ) {
		if( src instanceof ICFBamPubIndexTweakH ) {
			setIndexTweak( (ICFBamPubIndexTweakH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPubIndexTweakH" );
		}
	}

	@Override
	public void setIndexTweak( ICFBamPubIndexTweakH src ) {
		super.setTweak( src );
		setRequiredContainerIndexDef(src.getRequiredIndexId());
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
		String ret = "<CFBamBuffIndexTweak" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
