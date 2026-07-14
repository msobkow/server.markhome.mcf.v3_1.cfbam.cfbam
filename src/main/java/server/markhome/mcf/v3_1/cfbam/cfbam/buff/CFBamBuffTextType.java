// Description: Java 25 implementation of a TextType buffer

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

public class CFBamBuffTextType
	extends CFBamBuffTextDef
	implements ICFBamTextType
{
	protected CFLibDbKeyHash256 requiredSchemaDefId;

	public CFBamBuffTextType() {
		super();
		requiredSchemaDefId = CFLibDbKeyHash256.fromHex( ICFBamTextType.SCHEMADEFID_INIT_VALUE.toString() );
	}

	@Override
	public int getClassCode() {
		return( ICFBamTextType.CLASS_CODE );
	}

	@Override
	public ICFBamSchemaDef getRequiredContainerSchemaDef() {
		ICFBamSchema targetBackingSchema = ICFBamSchema.getBackingCFBam();
		if (targetBackingSchema == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam()");
		}
		ICFBamSchemaDefTable targetTable = targetBackingSchema.getTableSchemaDef();
		if (targetTable == null) {
			throw new CFLibNullArgumentException(getClass(), "setRequiredContainerSchemaDef", 0, "ICFBamSchema.getBackingCFBam().getTableSchemaDef()");
		}
		ICFBamSchemaDef targetRec = targetTable.readDerived(ICFSecSchema.getAuthorizationCallback().getEffectiveAuthorization(), getRequiredSchemaDefId());
		return(targetRec);
	}
	@Override
	public void setRequiredContainerSchemaDef(ICFBamSchemaDef argObj) {
		if(argObj == null) {
			throw new CFLibNullArgumentException(getClass(), "setContainerSchemaDef", 1, "argObj");
		}
		else {
			requiredSchemaDefId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerSchemaDef(ICFBamProtSchemaDef argObj) {
		if(argObj == null) {
			requiredSchemaDefId = null;
		}
		else {
			requiredSchemaDefId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerSchemaDef(ICFBamPubSchemaDef argObj) {
		if(argObj == null) {
			requiredSchemaDefId = null;
		}
		else {
			requiredSchemaDefId = argObj.getRequiredId();
		}
	}

	@Override
	public void setRequiredContainerSchemaDef(CFLibDbKeyHash256 argSchemaDefId) {
		requiredSchemaDefId = argSchemaDefId;
	}

	@Override
	public CFLibDbKeyHash256 getRequiredSchemaDefId() {
		return( requiredSchemaDefId );
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return( false );
		}
		else if( obj instanceof ICFBamTextType rhs ) {
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
		else if( obj instanceof ICFBamTextTypeH ) {
			ICFBamTextTypeH rhs = (ICFBamTextTypeH)obj;
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
		else if( obj instanceof ICFBamTextTypeBySchemaIdxKey ) {
			ICFBamTextTypeBySchemaIdxKey rhs = (ICFBamTextTypeBySchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamProtTextType rhs ) {
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
		else if( obj instanceof ICFBamProtTextTypeH rhs ) {
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
		else if( obj instanceof ICFBamTextTypeBySchemaIdxKey ) {
			ICFBamTextTypeBySchemaIdxKey rhs = (ICFBamTextTypeBySchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamPubTextType rhs ) {
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
		else if( obj instanceof ICFBamPubTextTypeH rhs ) {
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
		else if( obj instanceof ICFBamTextTypeBySchemaIdxKey ) {
			ICFBamTextTypeBySchemaIdxKey rhs = (ICFBamTextTypeBySchemaIdxKey)obj;
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
		else if( obj instanceof ICFBamTextType rhs ) {
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
		else if( obj instanceof ICFBamTextTypeH rhs ) {
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
		else if( obj instanceof ICFBamTextTypeBySchemaIdxKey rhs ) {
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
		else if( obj instanceof ICFBamProtTextType rhs ) {
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
		else if( obj instanceof ICFBamProtTextTypeH rhs ) {
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
		else if( obj instanceof ICFBamProtTextTypeBySchemaIdxKey rhs ) {
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
		else if( obj instanceof ICFBamPubTextType rhs ) {
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
		else if( obj instanceof ICFBamPubTextTypeH rhs ) {
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
		else if( obj instanceof ICFBamPubTextTypeBySchemaIdxKey rhs ) {
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
	public void set( ICFBamValue src ) {
		if( src instanceof CFBamBuffTextType ) {
			setTextType( (CFBamBuffTextType)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamBuffTextType" );
		}
	}

	@Override
	public void setTextType( ICFBamTextType src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
	}

	@Override
	public void set( ICFBamValueH src ) {
		if( src instanceof ICFBamTextTypeH ) {
			setTextType( (ICFBamTextTypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamTextTypeH" );
		}
	}

	@Override
	public void setTextType( ICFBamTextTypeH src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredSchemaDefId());
	}

	@Override
	public void set( ICFBamProtValue src ) {
		if( src instanceof CFBamProtBuffTextType ) {
			setTextType( (CFBamProtBuffTextType)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamProtBuffTextType" );
		}
	}

	@Override
	public void setTextType( ICFBamProtTextType src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
	}

	@Override
	public void set( ICFBamProtValueH src ) {
		if( src instanceof ICFBamProtTextTypeH ) {
			setTextType( (ICFBamProtTextTypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamProtTextTypeH" );
		}
	}

	@Override
	public void setTextType( ICFBamProtTextTypeH src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredSchemaDefId());
	}

	@Override
	public void set( ICFBamPubValue src ) {
		if( src instanceof CFBamPubBuffTextType ) {
			setTextType( (CFBamPubBuffTextType)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				"compareTo",
				"src",
				src,
				"CFBamPubBuffTextType" );
		}
	}

	@Override
	public void setTextType( ICFBamPubTextType src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredContainerSchemaDef());
	}

	@Override
	public void set( ICFBamPubValueH src ) {
		if( src instanceof ICFBamPubTextTypeH ) {
			setTextType( (ICFBamPubTextTypeH)src );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
					"set",
					"src",
					src,
					"ICFBamPubTextTypeH" );
		}
	}

	@Override
	public void setTextType( ICFBamPubTextTypeH src ) {
		super.setTextDef( src );
		setRequiredContainerSchemaDef(src.getRequiredSchemaDefId());
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
		String ret = "<CFBamBuffTextType" + getXmlAttrFragment() + "/>";
		return( ret );
	}
}
