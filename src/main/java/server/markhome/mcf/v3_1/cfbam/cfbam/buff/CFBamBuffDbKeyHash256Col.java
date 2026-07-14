// Description: Java 25 implementation of a DbKeyHash256Col buffer

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

public class CFBamBuffDbKeyHash256Col
	extends CFBamBuffDbKeyHash256Def
	implements ICFBamDbKeyHash256Col
{
	protected CFLibDbKeyHash256 requiredTableId;

	public CFBamBuffDbKeyHash256Col() {
		super();
		requiredTableId = CFLibDbKeyHash256.fromHex( ICFBamDbKeyHash256Col.TABLEID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamDbKeyHash256Col.CLASS_CODE );
	}

	@Override
	public ICFBamTable getRequiredContainerTable() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerTable", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamTableTable targetTable = targetBackingSchema.getTableTable();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerTable", 0, "ICFBamSchema.getBackingCFBam().getTableTable()");
		}
		ICFBamTable targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredTableId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerTable(ICFBamTable argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerTable", 1, "argObj");
		}
		else {
			requiredTableId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerTable(ICFBamProtTable argObj) {
		if(argObj == null) {
			requiredTableId = null;
		}
		else {
			requiredTableId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerTable(ICFBamPubTable argObj) {
		if(argObj == null) {
			requiredTableId = null;
		}
		else {
			requiredTableId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerTable(CFLibDbKeyHash256 argTableId) {
		requiredTableId = argTableId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredTableId() {
		return( requiredTableId );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamDbKeyHash256Col rhs ) {
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamDbKeyHash256ColH ) {
			ICFBamDbKeyHash256ColH rhs = (ICFBamDbKeyHash256ColH)obj;
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamValueHPKey ) {
			ICFBamValueHPKey rhs = (ICFBamValueHPKey)obj;
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
		else if( obj instanceof ICFBamDbKeyHash256ColByTableIdxKey ) {
			ICFBamDbKeyHash256ColByTableIdxKey rhs = (ICFBamDbKeyHash256ColByTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtDbKeyHash256Col rhs ) {
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamProtDbKeyHash256ColH rhs ) {
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
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
		else if( obj instanceof ICFBamDbKeyHash256ColByTableIdxKey ) {
			ICFBamDbKeyHash256ColByTableIdxKey rhs = (ICFBamDbKeyHash256ColByTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubDbKeyHash256Col rhs ) {
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
					return( false );
				}
			}
			return( true );
		}
		else if( obj instanceof ICFBamPubDbKeyHash256ColH rhs ) {
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
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
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
		else if( obj instanceof ICFBamDbKeyHash256ColByTableIdxKey ) {
			ICFBamDbKeyHash256ColByTableIdxKey rhs = (ICFBamDbKeyHash256ColByTableIdxKey)obj;
			if( getRequiredTableId() != null ) {
				if( rhs.getRequiredTableId() != null ) {
					if( ! getRequiredTableId().equals( rhs.getRequiredTableId() ) ) {
						return( false );
					}
				}
				else {
					return( false );
				}
			}
			else {
				if( rhs.getRequiredTableId() != null ) {
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
		hashCode = hashCode + getRequiredTableId().hashCode();
		return( hashCode & 0x7fffffff );
	}

	@Override
	public int compareTo( Object obj ) {
		int cmp;
		if( obj == null ) {
			return( -1 );
		}
		else if( obj instanceof ICFBamDbKeyHash256Col rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamValueHPKey rhs ) {
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
		else if( obj instanceof ICFBamDbKeyHash256ColH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamDbKeyHash256ColByTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamProtDbKeyHash256Col rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
		else if( obj instanceof ICFBamProtDbKeyHash256ColH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamProtDbKeyHash256ColByTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else if( obj instanceof ICFBamPubDbKeyHash256Col rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
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
		else if( obj instanceof ICFBamPubDbKeyHash256ColH rhs ) {
			cmp = super.compareTo( rhs );
			if( cmp != 0 ) {
				return( cmp );
			}
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}
			return( 0 );
		}
		else if( obj instanceof ICFBamPubDbKeyHash256ColByTableIdxKey rhs ) {
			if (getRequiredTableId() != null) {
				if (rhs.getRequiredTableId() != null) {
					cmp = getRequiredTableId().compareTo( rhs.getRequiredTableId() );
					if( cmp != 0 ) {
						return( cmp );
					}
				}
				else {
					return( 1 );
				}
			}
			else if (rhs.getRequiredTableId() != null) {
				return( -1 );
			}			return( 0 );
		}
		else {
			cmp = super.compareTo( obj );
			return( cmp );
		}
	}

	@Override
	public void set( ICFBamValue src ) {
		if( src instanceof CFBamBuffDbKeyHash256Col ) {
			setDbKeyHash256Col( (CFBamBuffDbKeyHash256Col)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamBuffDbKeyHash256Col" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamDbKeyHash256Col src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredContainerTable());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamDbKeyHash256ColH ) {
			setDbKeyHash256Col( (ICFBamDbKeyHash256ColH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamDbKeyHash256ColH" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamDbKeyHash256ColH src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredTableId());
	}

	@Override
	public void set( ICFBamProtValue src ) {
		if( src instanceof CFBamProtBuffDbKeyHash256Col ) {
			setDbKeyHash256Col( (CFBamProtBuffDbKeyHash256Col)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffDbKeyHash256Col" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamProtDbKeyHash256Col src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredContainerTable());
	}

	@Override
	public void set( ICFBamProtValueH src ) {
		if( src instanceof ICFBamProtDbKeyHash256ColH ) {
			setDbKeyHash256Col( (ICFBamProtDbKeyHash256ColH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamProtDbKeyHash256ColH" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamProtDbKeyHash256ColH src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredTableId());
	}

	@Override
	public void set( ICFBamPubValue src ) {
		if( src instanceof CFBamPubBuffDbKeyHash256Col ) {
			setDbKeyHash256Col( (CFBamPubBuffDbKeyHash256Col)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubBuffDbKeyHash256Col" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamPubDbKeyHash256Col src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredContainerTable());
	}

	@Override
	public void set( ICFBamPubValueH src ) {
		if( src instanceof ICFBamPubDbKeyHash256ColH ) {
			setDbKeyHash256Col( (ICFBamPubDbKeyHash256ColH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPubDbKeyHash256ColH" );
		}
	}

	@Override
	public void setDbKeyHash256Col( ICFBamPubDbKeyHash256ColH src ) {
		super.setDbKeyHash256Def( src );
		setRequiredContainerTable(src.getRequiredTableId());
	}

	@Override
	public String getXmlAttrFragment() {
		String ret = super.getXmlAttrFragment() 
			+ " RequiredId=" + "\"" + getRequiredId().toString() + "\""
			+ " RequiredTableId=" + "\"" + getRequiredTableId().toString() + "\"";
		return( ret );
	}

	@Override
	public String toString() {
		String ret = "<CFBamBuffDbKeyHash256Col" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
